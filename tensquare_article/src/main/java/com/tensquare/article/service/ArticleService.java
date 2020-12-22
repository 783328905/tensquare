package com.tensquare.article.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbitmq.client.AMQP;
import com.tensquare.article.client.NoticeClient;
import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.pojo.Notice;
import com.tensquare.util.IdWorker;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/21 13:43
 * 4
 */
@Service
public class ArticleService {

    @Autowired private RedisTemplate redisTemplate;
    @Autowired private ArticleDao articleDao;
    @Autowired private IdWorker idWorker;
    @Autowired private NoticeClient noticeClient;
    @Autowired private RabbitTemplate rabbitTemplate;

    public List<Article> findAll() {
        return articleDao.selectList(new QueryWrapper<Article>().eq("state",1));
    }

    public Article findOneById(String id){
        return articleDao.selectById(id);

    }

    public Boolean save(Article article) {
        //使用分布式id生成器
        String id = idWorker.nextId() + "";article.setId(id);

        //初始化数据
        article.setVisits(0);   //浏览量
        article.setThumbup(0);  //点赞数
        article.setComment(0);  //评论数
        //新增
        int row = articleDao.insert(article);
        String authorId = "3";
        //获取需要通知的读者
        String authorKey = "article_author_" + authorId;
        Set<String> set = redisTemplate.boundSetOps(authorKey).members();

        for (String uid : set) {
            //消息通知
            Notice notice = new Notice();
            notice.setReceiverId(uid);
            notice.setOperatorId(authorId);
            notice.setAction("publish");
            notice.setTargetType("article");
            notice.setTargetId(id);
            notice.setCreatetime(new Date());
            notice.setType("sys");
            notice.setState("0");
            noticeClient.add(notice);
        }
        rabbitTemplate.convertAndSend("article_subscribe",authorId,id);
        return row ==1;
    }

    public Boolean update(Article article) {
        return articleDao.updateById(article) ==1;
    }

    public Boolean deleteById(String articleId) {
        return articleDao.deleteById(articleId) ==1;
    }

    public Page<Article> findByPage(Map<String,Object> map, Integer page, Integer size) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        map.forEach((x,y)->{
            if(null!=y){
                queryWrapper.eq(x,y);
            }
        });
        Page<Article> pageData = new Page<>(page,size);
        IPage<Article> articleIPage = articleDao.selectPage(pageData, queryWrapper);

        pageData.setRecords(articleIPage.getRecords());
        return pageData;
    }

    public Boolean subscribe(String userId, String articleId) {
        //根据文章id查询文章作者id
        String authorId = articleDao.selectById(articleId).getUserid();
        String userKey = "article_subscribe_" + userId;
        String authorKey = "article_author_" + authorId;

        //绑定消息交换机
        AmqpAdmin amqpAdmin  = new RabbitAdmin(rabbitTemplate.getConnectionFactory());
        DirectExchange exchange = new DirectExchange("article_subscribe");
        amqpAdmin.declareExchange(exchange);
        Queue queue = new Queue("article_subscribe"+userId,true);
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(authorId);


        //查询该用户是否已经订阅作者
        Boolean flag = redisTemplate.boundSetOps(userKey).isMember(authorId);
        if (flag) {
            //如果为flag为true，已经订阅,则取消订阅
            redisTemplate.boundSetOps(userKey).remove(authorId);
            redisTemplate.boundSetOps(authorKey).remove(userId);
            //取消订阅，删除队列绑定关系
            amqpAdmin.removeBinding(binding);
            return false;
        } else {
            // 如果为flag为false，没有订阅，则进行订阅
            redisTemplate.boundSetOps(userKey).add(authorId);
            redisTemplate.boundSetOps(authorKey).add(userId);

            amqpAdmin.declareQueue(queue);
            amqpAdmin.declareBinding(binding);
            return true;
        }
    }

    public Boolean thumbup(String articleId, String userId) {
        String key = "thumbup_article_" + userId + "_" + articleId;
        Object flag = redisTemplate.opsForValue().get(key);
        if(flag ==null){
            Article article = articleDao.selectById(articleId);
            article.setThumbup(article.getThumbup() + 1);
            articleDao.updateById(article);
            //消息通知
            Notice notice = new Notice();
            notice.setReceiverId(article.getUserid());
            notice.setOperatorId(userId);
            notice.setAction("thumbup");
            notice.setTargetType("article");
            notice.setTargetId(articleId);
            notice.setCreatetime(new Date());
            notice.setType("user");
            notice.setState("0");
            noticeClient.add(notice);
            //点赞成功，保存点赞信息
            redisTemplate.opsForValue().set(key, 1);
            return true;
        }else {
            return false;
        }
    }
}

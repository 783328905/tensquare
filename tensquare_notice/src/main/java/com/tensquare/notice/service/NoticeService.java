package com.tensquare.notice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tensquare.entity.Result;
import com.tensquare.notice.client.ArticleClient;
import com.tensquare.notice.client.UserClient;
import com.tensquare.notice.dao.NoticeDao;
import com.tensquare.notice.dao.NoticeFreshDao;
import com.tensquare.notice.pojo.Notice;
import com.tensquare.notice.pojo.NoticeFresh;
import com.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/24 13:30
 * 4
 */
@Service
public class NoticeService {
    @Autowired private NoticeDao noticeDao;
    @Autowired private NoticeFreshDao noticeFreshDao;
    @Autowired private IdWorker idWorker;
    @Autowired private UserClient userClient;
    @Autowired private ArticleClient articleClient;



    public Notice selectById(String id) {
        return noticeDao.selectById(id);
    }

    public Page<Notice> selectByPage(Notice notice, Integer page, Integer size) {
        Page <Notice> pageData = new Page<>(page,size);
        IPage<Notice> iPage = noticeDao.selectPage(pageData, new QueryWrapper<>(notice));
        pageData.setRecords(iPage.getRecords());
        for(Notice n:pageData.getRecords()){
            getNoticeInfo(n);
        }
        return pageData;
    }

    public void updateById(Notice notice) {
        noticeDao.updateById(notice);
    }

    public void save(Notice notice) {
        notice.setState("0");
        notice.setCreatetime(new Date());
        String id = idWorker.nextId() + "";
        notice.setId(id);
        noticeDao.insert(notice);

//        NoticeFresh noticeFresh = new NoticeFresh();
//        noticeFresh.setNoticeId(id);
//        noticeFresh.setUserId(notice.getReceiverId());
//        noticeFreshDao.insert(noticeFresh);
    }

    public void freshDelete(NoticeFresh noticeFresh) {
        noticeFreshDao.delete(new QueryWrapper<>( noticeFresh));
    }

    private void getNoticeInfo(Notice notice) {
        //获取用户信息
        Result userResult = userClient.findById(notice.getOperatorId());
        HashMap userMap = (HashMap) userResult.getData();
        notice.setOperatorName(userMap.get("nickname").toString());
        //获取文章信息
        if ("article".equals(notice.getTargetType())) {
            Result articleResult = articleClient.findByArticleId(notice.getTargetId());
            HashMap articleMap = (HashMap) articleResult.getData();
            notice.setTargetName(articleMap.get("title").toString());
        }
    }
    public Page<NoticeFresh> freshPage(String userId, Integer page, Integer size) {
        //封装查询条件
        NoticeFresh noticeFresh = new NoticeFresh();
        noticeFresh.setUserId(userId);
        //创建分页对象
        Page<NoticeFresh> pageData = new Page<>(page, size);
        //执行查询
         IPage<NoticeFresh> iPage = noticeFreshDao.selectPage(pageData, new QueryWrapper<>(noticeFresh));
        //设置查询结果集到分页对象中
        pageData.setRecords(iPage.getRecords());
        //返回结果
        return pageData;
    }


}

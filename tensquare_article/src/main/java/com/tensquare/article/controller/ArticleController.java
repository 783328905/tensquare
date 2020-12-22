package com.tensquare.article.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.util.IdWorker;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.RequestingUserName;
import java.util.List;
import java.util.Map;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/21 13:43
 * 4
 */
@RestController
@RequestMapping("/article")
@CrossOrigin({"127.0.0.1","ctillnow.com"})
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private IdWorker idWorker;

    @GetMapping
    public Result<List<Article>> findAll(){
        List<Article> articles = articleService.findAll();
        return Result.ok(articles);
    }
    @GetMapping("/{articleId}")
    public Result<Article> findOneById(@PathVariable("articleId") String articleId){
        Article article = articleService.findOneById(articleId);
        return Result.ok(article);
    }
    @PostMapping
    public Result<Boolean> save(@RequestBody Article article){
        String id = idWorker.nextId()+"";
        article.setId(id);
        article.setVisits(0);
        article.setThumbup(0);
        article.setComment(0);
        article.setVisits(0);
        boolean flag = articleService.save(article);
        return Result.ok(flag);
    }
    @PutMapping("/{articleId}")
    public Result<Boolean> update(@PathVariable("articleId") String articleId, @RequestBody Article article){
        article.setId(articleId);
        boolean flag = articleService.update(article);
        return Result.ok(flag);
    }
    @DeleteMapping("/{articleId}")
    public Result<Boolean> delete(@PathVariable("articleId") String articleId){
        boolean flag = articleService.deleteById(articleId);
        return Result.ok(flag);
    }
    @GetMapping("/search")
    public Result<List<Article>> searchByCondition(){

        return null;
    }
    @GetMapping("/search/{page}/{size}")
    public Result<PageResult> findByPage(@PathVariable Integer page,
                                            @PathVariable Integer size,
                                            @RequestBody Map<String,Object> map){
        Page<Article> pageData = articleService.findByPage(map,page,size);

        return Result.ok( new PageResult<Article>(pageData.getTotal(),pageData.getRecords()));
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    private Result subscribe(@RequestBody Map map) {
        //根据文章id，订阅文章作者，返回订阅状态，true表示订阅成功，false表示取消订阅成功
        Boolean flag = articleService.subscribe(map.get("userId").toString(), map.get("articleId").toString());
        if (flag) {
            return Result.ok("订阅成功");
        } else {
            return Result.ok("订阅取消");
        }
    }

    @GetMapping("/exception")
    public void exception(){
        int i = 1/0;
        return;
    }

    @RequestMapping(value = "thumbup/{articleId}", method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String articleId) {
        //模拟用户id
        String userId = "4";
        boolean flag = articleService.thumbup(articleId,userId);
        return flag? Result.ok("点赞成功"):Result.fail("不能重复点赞");
    }


}

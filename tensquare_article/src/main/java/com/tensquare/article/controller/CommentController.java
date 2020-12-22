package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import com.tensquare.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/23 18:33
 * 4
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired private CommentService commentService;
    @Autowired private RedisTemplate redisTemplate;

    @GetMapping("/{id}")
    public Result<Comment> findById(@PathVariable(value = "id") String id){
       return Result.ok(  commentService.findById(id));
    }
    @GetMapping
    public Result<List<Comment>> findById(){
        return Result.ok(  commentService.findAll());
    }
    @PostMapping
    public Result save(@RequestBody Comment comment){
        commentService.save(comment);
        return Result.ok("新增成功",null);
    }
    @PutMapping("/{id}")
    public Result update(@RequestBody Comment comment){
        commentService.update(comment);
        return Result.ok("修改成功",null);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(value = "id") String id){
        commentService.deleteById(id);
        return Result.ok("删除成功",null);
    }
    @GetMapping("/article/{articleId}")
    public Result<List<Comment>> findByArticleId(@PathVariable(value = "articleId") String articleId){
        return Result.ok(commentService.findByArticleId(articleId));
    }
    @PutMapping("/thumbup/{id}/{userId}")
    public Result thumbup(@PathVariable(value = "id") String id,@PathVariable(value = "userId") String userId){
        Object result = redisTemplate.opsForHash().get("ct_tensquare_thumbup", "thumbup_" + userId + "_" + id);
        if(result!=null){
            return Result.fail("不能重复点赞！");
        }
        commentService.thumbup(id);
        redisTemplate.opsForHash().put( "ct_tensquare_thumbup", "thumbup_" + userId + "_" + id,1);
        return Result.ok("点赞成功",null);
    }


}

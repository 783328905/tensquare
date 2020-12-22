package com.tensquare.article.service;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.repository.CommentRepository;
import com.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/23 18:21
 * 4
 */
@Service
public class CommentService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private CommentRepository commentRepository;
    public Comment findById(String id){
        return commentRepository.findById(id).get();
    }
    public List<Comment> findAll(){
        return commentRepository.findAll();
    }
    public Comment save(Comment comment){
        String id = idWorker.nextId() + "";
        comment.set_id(id);
        comment.setPublishdate(new Date());
        comment.setThumbup(0);
        return commentRepository.save(comment);

    }
    public void deleteById(String id){
        commentRepository.deleteById(id);
    }
    public void update(Comment comment){
        commentRepository.save(comment);
    }

    public List<Comment> findByArticleId(String articleId){
        return commentRepository.findByArticleid(articleId);
    }
    public void thumbup(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"comment");
    }
}

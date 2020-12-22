package com.tensquare.article.repository;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/23 18:20
 * 4
 */
public interface CommentRepository extends MongoRepository<Comment,String> {
    List<Comment> findByArticleid(String articleId);

}

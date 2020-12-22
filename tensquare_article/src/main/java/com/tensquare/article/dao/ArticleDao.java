package com.tensquare.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tensquare.article.pojo.Article;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/21 13:40
 * 4
 */
public interface ArticleDao extends BaseMapper<Article> {

    void incrThumbup(String articleId);
}

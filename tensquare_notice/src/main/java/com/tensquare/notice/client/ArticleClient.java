package com.tensquare.notice.client;

import com.tensquare.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/24 14:29
 * 4
 */
@FeignClient("tensquare-article")
public interface ArticleClient {
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public Result findByArticleId(@PathVariable("articleId") String articleId);
}

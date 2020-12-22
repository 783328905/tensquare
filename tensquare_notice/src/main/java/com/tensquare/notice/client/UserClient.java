package com.tensquare.notice.client;

import com.tensquare.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/24 14:29
 * 4
 */
@FeignClient("tensquare-user")
public interface UserClient {
    @RequestMapping(value="/user/{userId}",method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "userId") String userId);
}

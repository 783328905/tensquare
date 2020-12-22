package com.tensquare.user.controller;

import com.tensquare.entity.Result;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/22 15:37
 * 4
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user){
        User result = userService.login(user);
        result.setPassword(null);
        if(result!=null){
            return Result.ok("登录成功",user);
        }else {
            return Result.fail("用户名或密码错误！");
        }
    }

    @GetMapping("/{userId}")
    public Result<User> findById(@PathVariable String userId){
        User user = userService.selectById(userId);
        return Result.ok(user);


    }
}

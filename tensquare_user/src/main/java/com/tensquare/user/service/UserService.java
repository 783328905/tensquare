package com.tensquare.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tensquare.user.dao.UserDao;
import com.tensquare.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/22 14:21
 * 4
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public User login(User user) {
        QueryWrapper<User> queryWrapper= new QueryWrapper<User>()
                .eq("username", user.getNickname())
                .eq("password", user.getPassword());
        return userDao.selectOne(queryWrapper);
    }

    public User selectById(String userId) {
        User user = userDao.selectById(userId);
        return user;
    }
}
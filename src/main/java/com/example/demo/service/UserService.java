package com.example.demo.service;

import com.example.demo.entiy.User;

/**
 * 用户服务类
 */
public interface UserService {
    //是否有该用户
    public boolean isHaveUser(int userId);

    //获得该用户信息
    public User getUserByUserId(int userId);

    public int addUser(User user);

    public int deleteUser(User user);
}

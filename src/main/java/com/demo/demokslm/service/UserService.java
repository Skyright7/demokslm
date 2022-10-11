package com.demo.demokslm.service;

import com.demo.demokslm.pojo.User;

public interface UserService {
    //查询
    User findUserById(Integer id);
    //添加
    void addUser(User user);
    //修改
    void updateUser(User user);
    //删除
    void deleteUserById(Integer id);
}

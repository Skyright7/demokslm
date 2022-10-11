package com.demo.demokslm.service;

import com.demo.demokslm.dao.UserDao;
import com.demo.demokslm.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User findUserById(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public void addUser(User user) {
        userDao.insert(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateById(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteById(id);
    }
}

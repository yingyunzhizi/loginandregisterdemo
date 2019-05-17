package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.dao.IUserMapper;
import com.qf.entity.User;
import com.qf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @data 5/16/2019 21:57
 * @user yingyunzhizi
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public void registerUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public User findUserEmailByName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",name);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer updatePwById(User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",user.getId());
        return userMapper.update(user,queryWrapper);
    }

    @Override
    public User findUserByName(String username) {
        /*QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        return userMapper.selectOne(queryWrapper);*/
        return userMapper.findUserByUsername(username);
    }
}

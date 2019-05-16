package com.qf.service;

import com.qf.entity.User;

public interface IUserService {

    void registerUser(User user);

    User findUserEmailByName(String name);

    Integer updatePwById(User user);

    User findUserByName(String username);
}

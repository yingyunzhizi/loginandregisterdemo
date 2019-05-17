package com.qf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.User;

public interface IUserMapper extends BaseMapper<User> {

    User findUserByUsername(String username);//这是伪了尝试mybatis的,没有用plus,因为昨晚我没有配置mybatis,所以一直报错,用不了自定义的方法
}

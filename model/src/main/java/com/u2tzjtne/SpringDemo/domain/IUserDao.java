package com.u2tzjtne.SpringDemo.domain;

import java.util.List;

/**
 * 用户持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有用户
     */
    List<User> findAll();

    /**
     * 保存用户
     */
    void addUser(User user);
}

package com.bookstore.service;

import com.bookstore.pojo.User;

import java.util.List;

/**
 * ClassName: UserService
 * Description:
 * date: 2022/1/22 19:34
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登陆
     * @param user
     * @return
     */
    public User login(User user);

    /**
     *
     * @param username
     * @return true：用户名存在，false：用户名不存在
     */
    public boolean checkUserName(String username);
}

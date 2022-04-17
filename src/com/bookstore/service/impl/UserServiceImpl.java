package com.bookstore.service.impl;

import com.bookstore.dao.UserDao;
import com.bookstore.dao.impl.UserDaoImpl;
import com.bookstore.pojo.User;
import com.bookstore.service.UserService;

import java.util.List;

/**
 * ClassName: UserServiceIml
 * Description:
 * date: 2022/1/22 19:40
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean checkUserName(String username) {
        if(userDao.queryUserByUsername(username) == null)
            return false;
        else
            return true;
    }
}

package com.bookstore.dao.impl;

import com.bookstore.dao.UserDao;
import com.bookstore.pojo.User;

import java.util.List;

/**
 * ClassName: UserDaoImpl
 * Description:
 * date: 2022/1/22 18:36
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select id,username,password,email from t_user where username = ?";
        return queryForOne(sql,username);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        return queryForOne(sql,username,password);
    }
}

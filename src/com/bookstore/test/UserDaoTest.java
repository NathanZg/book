package com.bookstore.test;

import com.bookstore.dao.impl.UserDaoImpl;
import com.bookstore.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ClassName: UserDaoTest
 * Description:
 * date: 2022/1/22 19:21
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class UserDaoTest {
    UserDaoImpl userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        System.out.println(userDao.queryUserByUsername("admin"));
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"sb","123456","ada@qq.com")));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        System.out.println(userDao.queryUserByUsernameAndPassword("admin","admin"));
    }
}
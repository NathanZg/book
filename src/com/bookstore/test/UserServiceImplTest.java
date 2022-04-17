package com.bookstore.test;

import com.bookstore.pojo.User;
import com.bookstore.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ClassName: UserServiceImplTest
 * Description:
 * date: 2022/1/22 19:54
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class UserServiceImplTest {

    private UserServiceImpl userService = new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"fuck","fuck","email@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "fuck", "fuck", "email@qq.com")));
    }

    @Test
    public void checkUserName() {
        System.out.println(userService.checkUserName("fuck"));
    }
}
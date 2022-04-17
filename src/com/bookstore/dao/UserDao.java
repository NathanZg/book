package com.bookstore.dao;
import com.bookstore.pojo.User;



import java.util.List;

/**
 * ClassName: UserDao
 * Description:
 * date: 2022/1/22 18:28
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 返回null则无此用户
     */
    public User queryUserByUsername(String username);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);

    /**
     * 通过账号密码查询用户信息
     * @param username
     * @param password
     * @return 返回null则无
     */
    public User queryUserByUsernameAndPassword(String username, String password);


}

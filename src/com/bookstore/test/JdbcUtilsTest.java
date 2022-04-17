package com.bookstore.test;

import com.bookstore.utils.JdbcUtils;
import org.junit.Test;

/**
 * ClassName: JdbcUtilsTest
 * Description:
 * date: 2022/1/22 17:14
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class JdbcUtilsTest {

    @Test
    public void test1(){
        System.out.println(JdbcUtils.getConnection());
    }
}

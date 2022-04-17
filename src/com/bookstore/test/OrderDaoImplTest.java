package com.bookstore.test;

import com.bookstore.dao.OrderDao;
import com.bookstore.dao.impl.OrderDaoImpl;
import com.bookstore.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * ClassName: OrderDaoImplTest
 * Description:
 * date: 2022/2/3 16:27
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class OrderDaoImplTest {
    OrderDaoImpl orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {

        orderDao.saveOrder(new Order("1234562",new Date(),new BigDecimal(190),0,1));
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDao.queryOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus(){
        orderDao.changeOrderStatus("12345", 1);
    }

    @Test
    public void queryOrderByUserId(){
        List<Order> orders = orderDao.queryOrderByUserId(1);
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
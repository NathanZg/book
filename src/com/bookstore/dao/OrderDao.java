package com.bookstore.dao;

import com.bookstore.pojo.Order;
import com.bookstore.pojo.OrderItem;

import java.util.List;

/**
 * ClassName: OrderDao
 * Description:
 * date: 2022/2/3 16:07
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public interface OrderDao {

    public int saveOrder(Order order);

    public List<Order> queryOrders();

    public int changeOrderStatus(String orderId,Integer status);

    public List<Order> queryOrderByUserId(Integer userId);

}

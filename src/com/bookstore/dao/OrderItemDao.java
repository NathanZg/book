package com.bookstore.dao;

import com.bookstore.pojo.OrderItem;

import java.util.List;

/**
 * ClassName: OrderItemDao
 * Description:
 * date: 2022/2/3 16:07
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public interface OrderItemDao {

    public int saveOrderItem(OrderItem orderItem);

    public List<OrderItem> queryOrderItemByOrderId(String orderId);
}

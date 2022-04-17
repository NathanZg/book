package com.bookstore.service;

import com.bookstore.pojo.Cart;
import com.bookstore.pojo.Order;
import com.bookstore.pojo.OrderItem;

import java.util.List;

/**
 * ClassName: OrderService
 * Description:
 * date: 2022/2/3 17:20
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public interface OrderService {

    public String createOrder(Cart cart,Integer userId);

    public List<Order> showAllOrders();

    public void sendOrder(String orderId);

    public List<OrderItem> showOrderDetail(String orderId);

    public List<Order> showMyOrders(Integer userId);

    public void receiverOrder(String orderId);
}

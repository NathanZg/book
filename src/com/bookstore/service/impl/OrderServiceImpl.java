package com.bookstore.service.impl;

import com.bookstore.dao.impl.BookDaoImpl;
import com.bookstore.dao.impl.OrderDaoImpl;
import com.bookstore.dao.impl.OrderItemDaoImpl;
import com.bookstore.pojo.*;
import com.bookstore.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName: OrderServiceImpl
 * Description:
 * date: 2022/2/3 17:26
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class OrderServiceImpl implements OrderService {

    private OrderDaoImpl orderDao = new OrderDaoImpl();
    private OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
    private BookDaoImpl bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //生成订单号
        String orderId = System.currentTimeMillis()+""+userId;
        //生成订单对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //保存订单
        orderDao.saveOrder(order);
        //保存订单项
        for(Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotoalPrice(),orderId);
            orderItemDao.saveOrderItem(orderItem);

            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, 1);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    @Override
    public void receiverOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, 2);
    }
}

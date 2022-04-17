package com.bookstore.test;

import com.bookstore.pojo.Cart;
import com.bookstore.pojo.CartItem;
import com.bookstore.pojo.Order;
import com.bookstore.pojo.OrderItem;
import com.bookstore.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * ClassName: OrderServiceImplTest
 * Description:
 * date: 2022/2/3 17:38
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class OrderServiceImplTest {

    private OrderServiceImpl orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(1,"java",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(2,"python",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(2,"python",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(2,"python",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(3,"c++",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(3,"c++",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(3,"c++",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(3,"c++",1,new BigDecimal(50),new BigDecimal(50)));
        String number = orderService.createOrder(cart, 1);
        System.out.println("订单号："+number);
    }

    @Test
    public void showAllOrders() {
        List<Order> orders = orderService.showAllOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("16438830547791");
        for (Order showMyOrder : orderService.showMyOrders(1)) {
            System.out.println(showMyOrder);
        }

    }

    @Test
    public void showOrderDetail() {
        List<OrderItem> orderItems = orderService.showOrderDetail("16438830547791");
        System.out.println(orderItems);
    }

    @Test
    public void showMyOrders() {
        for (Order showMyOrder : orderService.showMyOrders(1)) {
            System.out.println(showMyOrder);
        }

    }

    @Test
    public void receiverOrder() {
        orderService.receiverOrder("16438830547791");
        for (Order showMyOrder : orderService.showMyOrders(1)) {
            System.out.println(showMyOrder);
        }
    }
}
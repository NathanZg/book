package com.bookstore.test;

import com.bookstore.dao.impl.OrderItemDaoImpl;
import com.bookstore.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * ClassName: OrderItemDaoImplTest
 * Description:
 * date: 2022/2/3 16:34
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class OrderItemDaoImplTest {

    OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到放弃",1,new BigDecimal(12),new BigDecimal(12),"1234567890"));
    }

    @Test
    public void queryOrderItemByOrderId(){
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId("1234567890");
        System.out.println(orderItems);
    }
}
package com.bookstore.dao.impl;

import com.bookstore.dao.OrderDao;
import com.bookstore.pojo.Order;
import com.bookstore.pojo.OrderItem;

import java.util.List;

/**
 * ClassName: OrderDaoImpl
 * Description:
 * date: 2022/2/3 16:10
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {


    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(order_id,create_time,price,`status`,user_id) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select order_id orderId,create_time createTime,price,`status`,user_id userId from t_order";
        return queryForList(sql);
    }

    @Override
    public int changeOrderStatus(String orderId, Integer status) {
        String sql = "update t_order set `status` = ? where order_id = ?";
        return update(sql, status,orderId);
    }

    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        String sql = "select order_id orderId,create_time createTime,price,`status`,user_id userId from t_order where user_id = ?";
        return queryForList(sql, userId);
    }
}

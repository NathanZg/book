package com.bookstore.dao.impl;

import com.bookstore.dao.OrderItemDao;
import com.bookstore.pojo.OrderItem;

import java.util.List;

/**
 * ClassName: OrderItemDaoImpl
 * Description:
 * date: 2022/2/3 16:19
 *
 * @author Ekertree
 * @since JDK 1.8
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(name,count,price,`total_price`,order_id) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "select id,name,count,price,total_price totalPrice,order_id orderId from t_order_item where order_id = ?";
        return  queryForList(sql, orderId);
    }
}

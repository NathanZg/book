package com.bookstore.web; /**
 * ClassName: ${NAME}
 * Description:
 * date: 2022/2/3 17:45
 *
 * @author Ekertree
 * @version
 * @since JDK 1.8
 */

import com.bookstore.pojo.Cart;
import com.bookstore.pojo.Order;
import com.bookstore.pojo.OrderItem;
import com.bookstore.pojo.User;
import com.bookstore.service.impl.OrderServiceImpl;
import com.bookstore.utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    private OrderServiceImpl orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //获取userId
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        Integer userId = user.getId();
        String orderId = orderService.createOrder(cart, userId);
        request.getSession().setAttribute("orderId", orderId);
        //重定向
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
    }


    protected void showMyOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取userId
        int userId = Integer.parseInt(request.getParameter("userId"));
        //获取订单
        List<Order> myOrders = orderService.showMyOrders(userId);
        //存入reque域
        request.setAttribute("myOrder",myOrders);
        //请求转发
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
    }

    protected void showOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取orderId
        String orderId = request.getParameter("orderId");
        //获取订单
        List<OrderItem> orderDetails = null;
        try {
            orderDetails = orderService.showOrderDetail(orderId);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollBackAndClose();
            e.printStackTrace();
        }
        //存入request域
        request.setAttribute("orderDetails",orderDetails);
        //请求转发
        request.getRequestDispatcher("/pages/order/orderDetail.jsp").forward(request, response);
    }

    protected void showAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取所有订单
        List<Order> orders = orderService.showAllOrders();
        //存入reque域
        request.setAttribute("orders",orders);
        //请求转发
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
    }

    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取订单号
        String orderId = request.getParameter("orderId");
        //发货
        orderService.sendOrder(orderId);
        //重定向
        response.sendRedirect(request.getContextPath()+"/orderServlet?action=showAllOrders");
    }

}

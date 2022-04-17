package com.bookstore.web; /**
 * ClassName: ${NAME}
 * Description:
 * date: 2022/2/2 18:59
 *
 * @author Ekertree
 * @version
 * @since JDK 1.8
 */

import com.bookstore.pojo.Book;
import com.bookstore.pojo.Cart;
import com.bookstore.pojo.CartItem;
import com.bookstore.service.BookService;
import com.bookstore.service.impl.BookServiceImpl;
import com.bookstore.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //调用bookservice获取图书信息
        Book book = bookService.queryBookById(id);
        //把图书信息转换成商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用cart addItem
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        request.getSession().setAttribute("lastBookName", cartItem.getName());
        //重定向回商品列表
        response.sendRedirect(request.getContextPath()+"/client/bookServlet?action=page&pageNum="+request.getParameter("pageNum"));
    }


    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.deleteItem(id);
            //重定向
            response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
        }
    }

    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.clear();
            //重定向
            response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
        }
    }

    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 1);
        //获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart!=null){
            cart.updateCount(id, count);
            //重定向
            response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
        }
    }

    protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //调用bookservice获取图书信息
        Book book = bookService.queryBookById(id);
        //把图书信息转换成商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用cart addItem
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //存入session
        request.getSession().setAttribute("lastBookName", cartItem.getName());
        //返回购物车总的商品数和最后一个书的名称
        HashMap<String, Object> map = new HashMap<>();
        map.put("totalCount", cart.getTotalCount());
        map.put("lastBookName", cartItem.getName());
        Gson gson = new Gson();
        String json = gson.toJson(map);
        response.getWriter().write(json);
    }
}

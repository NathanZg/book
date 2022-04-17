package com.bookstore.web; /**
 * ClassName: ${NAME}
 * Description:
 * date: 2022/1/29 17:09
 *
 * @author Ekertree
 * @version
 * @since JDK 1.8
 */

import com.bookstore.pojo.Book;
import com.bookstore.pojo.Page;
import com.bookstore.pojo.User;
import com.bookstore.service.BookService;
import com.bookstore.service.impl.BookServiceImpl;
import com.bookstore.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数，封装成book对象
        Book book = WebUtils.copyParamToBeam(request.getParameterMap(), new Book());
        int pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1)+1;
        //2.调用bookService addBook（）保存图书
        bookService.addBook(book);
        //3.跳转图书列表
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNum="+pageNum);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数id，图书编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        //2.调用bookService
        bookService.deleteBookById(id);
        //3.重定向回到图书管理页面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNum="+pageNum);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数
        Book book = WebUtils.copyParamToBeam(request.getParameterMap(), new Book());
        int pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        //2.调用bookService修改图书
        if((book.getPrice().compareTo(new BigDecimal(0)) == -1)){
            book.setPrice(new BigDecimal(50));
        }
        if(book.getSales() < 0){
            book.setSales(0);
        }
        if(book.getStock() < 0){
            book.setStock(0);
        }
        bookService.updateBook(book);
        //3.重定向回图书管理页面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNum="+pageNum);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过bookservice查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到request域中
        request.setAttribute("books",books);
        //3.请求转发到/pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取图书编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //2.调用bookservice queryBookById
        Book book = bookService.queryBookById(id);
        //3.保存图书到request域中
        request.setAttribute("book",book);
        //4.请求转发
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数pageNum pageSize
        int pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用bookService page方法 获得page对象
        Page<Book> page = bookService.page(pageNum, pageSize);
        //设置分页条访问地址
        page.setUrl("manager/bookServlet?action=page");
        //3.保存page对象到request域中
        request.setAttribute("page",page);
        //4.请求转发
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }
}

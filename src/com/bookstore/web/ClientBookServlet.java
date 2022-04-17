package com.bookstore.web; /**
 * ClassName: ${NAME}
 * Description:
 * date: 2022/1/31 16:34
 *
 * @author Ekertree
 * @version
 * @since JDK 1.8
 */

import com.bookstore.pojo.Book;
import com.bookstore.pojo.Page;
import com.bookstore.service.BookService;
import com.bookstore.service.impl.BookServiceImpl;
import com.bookstore.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数pageNum pageSize
        int pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用bookService page方法 获得page对象
        Page<Book> page = bookService.page(pageNum, pageSize);
        //设置分页条访问地址
        page.setUrl("client/bookServlet?action=page");
        //3.保存page对象到request域中
        request.setAttribute("page",page);
        //4.请求转发
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数pageNum pageSize
        int pageNum = WebUtils.parseInt(request.getParameter("pageNum"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);
        //2.调用bookService page方法 获得page对象
        Page<Book> page = bookService.pageByPrice(pageNum, pageSize,min,max);
        //设置分页条访问地址
        StringBuilder url = new StringBuilder("client/bookServlet?action=pageByPrice");
        if(request.getParameter("min")!=null){
            url.append("&min=").append(request.getParameter("min"));
        }
        if(request.getParameter("max")!=null){
            url.append("&max=").append(request.getParameter("max"));
        }
        page.setUrl(url.toString());
        //3.保存page对象到request域中
        request.setAttribute("page",page);
        //4.请求转发
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

}

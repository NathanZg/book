package com.bookstore.web; /**
 * ClassName: ${NAME}
 * Description:
 * date: 2022/1/27 22:34
 *
 * @author Ekertree
 * @version
 * @since JDK 1.8
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //解决响应中文乱码
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        try {
            //获取action业务鉴别字符串，获取相应的业务，方法反射对象
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //调用目标业务方法
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);//把异常抛给filter过滤器
        }
    }
}

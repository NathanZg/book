package com.bookstore.web; /**
 * ClassName: ${NAME}
 * Description:
 * date: 2022/1/23 19:52
 *
 * @author Ekertree
 * @version
 * @since JDK 1.8
 */

import com.bookstore.pojo.User;
import com.bookstore.service.UserService;
import com.bookstore.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

     private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //登陆
        User loginUser = userService.login(new User(null,username,password,null));
        if(loginUser==null){
            request.setAttribute("msg","用户名或者密码错误！");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else{
            request.getSession().setAttribute("user", loginUser);
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }
}

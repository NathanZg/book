package com.bookstore.web; /**
 * ClassName: ${NAME}
 * Description:
 * date: 2022/1/27 15:51
 *
 * @author Ekertree
 * @version
 * @since JDK 1.8
 */

import com.bookstore.pojo.User;
import com.bookstore.service.UserService;
import com.bookstore.service.impl.UserServiceImpl;
import com.bookstore.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = WebUtils.copyParamToBeam(request.getParameterMap(), new User());
        //登陆
        User loginUser = userService.login(user);
        if (loginUser == null) {
            request.setAttribute("msg", "用户名或者密码错误！");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("user", loginUser);
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }
    }

    /**
     * 处理登陆请求
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session中的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        User user = WebUtils.copyParamToBeam(request.getParameterMap(), new User());
        //检查验证码是否正确
        if (token!=null&&token.equalsIgnoreCase(code)) {
            //检查用户名
            if (userService.checkUserName(username)) {
                request.setAttribute("msg", "用户名已存在！");
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                //跳转注册页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else if(username == ""){
                request.setAttribute("msg", "用户名不能为空！");
                request.setAttribute("email", email);
                //跳转注册页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else {
                //保存到数据库
                userService.registUser(user);
                //跳转注册成功页面
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "验证码错误！");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            //跳回注册页面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

    /**
     * 处理注册请求
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //销毁session
        request.getSession().invalidate();
        //重定向到首页
        response.sendRedirect(request.getContextPath());
    }

    protected void ajaxCheckUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取请求参数username
        String username = request.getParameter("username");
        //userservice
        boolean check = userService.checkUserName(username);
        //把返回的结果封装成MAP对象
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("check", check);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        response.getWriter().write(json);
    }
}


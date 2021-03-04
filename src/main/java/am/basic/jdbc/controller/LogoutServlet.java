package am.basic.jdbc.controller;


import am.basic.jdbc.model.User;
import am.basic.jdbc.model.excpetion.ForbiddenException;
import am.basic.jdbc.model.excpetion.NotFoundException;
import am.basic.jdbc.repository.impl.UserRepositoryImpl;
import am.basic.jdbc.service.UserService;
import am.basic.jdbc.service.impl.UserServiceImpl;
import am.basic.jdbc.util.CookieUtil;
import am.basic.jdbc.util.Encryption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {



    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CookieUtil.deleteCookie("username",resp);
        CookieUtil.deleteCookie("password",resp);
        req.getSession().invalidate();
        resp.sendRedirect("/index.jsp");

    }


}

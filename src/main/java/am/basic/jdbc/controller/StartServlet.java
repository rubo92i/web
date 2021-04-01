package am.basic.jdbc.controller;


import am.basic.jdbc.model.User;
import am.basic.jdbc.model.excpetion.ForbiddenException;
import am.basic.jdbc.model.excpetion.NotFoundException;
import am.basic.jdbc.repository.impl.UserRepositoryImpl;
import am.basic.jdbc.service.UserService;
import am.basic.jdbc.service.impl.UserServiceImpl;
import am.basic.jdbc.util.ContextInitializer;
import am.basic.jdbc.util.CookieUtil;
import am.basic.jdbc.util.Encryption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/start")
public class StartServlet extends HttpServlet {

    private final UserService userService = ContextInitializer.applicationContext.getBean(UserService.class);


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username;
        String password ;

        HttpSession session = req.getSession();

        if (session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            username = user.getUsername();
            password = user.getPassword();
        }else {
            username = CookieUtil.getCookieValue("username",req);
            password = Encryption.decrypt(CookieUtil.getCookieValue("password",req));
        }

        try {




            if (username != null && password != null){
                User user = userService.signIn(username, password);

                CookieUtil.setCookieValue("username",username,18000,resp);
                CookieUtil.setCookieValue("password", Encryption.encrypt(password),18000,resp);

                req.getSession().setAttribute("user",user);
                req.getSession().setMaxInactiveInterval(500000);
                req.getRequestDispatcher("/secure/home.jsp").forward(req, resp);
            }else {
                resp.sendRedirect("/index.jsp");
            }


        } catch (NotFoundException exception) {
            req.setAttribute("message", exception.getMessage());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);

        } catch (ForbiddenException e) {
            req.setAttribute("message", "Please verify");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/verification.jsp").forward(req, resp);

        }catch (RuntimeException throwable) {
            req.setAttribute("message", "Something went wrong , please try later");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }


    }


}

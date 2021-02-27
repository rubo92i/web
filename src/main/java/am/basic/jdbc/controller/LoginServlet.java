package am.basic.jdbc.controller;


import am.basic.jdbc.model.User;
import am.basic.jdbc.model.excpetion.NotFoundException;
import am.basic.jdbc.repository.impl.UserRepositoryImpl;
import am.basic.jdbc.service.UserService;
import am.basic.jdbc.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("user-i-name");
        String password = req.getParameter("user-i-pass");

        try {

            User user = userService.signIn(username, password);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/pages/home.jsp").forward(req, resp);

        } catch (NotFoundException exception) {
            req.setAttribute("message", exception.getMessage());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);

        } catch (RuntimeException throwable) {
            req.setAttribute("message", "Something went wrong , please try later");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }


    }


}

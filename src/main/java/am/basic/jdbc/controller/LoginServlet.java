package am.basic.jdbc.controller;


import am.basic.jdbc.model.User;
import am.basic.jdbc.repository.UserRepository;
import am.basic.jdbc.repository.impl.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("user-i-name");
        String password = req.getParameter("user-i-pass");

        UserRepository userRepository = new UserRepositoryImpl();
        User user = userRepository.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
           resp.sendRedirect("/pages/home.html");
        } else {
            resp.getWriter().println("Wrong username or password");
        }
    }


}

//package am.basic.jdbc.servlets;
//
//import am.basic.jdbc.model.User;
//import am.basic.jdbc.model.excpetion.DuplicateDataException;
//import am.basic.jdbc.service.UserService;
//import am.basic.jdbc.util.ContextInitializer;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//@WebServlet("/sign-up")
//public class SignUpServlet extends HttpServlet {
//
//    private final UserService userService = ContextInitializer.applicationContext.getBean(UserService.class);
//
//
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        User user = new User();
//        user.setName(req.getParameter("name"));
//        user.setSurname(req.getParameter("surname"));
//        user.setUsername(req.getParameter("username"));
//        user.setPassword(req.getParameter("password"));
//
//        try {
//            userService.signUp(user);
//            req.setAttribute("message", "You have successfully registered, please verify");
//            req.setAttribute("username", user.getUsername());
//            req.getRequestDispatcher("/verification.jsp").forward(req, resp);
//        } catch (DuplicateDataException duplicateDataException) {
//            req.setAttribute("message", "User with such username already exists");
//            req.getRequestDispatcher("/sign-up.jsp").forward(req, resp);
//        } catch (RuntimeException throwable) {
//            req.setAttribute("message", "Something went wrong , please try later");
//            req.getRequestDispatcher("/index.jsp").forward(req, resp);
//        }
//
//
//    }
//
//
//}

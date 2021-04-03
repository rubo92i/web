//package am.basic.jdbc.servlets;
//
//import am.basic.jdbc.model.excpetion.NotFoundException;
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
//@WebServlet("/resend")
//public class ResendServlet extends HttpServlet {
//
//    private final UserService userService = ContextInitializer.applicationContext.getBean(UserService.class);
//
//
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//
//        String username = req.getParameter("username");
//
//
//        try {
//            userService.resend(username);
//            req.setAttribute("message", "Code was sent to your email, please check it");
//            req.setAttribute("username", username);
//            req.getRequestDispatcher("/verification.jsp").forward(req, resp);
//        } catch (NotFoundException notFoundException) {
//            req.setAttribute("message", "Please register for first");
//            req.getRequestDispatcher("/index.jsp").forward(req, resp);
//        } catch (RuntimeException throwable) {
//            req.setAttribute("message", "Something went wrong , please try later");
//            req.setAttribute("username", username);
//            req.getRequestDispatcher("/verification.jsp").forward(req, resp);
//        }
//
//
//    }
//
//
//}

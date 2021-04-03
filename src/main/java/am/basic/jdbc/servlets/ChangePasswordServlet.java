//package am.basic.jdbc.servlets;
//
//import am.basic.jdbc.model.User;
//import am.basic.jdbc.model.excpetion.ForbiddenException;
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
//@WebServlet("/secure/change-password")
//public class ChangePasswordServlet extends HttpServlet {
//
//    private final UserService userService = ContextInitializer.applicationContext.getBean(UserService.class);
//
//
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//
//        String newPassword = req.getParameter("newPassword");
//        String oldPassword = req.getParameter("oldPassword");
//
//
//        try {
//            User user = (User) req.getSession().getAttribute("user");
//
//            userService.changePassword(user.getUsername(), oldPassword, newPassword);
//            req.setAttribute("message", "You have successfully changed password ");
//            req.getRequestDispatcher("/secure/change-password.jsp").forward(req, resp);
//
//        } catch (NotFoundException notFoundException) {
//            req.setAttribute("message", "Please register for first");
//            req.getRequestDispatcher("/index.jsp").forward(req, resp);
//        } catch (ForbiddenException e) {
//            req.setAttribute("message", "Wrong old password");
//            req.getRequestDispatcher("/secure/change-password.jsp").forward(req, resp);
//        } catch (RuntimeException throwable) {
//            req.setAttribute("message", "Something went wrong , please try later");
//            req.getRequestDispatcher("/secure/change-password.jsp").forward(req, resp);
//        }
//
//
//    }
//
//
//}

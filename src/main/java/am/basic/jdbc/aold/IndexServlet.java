package am.basic.jdbc.aold;

import am.basic.jdbc.model.User;
import am.basic.jdbc.repository.UserRepository;
import am.basic.jdbc.repository.impl.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       resp.getWriter().println("<html lang=\"en\">\n" +
               "<head>\n" +
               "    <meta charset=\"UTF-8\">\n" +
               "    <title>Title</title>\n" +
               "</head>\n" +
               "<body>\n" +
               "<br/>");

        String message = (String) req.getAttribute("message");
        if (message != null && !message.isEmpty()) {
            resp.getWriter().write(message);
        }
        resp.getWriter().println("<form action=\"/login\" method=\"post\">\n" +
                "    Username <input type=\"text\" name=\"user-i-name\"> <br/>\n" +
                "    Password <input type=\"password\" name=\"user-i-pass\"><br/>\n" +
                "    <input type=\"submit\" name=\"Sign in\">\n" +
                "</form>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n");


    }




}

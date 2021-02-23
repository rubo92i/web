package am.basic.jdbc.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // useri redirect a anum nshvac urli vra, url@ kara lini te html ej te mek ayl servlet
        //resp.sendRedirect("/pages/a.html");
        resp.sendRedirect("/pages/a.html");


    }


}

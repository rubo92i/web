package am.basic.jdbc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/secure/*")
public class AuthenticationFilter implements Filter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (request.getSession().getAttribute("user") == null) {
            request.setAttribute("message","Your session is expired, please sign in");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        } else {
            chain.doFilter(request, response);
        }
    }
}

package am.basic.jdbc.controller;


import am.basic.jdbc.model.Comment;
import am.basic.jdbc.model.User;
import am.basic.jdbc.repository.impl.CommentRepositoryImpl;
import am.basic.jdbc.service.CommentService;
import am.basic.jdbc.service.impl.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/secure/comments")
public class CommentServlet extends HttpServlet {

    private final CommentService commentService = new CommentServiceImpl(new CommentRepositoryImpl());


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {

            User user = (User) req.getSession().getAttribute("user");

            List<Comment> comments = commentService.getByUserId(user.getId());
            req.setAttribute("comments", comments);
            req.getRequestDispatcher("/secure/comments.jsp").forward(req, resp);


        } catch (RuntimeException throwable) {
            req.setAttribute("message", "Something went wrong , please try later");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }


    }


}
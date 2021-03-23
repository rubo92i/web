package am.basic.jdbc.service.impl;

import am.basic.jdbc.model.Comment;
import am.basic.jdbc.repository.CommentRepository;
import am.basic.jdbc.service.CommentService;
import lombok.Data;

import java.util.List;

@Data
public class CommentServiceImpl implements CommentService {


    private CommentRepository commentRepository;




    @Override
    public List<Comment> getByUserId(long id) {
        return commentRepository.getByUserId(id);
    }

    @Override
    public void add(Comment comment) {
        commentRepository.add(comment);
    }

    @Override
    public void deleteByUser(long commentId, long userId) {
        commentRepository.deleteByIdAndUserId(commentId,userId);
    }
}

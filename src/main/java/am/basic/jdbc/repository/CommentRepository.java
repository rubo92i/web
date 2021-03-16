package am.basic.jdbc.repository;

import am.basic.jdbc.model.*;

import java.util.List;

public interface CommentRepository {

    void add(Comment comment);

    void update(Comment comment);

    void delete(long id);

    List<Comment> getByUserId(long userId);

    List<Comment> getAll();

    void deleteByIdAndUserId(long commentId, long userId);

}

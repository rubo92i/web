package am.basic.jdbc.mapper;

import am.basic.jdbc.model.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<Comment> {


    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setId(resultSet.getLong("id"));
        comment.setTitle(resultSet.getString("title"));
        comment.setContent(resultSet.getString("content"));
        comment.setUserId(resultSet.getLong("user_id"));
        return comment;
    }
}

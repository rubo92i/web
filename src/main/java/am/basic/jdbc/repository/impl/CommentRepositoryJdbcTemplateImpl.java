package am.basic.jdbc.repository.impl;

import am.basic.jdbc.mapper.UserMapper;
import am.basic.jdbc.model.Comment;
import am.basic.jdbc.repository.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Repository
@AllArgsConstructor
public class CommentRepositoryJdbcTemplateImpl implements CommentRepository {


    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public void add(Comment comment) {
        jdbcTemplate.update("INSERT INTO comment VALUES(0,?,?,?)",
                comment.getContent(), comment.getTitle(), comment.getUserId());
    }

    @Override
    public void update(Comment comment) {
        jdbcTemplate.update("UPDATE comment set title = ? ,content = ?, user_id = ?  where id = ? ",
                comment.getContent(), comment.getTitle(), comment.getUserId(), comment.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE  FROM comment  where id = ? ",
                id);
    }

    @Override
    public List<Comment> getByUserId(long userId) {
        return namedParameterJdbcTemplate
                .queryForStream(
                        "SELECT * FROM comment WHERE user_id = :paramUserId",
                        Collections.singletonMap("paramUserId", userId),
                        new UserMapper())
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> getAll() {
        return jdbcTemplate
                .queryForStream("SELECT * FROM comment ", new UserMapper())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByIdAndUserId(long commentId, long userId) {
        jdbcTemplate.update("DELETE  FROM comment  where id = ? and user_id = ?",
                commentId, userId);
//        namedParameterJdbcTemplate.update(
//                "DELETE  FROM comment  where id = :commentId and user_id = :userId",
//                Map.of("commentId", commentId, "userId", userId));
    }
}

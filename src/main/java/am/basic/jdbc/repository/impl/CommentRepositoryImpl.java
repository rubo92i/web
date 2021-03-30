package am.basic.jdbc.repository.impl;

import am.basic.jdbc.model.Comment;
import am.basic.jdbc.repository.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@Repository
@AllArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {




    public void onInit(){
        System.out.println("CommentRepositoryImpl initialized");
    }


    public void onDestroy(){
        System.out.println("CommentRepositoryImpl initialized");
    }


    private final DataSource dataSource;

    @Override
    public void add(Comment comment) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = null;

            pstmt = connection.prepareStatement("INSERT INTO comment(title,content,user_id) values(?,?,?) ");


            pstmt.setString(1, comment.getTitle());
            pstmt.setString(2, comment.getContent());
            pstmt.setLong(3, comment.getUserId());
            int result = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Comment comment) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE comment set title = ? ,content = ?, user_id = ?  where id = ? ");

            pstmt.setString(1, comment.getTitle());
            pstmt.setString(2, comment.getContent());
            pstmt.setLong(3, comment.getUserId());
            pstmt.setLong(4, comment.getId());
            int result = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE  FROM comment  where id = ?");
            pstmt.setLong(1, id);

            int result = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

    }


    @Override
    public List<Comment> getByUserId(long userId) {
        List<Comment> comments = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM comment WHERE user_id = ?");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                comments.add(map(resultSet));
            }


            resultSet.close();
            preparedStatement.close();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        return comments;
    }


    @Override
    public List<Comment> getAll() {
        List<Comment> comments = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM comment");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                comments.add(map(resultSet));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return comments;
    }

    @Override
    public void deleteByIdAndUserId(long commentId, long userId) {

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE  FROM comment  where id = ? and user_id = ?");
            pstmt.setLong(1, commentId);
            pstmt.setLong(2, userId);
            int result = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }


    private Comment map(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        comment.setId(resultSet.getLong("id"));
        comment.setTitle(resultSet.getString("title"));
        comment.setContent(resultSet.getString("content"));
        comment.setUserId(resultSet.getLong("user_id"));
        return comment;
    }


}




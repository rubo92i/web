package am.basic.jdbc.repository.impl;

import am.basic.jdbc.model.User;
import am.basic.jdbc.repository.UserRepository;
import am.basic.jdbc.util.DataSource;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class UserRepositoryImpl implements UserRepository {


    @Override
    public void add(User user) {
        try {
            Connection connection = DataSource.getConnection();
            PreparedStatement pstmt = null;

            pstmt = connection.prepareStatement("INSERT INTO user(name,surname,username,password,status,code) values(?,?,?,?,?,?) ");


            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getSurname());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getStatus());
            pstmt.setString(6, user.getCode());
            int result = pstmt.executeUpdate();

            log.error("query was executed {} rows were affected", result);
            pstmt.close();
        } catch (SQLException exception) {
            log.error("Add user failed with reason {}", exception.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try {
            Connection connection = DataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE user set name = ? ,surname = ?, username = ?, password = ? ,status = ? ,code = ? where id = ? ");

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getSurname());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getStatus());
            pstmt.setString(6,user.getCode());
            pstmt.setLong(7, user.getId());
            int result = pstmt.executeUpdate();

            log.error("query was executed {} rows were affected", result);
            pstmt.close();
        } catch (SQLException exception) {
            log.error("Add user failed with reason {}", exception.getMessage());
        }
    }

    @Override
    public void delete(long id) {

        try {
            Connection connection = DataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE  FROM user  where id = ?");
            pstmt.setLong(1, id);

            int result = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

    }

    @Override
    public User getById(long id) {
        User user = null;
        try {
            Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = map(resultSet);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return user;
    }

    @Override
    public User getByUsername(String username) {
        User user = null;
        try {
            Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = map(resultSet);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        return user;
    }


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(map(resultSet));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return users;
    }


    private User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setStatus(resultSet.getInt("status"));
        user.setCode(resultSet.getString("code"));
        return user;
    }
}

package am.basic.jdbc;

import am.basic.jdbc.model.User;
import am.basic.jdbc.repository.UserRepository;
import am.basic.jdbc.repository.impl.UserRepositoryImpl;
import am.basic.jdbc.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException, InterruptedException {
        UserRepository userRepository = new UserRepositoryImpl();
        User user = userRepository.getByUsername("ruben.manukyan");
        // System.out.println(user);
        user.setPassword("newPassword");
        userRepository.update(user);

        user = userRepository.getByUsername("ruben.manukyan");

        Connection connection = DataSource.getConnection();
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        connection.setReadOnly(true);
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user set name = 'Karen' WHERE id = 2");

            preparedStatement.executeUpdate();
            System.out.println("Query was executed");


            preparedStatement = connection.prepareStatement("UPDATE user set name = 'Chxangarox' WHERE id = 1 ");

            preparedStatement.executeUpdate();


            connection.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
            connection.rollback();
        }



         //  System.out.println(user);

        // System.out.println("Hello world");
    }
}

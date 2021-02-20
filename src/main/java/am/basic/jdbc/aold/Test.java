package am.basic.jdbc.aold;

import am.basic.jdbc.util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test {


    public static void updateUser(String name, String username, String surname, String password, int id) throws SQLException {
        Connection connection = DataSource.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("UPDATE user set name = ? ,surname = ?, username = ?, password = ? where id = ? ");

        pstmt.setString(1, name);
        pstmt.setString(2, surname);
        pstmt.setString(3, username);
        pstmt.setString(4, password);
        pstmt.setInt(5, id);
        int result = pstmt.executeUpdate();

        System.out.println("query was executed " + result + " rows were affected");
        pstmt.close();
    }
}

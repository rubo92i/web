package am.basic.jdbc.aold;

import java.sql.*;

public class Main {


    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.am.basic.jdbc.Driver");
        Connection connection = DriverManager.getConnection("am.basic.jdbc:mysql://localhost:3306/test", "root", "");


        updateSample(connection, "karen.darbinyan", 2);

        selectSample(connection);

        insertSample(connection, "Asya", "Khachatryan", "asya.khachatryan", "password");

        connection.close();

    }


    public static void selectSample(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {//iterator,  returns true if result has more rows
            System.out.print(resultSet.getInt("id") + " ");  //reading data using table column names
            System.out.print(resultSet.getString("name") + " ");
            System.out.print(resultSet.getString("surname") + " ");
            System.out.print(resultSet.getString("username") + " ");
            System.out.print(resultSet.getString("password") + " ");
            System.out.println();

        }

        resultSet.close();
        preparedStatement.close();
    }



    public static void updateSample(Connection connection, String username, int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("UPDATE user SET username = ? where id = ?");

        pstmt.setString(1, username);
        pstmt.setInt(2, id);

        int result = pstmt.executeUpdate();

        System.out.println("query was executed " + result + " rows were affected");
        pstmt.close();
    }



    public static void deleteSample(Connection connection, int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("DELETE  FROM user  where id = ?");
        pstmt.setInt(1, id);

        int result = pstmt.executeUpdate();

        System.out.println("query was executed " + result + " rows were affected");
        pstmt.close();
    }



    public static void insertSample(Connection connection, String name, String surname, String username, String password) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO user(name,surname,username,password) values(?,?,?,?) ");

        pstmt.setString(1, name);
        pstmt.setString(2, surname);
        pstmt.setString(3, username);
        pstmt.setString(4, password);

        int result = pstmt.executeUpdate();

        System.out.println("query was executed " + result + " rows were affected");
        pstmt.close();
    }
}

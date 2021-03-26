package am.basic.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSource  {


    private static Connection connection;


    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

            }


        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return connection;
    }
}

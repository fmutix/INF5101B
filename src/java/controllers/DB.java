package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection getConnection() 
        throws ClassNotFoundException, SQLException 
    {
        Class.forName("org.apache.derby.jdbc.ClientDriver");

        return DriverManager.getConnection(
            "jdbc:derby://localhost:1527/hotline",
            "test", "test"
        );
    }
}

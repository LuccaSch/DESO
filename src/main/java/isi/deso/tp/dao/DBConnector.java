package isi.deso.tp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static String user = "root";
    private static String pass = "rootpassword";
    private static String url = "jdbc:mysql://localhost:3306";
    private static String database = "exampledb";

    public static Connection instance;

    public static Connection getInstance() {
        if (instance == null) {

            try {
                instance = DriverManager.getConnection(url + "/" + database, user, pass);
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getLocalizedMessage());
            }
        }
        return instance;
    }

}

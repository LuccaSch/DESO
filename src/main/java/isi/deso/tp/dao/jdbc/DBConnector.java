package isi.deso.tp.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static String user = "user";
    private static String pass = "userpassword";
    private static String url = "jdbc:mysql://localhost:3306";
    private static String database = "exampledb";

    public static Connection instance;

    public static Connection getInstance() throws SQLException {
        if (instance == null || instance.isClosed()) {
            // Crear una nueva conexión si no existe o está cerrada
            instance = DriverManager.getConnection(url + "/" + database, user, pass);
        }
        return instance;
    }
}

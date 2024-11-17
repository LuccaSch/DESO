package isi.deso.tp.dao.jdbc;

import java.sql.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ClienteDatabaseTest {

    @Test
    public void testCuitUnico() {
        String url = "jdbc:mysql://localhost:3306/exampledb";
        String usuario = "user";
        String contrasena = "userpassword";

        String query = "SELECT cuit, COUNT(*) FROM Cliente GROUP BY cuit HAVING COUNT(*) > 1";

        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            int duplicados = 0;
            while (rs.next()) {
                duplicados++;
            }

            // Aseguramos que no haya duplicados
            assertEquals(0, duplicados, "Existen CUITs duplicados en la base de datos");

        } catch (SQLException e) {
        }
    }
}

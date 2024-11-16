package isi.deso.tp.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import isi.deso.tp.model.Coordenada;

public class CoordenadaJDBC {

    public static Coordenada obtenerCoordenadaById(Integer id) {
        String query = "SELECT lat, lgn FROM Coordenada WHERE id = ?";
        try (Connection conn = DBConnector.getInstance();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Double lat = rs.getDouble("lat");
                    Double lgn = rs.getDouble("lgn");
                    return new Coordenada(lat, lgn);
                } else {
                    throw new SQLException("No se encontró la coordenada con ID: " + id);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoordenadaJDBC.class.getName()).log(Level.SEVERE, "Error obteniendo coordenada", ex);
            return null;
        }
    }

    public static Integer guardarCoordenada(Coordenada coordenada) {
        String query = "INSERT INTO Coordenada (lat, lgn) VALUES (?, ?) RETURNING id;";
        try (Connection conn = DBConnector.getInstance();
             PreparedStatement ps = conn.prepareStatement(query)) {
    
            ps.setDouble(1, coordenada.getLat());
            ps.setDouble(2, coordenada.getLgn());
    
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("No se pudo insertar la coordenada");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoordenadaJDBC.class.getName())
                  .log(Level.SEVERE, "Error al guardar la coordenada", ex);
            return null;
        }
    }
}

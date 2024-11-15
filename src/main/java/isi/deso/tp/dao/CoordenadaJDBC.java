package isi.deso.tp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import isi.deso.tp.model.Cliente;
import isi.deso.tp.model.Coordenada;

public class CoordenadaJDBC {

    public static Coordenada obtenerCoordenadaById(Integer id){
        List<Coordenada> listaCoordenadas = new ArrayList<>();
        Connection conn = DBConnector.getInstance();
        String query = "SELECT * FROM Coordenada c WHERE c.id="+id+";";
        try (Statement stm = conn.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
            Double lat = rs.getDouble("lat");
            Double lgn = rs.getDouble("lgn");
            Coordenada coord=new Coordenada(lat,lgn);
            listaCoordenadas.add(coord);
            }
            
        }
        catch (SQLException ex) {
            Logger.getLogger(ClienteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCoordenadas.;
    }
}

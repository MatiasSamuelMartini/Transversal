
package Persistencia;

import Modelo.Conexion;
import Modelo.Cursada;
import java.sql.*;
import javax.swing.JOptionPane;


public class InscripcionData {
    private Connection con = null;

    public InscripcionData() {
        con = Conexion.getConexion();
    }
    
    public void guardarInscripcion(Cursada cursada){
        String sql = "INSERT INTO cursada (idAlumno, idMateria, nota, asist, cursa) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cursada.getAlumno().getIdAlumno());
            ps.setInt(2, cursada.getMateria().getIdMateria());
            ps.setDouble(3, cursada.getNota());
            ps.setDouble(4, cursada.getAsist());
            ps.setDate(5, Date.valueOf(cursada.getCursa()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                cursada.setIdCursada(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Alumno añadido con exito.");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno" + e.getMessage());
        }
    }
    
    public void anularInscripcion(int id){
        String sql = "DELETE FROM cursada WHERE cursada.idCursada = ?";
        
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int validation = ps.executeUpdate();

            if (validation == 1) {
                JOptionPane.showMessageDialog(null, "Se elimino esa inscripcion!");
            } else {
                JOptionPane.showMessageDialog(null, "Esa inscripcion no existe.");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL." + ex);
        }
    }
}

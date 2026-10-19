//ALTER TABLE alumno AUTO_INCREMENT = 1;
//Volver el autoincrement a 1
package Persistencia;

import Modelo.Alumno;
import Modelo.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AlumnoData {

    private Connection con = null;

    public AlumnoData() {
        con = Conexion.getConexion();
    }

    public void guardarAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumno (dni, nombre, apellido , fechaNac, activo ) VALUES ( ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getApellido());
            ps.setDate(4, Date.valueOf(alumno.getFechaNac()));
            ps.setBoolean(5, alumno.isActivo());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alumno.setIdAlumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Alumno añadido con exito.");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno" + e.getMessage());
        }
    }

    public void borrarAlumno(int dni) {
//        String sql = "DELETE FROM alumno WHERE dni = ?";
          String sql = "UPDATE alumno SET activo = '0' WHERE alumno.dni = ? ";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dni);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Se eliminó ese alumno!");
            } else {
                JOptionPane.showMessageDialog(null, "Ese alumno no existe.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + ex);
        }
    }

    public Alumno buscarAlumno(int dni) {
        Alumno alumno = null;

        String sql = "SELECT * FROM alumno WHERE dni = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    alumno = new Alumno();
                    alumno.setIdAlumno(rs.getInt("idAlumno"));
                    alumno.setDni(rs.getInt("dni"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setApellido(rs.getString("apellido"));
                    alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                    alumno.setActivo(rs.getBoolean("activo"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + ex);
        }
        return alumno;
    }

    public List<Alumno> listarAlumno() {
        Alumno alumno = null;
        String sql = "SELECT * FROM alumno";
        ArrayList<Alumno> alumnos = new ArrayList<>();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                alumno.setActivo(rs.getBoolean("activo"));
                alumnos.add(alumno);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL" + ex);
        }
        return alumnos;

    }

    public void actualizarAlumno(Alumno alumno) {
        String sql = "UPDATE alumno SET dni = ?, nombre = ?, apellido = ?, fechaNac = ?, activo = ? "
                + "WHERE idAlumno = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getApellido());
            ps.setDate(4, Date.valueOf(alumno.getFechaNac()));
            ps.setBoolean(5, alumno.isActivo());
            ps.setInt(6, alumno.getIdAlumno());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Alumno actualizado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el alumno a actualizar.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno: " + e.getMessage());
        }
    }
}

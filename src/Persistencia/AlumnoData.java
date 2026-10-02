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
        String sql = "INSERT INTO alumno (dni, nombre, fechaNac, activo ) VALUES ( ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
//            ps.setString(2, alumno.getApellido());
            ps.setString(2, alumno.getNombre());
            ps.setDate(3, Date.valueOf(alumno.getFechaNac()));
            ps.setBoolean(4, alumno.isActivo());
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

//    public void borrarAlumno(int id) {
//        String sql = "UPDATE 'alumno' SET 'estado' = 0 WHERE idAlumno = ?";
//
//        PreparedStatement ps;
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, id);
//            int validation = ps.executeUpdate();
//
//            if (validation == 1) {
//                JOptionPane.showMessageDialog(null, "Se elimino ese alumno!");
//            } else {
//                JOptionPane.showMessageDialog(null, "Ese alumno no existe.");
//            }
//            ps.close();
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error SQL." + ex);
//        }
//    }
//
    public Alumno buscarAlumno(int id) {
        Alumno alumno = null;

        String sql = "SELECT * FROM alumno WHERE idAlumno = 7 AND activo = true";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(id, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(id);
                alumno.setDni(rs.getInt("dni"));
//                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaDeNacimiento").toLocalDate());
                alumno.setActivo(true);
            } else {
                JOptionPane.showMessageDialog(null, "No existe ese alumno: ");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL" + ex);
        }
        return alumno;
    }
//
//    public List<Alumno> listarAlumno() {
//        String sql = "SELECT idAlumno, dni, apellido, nombre, fechaDeNacimiento FROM alumno estado = 1";
//        ArrayList<Alumno> alumnos = new ArrayList<>();
//
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Alumno alumno = new Alumno();
//                alumno = new Alumno();
//                alumno.setDni(rs.getInt("dni"));
////                alumno.setApellido(rs.getString("apellido"));
//                alumno.setNombre(rs.getString("nombre"));
//                alumno.setFechaNac(rs.getDate("fechaDeNacimiento").toLocalDate());
//                alumno.setEstado(true);
//
//                alumnos.add(alumno);
//            }
//            ps.close();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error SQL." + ex);
//        }
//        return alumnos;
//    }
}

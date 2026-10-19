package Persistencia;

import Modelo.Conexion;
import Modelo.Cursada;
import Modelo.Materia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionData {

    private Connection con = null;

    public InscripcionData() {
        con = Conexion.getConexion();
        Cursada materia = new Cursada();
    }

    public List<Materia> obtenerMateriasCursadas(int idAlumno) {
        List<Materia> lista = new ArrayList<>();
        String sql = "SELECT DISTINCT m.* FROM materia m JOIN cursada c ON m.idMateria = c.idMateria "
                + "WHERE c.idAlumno=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAlumno);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Materia(rs.getInt("idMateria"), rs.getString("nombre"), rs.getBoolean("estado")));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar materias: " + e.getMessage());
        }
        return lista;
    }

    public List<Materia> obtenerMateriasNoCursadas(int idAlumno) {
        List<Materia> lista = new ArrayList<>();
        String sql = "SELECT * FROM materia WHERE estado = 1 AND idMateria NOT IN "
                + "(SELECT idMateria FROM cursada WHERE idAlumno = ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAlumno);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Materia(rs.getInt("idMateria"), rs.getString("nombre"), rs.getBoolean("estado")));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar materias no cursadas: " + e.getMessage());
        }
        return lista;
    }

    public void inscribir(int idAlumno, int idMateria) {
        String sql = "INSERT INTO cursada (idAlumno, idMateria, nota, asist, cursa) VALUES (?, ?, 0, 0, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            ps.setInt(3, java.time.Year.now().getValue());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alumno inscripto correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al inscribir: " + e.getMessage());
        }
    }

    public void anularInscripcion(int idAlumno, int idMateria) {
        String sql = "DELETE FROM cursada WHERE idAlumno = ? AND idMateria = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inscripción anulada");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al anular: " + e.getMessage());
        }
    }
    
    public List<Cursada> listarNotas() {
        String sql = "SELECT idCursada, idMateria, nota FROM cursada";
        List<Cursada> cursadas = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            MateriaData materiaData = new MateriaData();

            while (rs.next()) {
                Cursada cursada = new Cursada();
                cursada.setIdCursada(rs.getInt("idCursada"));
                cursada.setMateria(materiaData.buscarMateria(rs.getInt("idMateria")));
                cursada.setNota(rs.getFloat("nota"));
                cursadas.add(cursada);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL: " + ex.getMessage());
        }
        return cursadas;
    }

}

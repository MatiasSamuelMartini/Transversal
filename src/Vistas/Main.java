//DELETE FROM alumno;
//ALTER TABLE alumno AUTO_INCREMENT = 1;
//PONER EL AUTO_INCRIMENT EN UNO
package Vistas;
import Persistencia.AlumnoData;
import Modelo.Alumno;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        LocalDate fecha = LocalDate.of(2000, 2, 10);
        ArrayList<Alumno> alumnos = new ArrayList<>();
        Alumno alumno = new Alumno(100, "Martini", "Matias", fecha, true);
        AlumnoData ad = new AlumnoData();
        ad.guardarAlumno(alumno);
        ad.listarAlumno();
        System.out.println(ad.listarAlumno());
        
    }
}

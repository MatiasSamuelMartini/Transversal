package Vistas;
import Persistencia.AlumnoData;
import Modelo.Alumno;
import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {
                
        LocalDate fecha = LocalDate.of(2000, 2, 10);
        Alumno alumno = new Alumno(100, "Matias", fecha, true);
        AlumnoData ad = new AlumnoData();
        ad.guardarAlumno(alumno);
        
        Alumno encontrado = ad.buscarAlumno(1);
        System.out.println("------ALUMNO-----");
        System.out.println(encontrado);


    }
}

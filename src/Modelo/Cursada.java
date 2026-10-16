package Modelo;

import java.time.LocalDate;

public class Cursada {
    private int idCursada;
    private Alumno alumno;
    private Materia materia;
    private double nota;
    private double asist;
    private LocalDate cursa;
    
    public Cursada(){
    }
    
    public Cursada(int idCursada,Alumno alumno,Materia materia, double nota, double asist, LocalDate cursa) {
        this.idCursada = idCursada;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
        this.asist = asist;
        this.cursa = cursa;
    }

    public int getIdCursada() {
        return idCursada;
    }

    public void setIdCursada(int idCursada) {
        this.idCursada = idCursada;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public double getAsist() {
        return asist;
    }

    public void setAsist(double asist) {
        this.asist = asist;
    }

    public LocalDate getCursa() {
        return cursa;
    }

    public void setCursa(LocalDate cursa) {
        this.cursa = cursa;
    }
    
    
    
    
    
}

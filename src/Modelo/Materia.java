package Modelo;

public class Materia {

    private int idMateria;
    private String nombre;
    private boolean estado;

    public Materia() {
    }

    public Materia(int idMateria, String nombre, boolean estado) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.estado = estado;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        String estadoo;
        if (estado == true){
            estadoo = "Activo";
        }else{
            estadoo = "Inactivo";
        }
        return "ID:" + idMateria +"- Nombre:" + nombre +"- Estado:"+ estadoo;
    }

}

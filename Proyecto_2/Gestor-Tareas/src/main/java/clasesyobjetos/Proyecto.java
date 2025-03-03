package clasesyobjetos;

import java.util.ArrayList; //Libreria util para crear arreglos mas eficientemente
        
public class Proyecto {
    //Atributos
    private String nombre;
    private String descripcion;
    private String prioridad;
    private ArrayList<String> PersonasAsignadas;
    private String estado;
    private ArrayList<Tarea> tareas;
    // Constructor
    // Prepara los atributos que va a tener un objeto Proyecto, así los valores pueden ser asignados correctamente y de forma automatica
    public Proyecto(String nombre, String descripcion, String prioridad, String estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.PersonasAsignadas = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }

    // Getters
    public String getNombre() {return nombre;}
    public String getDescripcion() {return descripcion;}
    public String getPrioridad() {return prioridad;}
    public ArrayList<String> getEquipoAsignado() {return PersonasAsignadas;}
    public String getEstado() {return estado;}
    public ArrayList<Tarea> getTareas() {return tareas;}

    // Setters
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public void setPrioridad(String prioridad) {this.prioridad = prioridad;}
    public void setEquipoAsignado(ArrayList<String> PersonasAsignadas) {this.PersonasAsignadas = PersonasAsignadas;}
    public void setEstado(String estado) {this.estado = estado;}
    public void setTareas(ArrayList<Tarea> tareas) {this.tareas = tareas;}
}


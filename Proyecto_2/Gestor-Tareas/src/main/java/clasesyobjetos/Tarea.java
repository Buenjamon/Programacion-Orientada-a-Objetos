package clasesyobjetos;

public class Tarea {
    //Atributos de una tarea
    private String nombre;
    private String descripcion;
    private String prioridad;
    private String estado;
    private String fechaVencimiento;

    //Constructor
    public Tarea(String nombre, String descripcion, String prioridad, String estado, String fechaVencimiento) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.fechaVencimiento = fechaVencimiento;
    }

    // Getters
    public String getNombre() {return nombre;}
    public String getDescripcion() {return descripcion;}
    public String getPrioridad() {return prioridad;}
    public String getEstado() {return estado;}
    public String getFechaVencimiento() {return fechaVencimiento;}

    // Setters
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public void setPrioridad(String prioridad) {this.prioridad = prioridad;}
    public void setEstado(String estado) {this.estado = estado;}
    public void setFechaVencimiento(String fechaVencimiento) {this.fechaVencimiento = fechaVencimiento;}
}
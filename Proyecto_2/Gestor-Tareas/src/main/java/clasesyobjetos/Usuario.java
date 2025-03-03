package clasesyobjetos;

import java.util.ArrayList; 

public class Usuario {
    private String nombre;
    private String correo;
    private ArrayList<Proyecto> proyectosAsignados;
    private String contraseña;

    // Lista de usuarios predefinidos
    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    static { // Definimos los usuarios que ya estaran definidos desde la creacion de la lista
        listaUsuarios.add(new Usuario("Benjamin", "be13abisai@gmail.com", "jamones"));
        listaUsuarios.add(new Usuario("Frida", "fridapatriciag1931@gmail.com", "frida"));
    }
    // Constructor
    public Usuario(String nombre, String correo, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.proyectosAsignados = new ArrayList<>();
    }

    // Getters
    public String getNombre() {return nombre;}
    public String getCorreo() {return correo;}
    public ArrayList<Proyecto> getProyectosAsignados() {return proyectosAsignados;}
    public String getContraseña() {return contraseña;}

    // Setters
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setCorreo(String correo) {this.correo = correo;}
    public void setProyectosAsignados(ArrayList<Proyecto> proyectosAsignados) {this.proyectosAsignados = proyectosAsignados;}
    public void setContraseña(String contraseña) {this.contraseña = contraseña;}

    // Obtener la lista de usuarios
    public static ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
}
}
package com.mycompany.producto;

public class Producto {
    //Atributos
    private String descripcion;
    private String codigo;
    private String tipo;
    private double costo;
    private double impuesto;
    
    //Métodos
    //Get
    public String getDescripcion() { return descripcion; } 
    public String getCodigo() { return codigo; }
    public String getTipo() { return tipo; }
    public double getCosto() { return costo; }
    public double getImpuesto() { return impuesto; }
    //Set
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setCosto(double costo) { this.costo = costo; }
    public void setImpuesto(double impuesto) { this.impuesto = impuesto; }
    //imprimir atributos del producto
    public void mostrarProducto() {
        System.out.println("Producto:");
        System.out.println("Descripcion: " + descripcion);
        System.out.println("Codigo: " + codigo);
        System.out.println("Tipo: " + tipo);
        System.out.println("Costo: $" + costo);
        System.out.println("Impuesto: " + impuesto + "%");
    }
    //precio de venta
    public double calcularPrecio(double utilidad) {
        double precioInicial = costo + (costo * (utilidad / 100));
        double precioFinal = precioInicial + (precioInicial * (impuesto / 100));
        return precioFinal;
    }
}
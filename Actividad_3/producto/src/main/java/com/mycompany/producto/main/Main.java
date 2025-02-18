package com.mycompany.producto.main;

import com.mycompany.producto.Producto; //Funciones de la clase producto importadas
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // datos para el primer producto
        Producto producto1 = new Producto();
        datosProducto(scanner, producto1);

        // datos para el segundo producto
        Producto producto2 = new Producto();
        datosProducto(scanner, producto2);

        // Mostrar información de los productos
        producto1.mostrarProducto();
        producto2.mostrarProducto();

        // Comparar productos y mostrar el resultado
        String productoMasCaro = compararProductos(producto1, producto2);
        System.out.println("\nEl producto con mayor precio de venta es: " + productoMasCaro);
    }

    // Capturar datos del usuario con excepciones
    private static void datosProducto(Scanner scanner, Producto producto) {
        try {
            System.out.print("\nIngrese descripcion: ");
            producto.setDescripcion(scanner.nextLine());

            System.out.print("Ingrese codigo: ");
            producto.setCodigo(scanner.nextLine());

            System.out.print("Ingrese tipo: ");
            producto.setTipo(scanner.nextLine());

            System.out.print("Ingrese costo: ");
            producto.setCosto(Double.parseDouble(scanner.nextLine()));

            System.out.print("Ingrese impuesto (%): ");
            producto.setImpuesto(Double.parseDouble(scanner.nextLine()));

        } catch (Exception e) {
            System.out.println("Ingresa un dato valido por favor.");
            datosProducto(scanner, producto); // Se vuelve a pedir la información
        }
    }

    // Método estático para comparar los productos
    public static String compararProductos(Producto p1, Producto p2) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIngrese el porcentaje de utilidad para calcular el precio de venta: ");
        double utilidad = Double.parseDouble(scanner.nextLine());

        double precio1 = p1.calcularPrecio(utilidad);
        double precio2 = p2.calcularPrecio(utilidad);

        System.out.println("\nPrecio de venta del Producto 1: $" + precio1);
        System.out.println("Precio de venta del Producto 2: $" + precio2);

        return (precio1 > precio2) ? p1.getDescripcion() : p2.getDescripcion();
    }
}

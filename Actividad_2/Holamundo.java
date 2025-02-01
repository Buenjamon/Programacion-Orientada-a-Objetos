/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.holamundo;

import java.util.Scanner; //importar la clase Scanner

//1. Declaración de la clase
public class Holamundo {
    
    //2. Metodo principal (punto de entrada)
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in); // Crear un objeto scanner
        
        System.out.print("Escribe tu nombre: "); //Solicitar nombre al usuario
        //3. Instrucción para mostrar texto en consola
        String name = scanner.nextLine(); //Leer entrada del usuario
        name = name.toLowerCase(); //Transformar a minúsculas el nombre para su adecuado uso
        
        //Si la entrada del usuario coincide con estos casos:
        if (name.equalsIgnoreCase("joe")) {
            System.out.println("Mi Jouuuu que cuentas.");
        } else if (name.equalsIgnoreCase("angel")) { //Otro caso
            System.out.println("Q haces por aqui bro");
        } else if (name.equalsIgnoreCase("Uriel")) {
            System.out.println("Hola Urii como andas");
        } else {
            System.out.println("Hola " + name + "! Un gusto conocerte.");
        }
        // Parte 2: Estructuras de repeticion y arreglos
        // Arreglo de numeros pares
        int[] numerosPares = new int[50]; // Almacenar 49 numeros enteros en un arreglo.
        int index = 0; // la primera posicion del arreglo es cero

        // Generar números pares desde 2 hasta 100
        for (int i = 2; i <= 100; i += 2) {
            numerosPares[index] = i;// Almacena el número par segun su posición 
            index++;//incrementar el index por 1 para que siga la cuenta
        }

        // Mostrar los números pares del 1 al 100
        System.out.println("Números pares desde 2 hasta 100:");
        for (int i = 0; i < index; i++) {
            System.out.print(numerosPares[i] + " ");
        }
        scanner.close(); // Cerrar el scanner
    }
}



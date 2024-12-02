/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ordenamientoexterno;

import java.util.*;
import java.io.*;

/**
  * Numero de Control: 23550373
 * @author Alexis Cruz Martinez
 * Materia: Estructura de Datos
 */
public class OrdenamientoExterno {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hola mundooooo");
        Scanner input = new Scanner(System.in);
        String archivoSalida = "numeros_ordenados.txt";
        // Lista de números para guardar
        int[] numero = {};

        while (true) {
            System.out.print("Número (o 'salir' para terminar): ");
            String inputString = input.nextLine();

            // Si el usuario ingresa 'salir', terminar el ciclo
            if (inputString.equalsIgnoreCase("salir")) {
                break;
            }

            try {
                // Intentar convertir la entrada a un número
                int x = Integer.parseInt(inputString);

                // Insertar el número en el arreglo
                numero = insertarNumero(numero, x);

                // Nombre del archivo
                String nombreArchivo = "numeros.txt";

                // Llamar al método para guardar los números en el archivo
                guardarNumeros(nombreArchivo, numero);

            } catch (NumberFormatException e) {
                // Si la entrada no es un número válido
                System.out.println("Entrada no válida. Por favor ingresa un número entero.");
            }
        }

        // Imprimir mensaje de finalización
        System.out.println("Se terminaron de insertar los números.");

        try {
            // Leer los números del archivo de entrada
            List<Integer> numeros = leerNumerosDeArchivo("numeros.txt");
            mostrarContenidoArchivo("numeros.txt");

            // Ordenar los números con mezcla natural
            List<Integer> numerosOrdenados = MezclaNatural.ordenar(numeros);

            // Guardar los números ordenados en el archivo de salida
            escribirNumerosEnArchivo(archivoSalida, numerosOrdenados);

            System.out.println("Archivo ordenado creado: " + archivoSalida);

            mostrarContenidoArchivo("numeros_ordenados.txt");
        } catch (IOException e) {
            System.err.println("Error al manejar el archivo: " + e.getMessage());
        }

    }

    public static int[] insertarNumero(int[] arreglo, int nuevoNumero) {
        // Crear un nuevo arreglo con un tamaño mayor
        int[] nuevoArreglo = new int[arreglo.length + 1];

        // Copiar los elementos del arreglo original al nuevo arreglo
        for (int i = 0; i < arreglo.length; i++) {
            nuevoArreglo[i] = arreglo[i];
        }

        // Insertar el nuevo número al final del nuevo arreglo
        nuevoArreglo[arreglo.length] = nuevoNumero;

        return nuevoArreglo;
    }

    public static List<Integer> leerNumerosDeArchivo(String nombreArchivo) throws IOException {
        List<Integer> numeros = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                numeros.add(Integer.parseInt(linea.trim()));
            }
        }
        return numeros;
    }

    public static void escribirNumerosEnArchivo(String nombreArchivo, List<Integer> numeros) throws IOException {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (int numero : numeros) {
                escritor.write(numero + "\n");
            }
        }
    }

    public static void mostrarContenidoArchivo(String nombreArchivo) throws IOException {
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            System.out.println("Contenido del archivo '" + nombreArchivo + "':");
            while ((linea = lector.readLine()) != null) {
                System.out.print(linea + ", ");
            }
        }
    }

    public static void guardarNumeros(String nombreArchivo, int[] numeros) {
        try (FileWriter escritor = new FileWriter(nombreArchivo)) {
            // Escribir cada número en una nueva línea
            for (int numero : numeros) {
                escritor.write(numero + "\n");
            }
            System.out.println("Numeros guardados en el archivo '" + nombreArchivo + "' exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar los números: " + e.getMessage());
        }
    }

}

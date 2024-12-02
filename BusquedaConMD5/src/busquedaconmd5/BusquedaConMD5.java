/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedaconmd5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

/**
* Numero de Control: 23550373
 * @author Alexis Cruz Martinez
 * Materia: Estructura de Datos
 */
public class BusquedaConMD5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hola mundoooo");
       // Crear un HashMap para almacenar estudiantes (clave MD5 -> Nombre)
        HashMap<String, String> estudiantes = new HashMap<>();

        // Crear un escáner para recibir la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que ingrese los nombres de los estudiantes
        System.out.println("Ingrese la cantidad de estudiantes que desea agregar:");
        int cantidadEstudiantes = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea restante

        for (int i = 0; i < cantidadEstudiantes; i++) {
            System.out.println("Ingrese el nombre del estudiante " + (i + 1) + ":");
            String nombre = scanner.nextLine();
            agregarEstudiante(estudiantes, nombre);
        }

        // Mostrar todos los estudiantes con sus hashes MD5
        System.out.println("\nEstudiantes registrados (con su hash MD5):");
        for (String hash : estudiantes.keySet()) {
            System.out.println("Hash: " + hash + " - Nombre: " + estudiantes.get(hash));
        }

        // Solicitar al usuario que busque un estudiante por nombre
        System.out.println("\nIngrese el nombre del estudiante que desea buscar:");
        String nombreBuscado = scanner.nextLine();

        // Calcular el hash MD5 del nombre ingresado
        String hashBuscado = generarHashMD5(nombreBuscado);

        // Buscar el estudiante en el HashMap
        if (estudiantes.containsKey(hashBuscado)) {
            System.out.println("Estudiante encontrado: " + estudiantes.get(hashBuscado));
        } else {
            System.out.println("No se encontró ningún estudiante con ese nombre.");
        }

        // Cerrar el escáner
        scanner.close();
    }

    // Método para agregar estudiantes al HashMap
    private static void agregarEstudiante(HashMap<String, String> mapa, String nombre) {
        String hash = generarHashMD5(nombre);
        mapa.put(hash, nombre);
    }

    // Método para generar el hash MD5 de un texto
    private static String generarHashMD5(String texto) {
        try {
            // Obtener una instancia del algoritmo MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Convertir el texto en un array de bytes y generar el hash
            byte[] hashBytes = md.digest(texto.getBytes());

            // Convertir los bytes del hash a una cadena hexadecimal
            StringBuilder hashHex = new StringBuilder();
            for (byte b : hashBytes) {
                hashHex.append(String.format("%02x", b));
            }

            return hashHex.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("El algoritmo MD5 no está disponible.");
            return null;
        }
    }
}
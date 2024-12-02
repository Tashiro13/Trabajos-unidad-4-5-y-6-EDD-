/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploarbolbinario;

import java.util.Scanner;

/**
 * Numero de Control: 23550373
 * @author Alexis Cruz Martinez
 * Materia: Estructura de Datos
 */
public class EjemploArbolBinario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hola mundoooo");
     Scanner scanner = new Scanner(System.in);
        ArbolBinario arbol = new ArbolBinario();
        int opcion;
        int valor;
        String entrada;

        do {
            System.out.println("\nMenu de opciones:");
            System.out.println("1. Insertar un valor");
            System.out.println("2. Mostrar el árbol");
            System.out.println("3. Verificar si el árbol está vacío");
            System.out.println("4. Obtener la raíz");
            System.out.println("5. Buscar un valor en el árbol");
            System.out.println("6. Obtener la altura del árbol");
            System.out.println("7. Obtener el número de elementos");
            System.out.println("8. Borrar un valor del árbol");
            System.out.println("9. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    do {
                        System.out.print("Ingrese el valor a insertar o escriba 'salir' para terminar: ");
                        entrada = scanner.next();

                        if (!entrada.equalsIgnoreCase("salir")) {
                            try {
                                valor = Integer.parseInt(entrada);
                                arbol.insertar(valor);
                                System.out.println("Valor insertado: " + valor);
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida. Por favor, ingrese un número entero o 'salir'.");
                            }
                        }
                    } while (!entrada.equalsIgnoreCase("salir"));
                    /*
                    System.out.print("Ingrese el valor a insertar: ");
                    valor = scanner.nextInt();
                    arbol.insertar(valor);
                    System.out.println("Valor insertado: " + valor);
                    */
                    break;

                case 2:
                    System.out.println("Árbol:");
                    arbol.mostrarArbol();
                    break;

                case 3:
                    System.out.println("¿Está vacío el árbol? " + (arbol.estaVacio() ? "Sí" : "No"));
                    break;

                case 4:
                    System.out.println("Raíz del árbol: " + arbol.obtenerRaiz());
                    break;

                case 5:
                    System.out.print("Ingrese el valor a buscar: ");
                    valor = scanner.nextInt();
                    if (arbol.pertenece(valor)) {
                        System.out.println("El valor " + valor + " está en el árbol.");
                    } else {
                        System.out.println("El valor " + valor + " NO está en el árbol.");
                    }
                    break;

                case 6:
                    System.out.println("Altura del árbol: " + arbol.obtenerAltura());
                    break;

                case 7:
                    System.out.println("Número de elementos en el árbol: " + arbol.obtenerNumeroDeElementos());
                    break;

                case 8:
                    System.out.print("Ingrese el valor a borrar: ");
                    valor = scanner.nextInt();
                    if (arbol.pertenece(valor)){
                        arbol.borrar(valor);
                        System.out.println("Valor " + valor + " borrado.");
                    } else {
                        System.out.println("El valor " + valor + " NO está en el árbol."); 
                    }
                    break;

                case 9:
                    System.out.println("¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 9);

        scanner.close();
    }
    
}

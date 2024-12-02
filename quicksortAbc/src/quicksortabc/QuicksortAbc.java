package quicksortabc;

import java.util.Scanner;
import java.util.Random;

/**
  * Numero de Control: 23550373
 * @author Alexis Cruz Martinez
 * Materia: Estructura de Datos
 */
public class QuicksortAbc {

    public static void main(String[] args) {
        System.out.println("Hola mundooooo");
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar letras al usuario
        System.out.print("Ingrese las letras a ordenar, sin espacios (por ejemplo, ZMAKXBRPQ): ");
        String input = scanner.nextLine().toUpperCase();
        char[] letras = input.toCharArray();

        // Solicitar tipo de pivote
        System.out.println("Seleccione el tipo de pivote:");
        System.out.println("1. Primer elemento");
        System.out.println("2. Último elemento");
        System.out.println("3. Elemento aleatorio");
        int opcionPivote = scanner.nextInt();

        System.out.println("Arreglo original:");
        for (char letra : letras) {
            System.out.print(letra + " ");
            
        }

        quickSort(letras, 0, letras.length - 1, opcionPivote);

        System.out.println("\nArreglo ordenado:");
        for (char letra : letras) {
            System.out.print(letra + " ");
            
        }
    }

    public static void quickSort(char[] array, int start, int end, int opcionPivote) {
        
        if (start < end) {
            int indiceParticion = particion(array, start, end, opcionPivote);
            quickSort(array, start, indiceParticion - 1, opcionPivote);
            quickSort(array, indiceParticion + 1, end, opcionPivote);
            
        }
    }

    private static int particion(char[] array, int start, int end, int opcionPivote) {
        
        int pivotIndex;
        switch (opcionPivote) {
            case 1: // Primer elemento como pivote
                pivotIndex = start;
                break;
            case 2: // Último elemento como pivote
                pivotIndex = end;
                break;
            case 3: // Elemento aleatorio como pivote
                Random random = new Random();
                pivotIndex = start + random.nextInt(end - start + 1);
                break;
            default:
                System.out.println("Opción de pivote no válida. Usando el último elemento por defecto.");
                pivotIndex = end;
                
        }

        // Intercambiamos el pivote elegido con el último elemento
        char temp = array[pivotIndex];
        array[pivotIndex] = array[end];
        array[end] = temp;

        // Usamos el pivote (ahora en la posición 'end') para la partición
        char pivote = array[end];
        int i = start - 1;

        for (int j = start; j < end; j++) {
            if (array[j] < pivote) {
                i++;
                // Intercambiamos array[i] y array[j]
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                
            }
        }

        // Colocamos el pivote en su posición final
        temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;

        return i + 1; // Índice final del pivote
        
    }
}

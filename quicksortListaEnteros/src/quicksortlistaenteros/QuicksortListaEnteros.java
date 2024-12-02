package quicksortlistaenteros;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
  * Numero de Control: 23550373
 * @author Alexis Cruz Martinez
 * Materia: Estructura de Datos
 */
public class QuicksortListaEnteros {

    public static void main(String[] args) {
        System.out.println("Hola mundoooo");
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar números al usuario
        List<Integer> numbers = new ArrayList<>();
        System.out.println("Ingrese los números a ordenar (separados por espacios): ");
        String[] input = scanner.nextLine().split(" ");
        
        for (String num : input) {
            numbers.add(Integer.parseInt(num.trim()));
            
        }

        // Solicitar tipo de pivote
        System.out.println("Seleccione el tipo de pivote:");
        System.out.println("1. Primer elemento");
        System.out.println("2. Último elemento");
        System.out.println("3. Elemento aleatorio");
        int opcionPivote = scanner.nextInt();

        System.out.println("Lista original:");
        System.out.println(numbers);

        quickSort(numbers, 0, numbers.size() - 1, opcionPivote);

        System.out.println("Lista ordenada:");
        System.out.println(numbers);
        
    }

    public static void quickSort(List<Integer> list, int start, int end, int opcionPivote) {
        
        if (start < end) {
            int partitionIndex = partition(list, start, end, opcionPivote);
            quickSort(list, start, partitionIndex - 1, opcionPivote);
            quickSort(list, partitionIndex + 1, end, opcionPivote);
            
        }
    }

    private static int partition(List<Integer> list, int start, int end, int opcionPivote) {
        
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
        int temp = list.get(pivotIndex);
        list.set(pivotIndex, list.get(end));
        list.set(end, temp);

        // Usamos el pivote (ahora en la posición 'end') para la partición
        int pivot = list.get(end);
        int i = start - 1;

        for (int j = start; j < end; j++) {
            if (list.get(j) < pivot) {
                i++;
                // Intercambiamos list[i] y list[j]
                temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
                
            }
        }

        // Colocamos el pivote en su posición final
        temp = list.get(i + 1);
        list.set(i + 1, list.get(end));
        list.set(end, temp);

        return i + 1; // Índice final del pivote
        
    }
}

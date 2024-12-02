/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package radix;

import java.util.Scanner;

/*
 * Numero de Control: 23550373
 * @author Alexis Cruz Martinez
 * Materia: Estructura de Datos
import java.util.Scanner;
*/
public class Radix {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Hola mundoooo");
        System.out.println("Ingrese el tamaño del arreglo:");
        int n = leer.nextInt();
        
        if (n <= 0) {
            System.out.println("El tamaño del arreglo debe ser mayor a 0.");
            return;
        }

        int[] arr = new int[n];
        System.out.println("Ingrese los elementos del arreglo:");
        for (int i = 0; i < n; i++) {
            arr[i] = leer.nextInt();
        }

        Radix obj = new Radix();
        obj.radixSort(arr);

        System.out.println("El arreglo ordenado de menor a mayor es:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    private void radixSort(int[] arr) {
        int max = getMax(arr); // Encuentra el valor máximo para determinar el número de dígitos.

        // Ordenar utilizando cada dígito, desde el menos significativo al más significativo.
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    private void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n]; // Array temporal para almacenar los resultados ordenados.
        int[] count = new int[10]; // Cuenta los dígitos (0-9).

        // Contar ocurrencias de los dígitos.
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        // Transformar `count` para que contenga las posiciones reales de los dígitos.
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Construir el arreglo ordenado.
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copiar el arreglo ordenado de vuelta al original.
        System.arraycopy(output, 0, arr, 0, n);
    }

    private int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}

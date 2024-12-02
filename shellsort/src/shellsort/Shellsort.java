/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package shellsort;

import java.util.Scanner;

/**
  * Numero de Control: 23550373
 * @author Alexis Cruz Martinez
 * Materia: Estructura de Datos
 */
public class Shellsort {

    public static void main(String[] args) {
        System.out.println("Hola mundoooo");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa los numeros que deseas ordenar, separados por espacios:");
        String input = scanner.nextLine();

        String[] tokens = input.split(" ");
        int[] arr = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }

        System.out.println("\nArreglo original:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        shellSort(arr);

        System.out.println("\n\nArreglo ordenado:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        scanner.close();
    }
     
    public static void shellSort(int[] arr) {
        int n = arr.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;

                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }

                arr[j] = temp;
            }
        }
    }
}



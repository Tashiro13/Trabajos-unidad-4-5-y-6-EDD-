/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg3.pkg3.mezcladirecta;
import java.util.Scanner;

 /* 
  * Numero de Control: 23550373
 * @author Alexis Cruz Martinez
 * Materia: Estructura de Datos
 */
public class MezclaDirecta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hola mundooooo");
        Scanner scanner = new Scanner(System.in);

        System.out.print("¿Cuántos numero quieres poner? ");
        int n = scanner.nextInt();

        // se crea el arreglo ajustado para el tamaño puesto por el usuario
        int[] arreglo = new int[n];

        // pedimos al usuario que ponga los números
        System.out.println("Ponlos aquí porfa:");
        for (int i = 0; i < n; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            arreglo[i] = scanner.nextInt();
        }
        
        // aquí se imprime el arreglo original
        System.out.println("Este es tu arreglo original:");
        imprimirArreglo(arreglo); 

        // se llama al método de ordenamiento por mezcla
        mezclaD(arreglo, 0, arreglo.length - 1);

        // se imprime el arreglo ordenado
        System.out.println("Arreglo ordenado:");
        imprimirArreglo(arreglo); 

        scanner.close();
    }
    
    // método que implementa el algoritmo
    public static void mezclaD(int[] arreglo, int izqui, int dere) {
        // Caso base: si izqui (izquierda) es menor que dere (derecha), el arreglo tiene al menos dos elementos
        if (izqui < dere) {
            // se calcula el punto medio
            int mid = izqui + (dere - izqui) / 2;
            
            // Llamamos recursivamente al método mergeSort para la mitad izquierda
            mezclaD(arreglo, izqui, mid);
            
            // Llamamos recursivamente al método mergeSort para la mitad derecha
            mezclaD(arreglo, mid + 1, dere);
            
            // Combinamos las mitades ordenadas
            merge(arreglo, izqui, mid, dere);
        }
    }
    
    // método que cambea (el que combina (merge) dos subarreglos ordenados
    public static void merge(int[] arreglo, int izqui, int mid, int dere) {
        // se calcula el tamaños de los subarreglos izqui (izquierdo) y dere (derecho)
        int n1 = mid - izqui + 1;
        int n2 = dere - mid;
        
        // se crean arreglos temporales para almacenar los elementos
        int[] arreloizqui = new int[n1];
        int[] arreglodere = new int[n2];
        
        // se copian los datos a los arreglos temporales
        for (int i = 0; i < n1; i++) {
            arreloizqui[i] = arreglo[izqui + i];
        }
        for (int j = 0; j < n2; j++) {
            arreglodere[j] = arreglo[mid + 1 + j];
        }
        
        // se inician los índices para recorrer los subarreglos y el arreglo original
        int i = 0, j = 0, k = izqui;
        
        // se combinan los arreglos ordenados
        while (i < n1 && j < n2) {
            if (arreloizqui[i] <= arreglodere[j]) {
                arreglo[k] = arreloizqui[i];
                i++;
            } else {
                arreglo[k] = arreglodere[j];
                j++;
            }
            k++;
        }
        
        // si quedan arreglos restantes (del lado izquierdo), aqui se copian
        while (i < n1) {
            arreglo[k] = arreloizqui[i];
            i++;
            k++;
        }
        
        // lo mismo del lado derecho
        while (j < n2) {
            arreglo[k] = arreglodere[j];
            j++;
            k++;
        }
    }
    
    // método para imprimir un arreglo
    public static void imprimirArreglo(int[] arreglo) {
        for (int num : arreglo) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

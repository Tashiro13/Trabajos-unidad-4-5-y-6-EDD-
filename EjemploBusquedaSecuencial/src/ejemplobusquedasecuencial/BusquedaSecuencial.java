/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplobusquedasecuencial;

/**
 *
 * @author chaco
 */
public class BusquedaSecuencial {

    public static int busquedaSecuencial(int[] arreglo, int numero) {
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] == numero) {
                return i; 

            }
        }
        return -1; 
    }
}

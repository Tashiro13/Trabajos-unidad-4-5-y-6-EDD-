/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploarbolbinario;

/**
 *
 * @author OscarMolina
 */
public class Nodo {
        int dato;
        Nodo izquierdo, derecho;

        Nodo(int dato) {
            this.dato = dato;
            izquierdo = null;
            derecho = null;
        }
    }
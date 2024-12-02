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
public class ArbolBinario {

    // Raíz del árbol
    private Nodo raiz;

    // Constructor del árbol binario
    public ArbolBinario() {
        raiz = null;
    }

    // Método para saber si el árbol está vacío
    public boolean estaVacio() {
        return raiz == null;
    }

    // Método para obtener la raíz
    public int obtenerRaiz() {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
            return -1; // Indicamos que no existe raíz
        }
        return raiz.dato;
    }

    // Método para obtener el subárbol izquierdo
    public ArbolBinario obtenerSubArbolIzquierdo() {
        ArbolBinario subArbol = new ArbolBinario();
        subArbol.raiz = raiz != null ? raiz.izquierdo : null;
        return subArbol;
    }

    // Método para obtener el subárbol derecho
    public ArbolBinario obtenerSubArbolDerecho() {
        ArbolBinario subArbol = new ArbolBinario();
        subArbol.raiz = raiz != null ? raiz.derecho : null;
        return subArbol;
    }

    // Método para insertar un nodo en el árbol
    public void insertar(int dato) {
        raiz = insertarRecursivo(raiz, dato);
    }

    private Nodo insertarRecursivo(Nodo nodo, int dato) {
        if (nodo == null) {
            nodo = new Nodo(dato);
            return nodo;
        }

        if (dato < nodo.dato) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, dato);
        } else if (dato > nodo.dato) {
            nodo.derecho = insertarRecursivo(nodo.derecho, dato);
        }

        return nodo;
    }

    // Método para borrar un nodo
    public void borrar(int dato) {
        raiz = borrarRecursivo(raiz, dato);
    }

    private Nodo borrarRecursivo(Nodo nodo, int dato) {
        if (nodo == null) {
            return null;
        }

        if (dato < nodo.dato) {
            nodo.izquierdo = borrarRecursivo(nodo.izquierdo, dato);
        } else if (dato > nodo.dato) {
            nodo.derecho = borrarRecursivo(nodo.derecho, dato);
        } else {
            // Nodo a borrar encontrado
            if (nodo.izquierdo == null) {
                return nodo.derecho;
            } else if (nodo.derecho == null) {
                return nodo.izquierdo;
            }

            nodo.dato = obtenerMinimo(nodo.derecho);
            nodo.derecho = borrarRecursivo(nodo.derecho, nodo.dato);
        }

        return nodo;
    }

    private int obtenerMinimo(Nodo nodo) {
        int minimo = nodo.dato;
        while (nodo.izquierdo != null) {
            minimo = nodo.izquierdo.dato;
            nodo = nodo.izquierdo;
        }
        return minimo;
    }

    // Método para saber si un dato pertenece al árbol
    public boolean pertenece(int dato) {
        return perteneceRecursivo(raiz, dato);
    }

    private boolean perteneceRecursivo(Nodo nodo, int dato) {
        if (nodo == null) {
            return false;
        }

        if (dato == nodo.dato) {
            return true;
        } else if (dato < nodo.dato) {
            return perteneceRecursivo(nodo.izquierdo, dato);
        } else {
            return perteneceRecursivo(nodo.derecho, dato);
        }
    }

    // Método para obtener la altura del árbol
    public int obtenerAltura() {
        return obtenerAlturaRecursiva(raiz);
    }

    private int obtenerAlturaRecursiva(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }

        int alturaIzquierda = obtenerAlturaRecursiva(nodo.izquierdo);
        int alturaDerecha = obtenerAlturaRecursiva(nodo.derecho);

        return Math.max(alturaIzquierda, alturaDerecha) + 1;
    }

    // Método para obtener el número de nodos
    public int obtenerNumeroDeElementos() {
        return obtenerNumeroDeElementosRecursivo(raiz);
    }

    private int obtenerNumeroDeElementosRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + obtenerNumeroDeElementosRecursivo(nodo.izquierdo) + obtenerNumeroDeElementosRecursivo(nodo.derecho);
    }

    // Método para mostrar el árbol en consola (Recorrido Inorden)
    public void mostrarArbol() {
        if (raiz == null) {
            System.out.println("El árbol está vacío.");
            }
        mostrarArbolGrafico(raiz, "", true);
    }

    private void mostrarArbolGrafico(Nodo nodo, String espacio, boolean esIzquierdo) {
    if (nodo != null) {
        System.out.println(espacio + (esIzquierdo ? "├── " : "└── ") + "(" + nodo.dato + ")");
        
        // Agrega espacios adicionales para mejorar la visualización de los hijos
        mostrarArbolGrafico(nodo.izquierdo, espacio + (esIzquierdo ? "│   " : "    "), true);
        mostrarArbolGrafico(nodo.derecho, espacio + (esIzquierdo ? "│   " : "    "), false);
    }
}

}

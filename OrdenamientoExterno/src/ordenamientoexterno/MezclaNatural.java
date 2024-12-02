
package ordenamientoexterno;

import java.util.ArrayList;
import java.util.List;

public class MezclaNatural {

    // Método principal para ordenar usando mezcla natural
    public static List<Integer> ordenar(List<Integer> numeros) {
        if (numeros.size() <= 1) return numeros;

        boolean estaOrdenado = false;

        while (!estaOrdenado) {
            List<List<Integer>> sublistas = new ArrayList<>();
            List<Integer> sublistaActual = new ArrayList<>();
            sublistaActual.add(numeros.get(0));

            // Dividir en sublistas naturales (ya ordenadas)
            for (int i = 1; i < numeros.size(); i++) {
                if (numeros.get(i) >= numeros.get(i - 1)) {
                    sublistaActual.add(numeros.get(i));
                } else {
                    sublistas.add(sublistaActual);
                    sublistaActual = new ArrayList<>();
                    sublistaActual.add(numeros.get(i));
                }
            }
            sublistas.add(sublistaActual);

            // Si solo hay una sublista, está completamente ordenado
            if (sublistas.size() == 1) {
                estaOrdenado = true;
            } else {
                // Mezclar sublistas
                List<Integer> numerosFusionados = new ArrayList<>();
                for (int i = 0; i < sublistas.size(); i += 2) {
                    if (i + 1 < sublistas.size()) {
                        numerosFusionados.addAll(fusionar(sublistas.get(i), sublistas.get(i + 1)));
                    } else {
                        numerosFusionados.addAll(sublistas.get(i));
                    }
                }
                numeros = numerosFusionados;
            }
        }

        return numeros;
    }

    // Método para fusionar dos listas ordenadas
    private static List<Integer> fusionar(List<Integer> lista1, List<Integer> lista2) {
        List<Integer> resultado = new ArrayList<>();
        int i = 0, j = 0;

        // Fusionar mientras haya elementos en ambas listas
        while (i < lista1.size() && j < lista2.size()) {
            if (lista1.get(i) <= lista2.get(j)) {
                resultado.add(lista1.get(i));
                i++;
            } else {
                resultado.add(lista2.get(j));
                j++;
            }
        }

        // Añadir elementos restantes de cada lista
        while (i < lista1.size()) {
            resultado.add(lista1.get(i));
            i++;
        }
        while (j < lista2.size()) {
            resultado.add(lista2.get(j));
            j++;
        }

        return resultado;
    }
}

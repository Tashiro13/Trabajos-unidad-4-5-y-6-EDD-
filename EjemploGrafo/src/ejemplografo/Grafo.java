/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplografo;

/**
 *
 * @author OscarMolina
 */
import java.util.*;

public class Grafo {
    private List<Nodo> nodos;
    private List<Arista> aristas;

    public Grafo() {
        nodos = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public void agregarNodo(Nodo nodo) {
        nodos.add(nodo);
    }

    public void agregarArista(Nodo origen, Nodo destino, int peso) {
        aristas.add(new Arista(origen, destino, peso));
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    // Método para buscar la ruta más corta usando Dijkstra
    public Map<Nodo, Integer> dijkstra(Nodo inicio) {
    Map<Nodo, Integer> distancias = new HashMap<>();
    Set<Nodo> visitados = new HashSet<>();
    PriorityQueue<Nodo> cola = new PriorityQueue<>((n1, n2) -> Integer.compare(distancias.get(n1), distancias.get(n2)));

    // Inicializar todas las distancias como infinito, excepto el nodo inicial
    for (Nodo nodo : nodos) {
        distancias.put(nodo, Integer.MAX_VALUE);
    }
    distancias.put(inicio, 0);
    cola.add(inicio);

    while (!cola.isEmpty()) {
        Nodo actual = cola.poll();
        if (!visitados.contains(actual)) {
            visitados.add(actual);

            for (Arista arista : getAristasDesde(actual)) { // Método para obtener aristas desde el nodo actual
                Nodo vecino = arista.getDestino();
                if (!visitados.contains(vecino)) {
                    int nuevaDistancia = distancias.get(actual) + arista.getPeso();
                    if (nuevaDistancia < distancias.get(vecino)) {
                        distancias.put(vecino, nuevaDistancia);
                        cola.add(vecino);
                    }
                }
            }
        }
    }

    return distancias;
}
    public List<Arista> getAristasDesde(Nodo nodo) {
    List<Arista> aristasDesdeNodo = new ArrayList<>();
    for (Arista arista : aristas) {
        if (arista.getOrigen().equals(nodo)) {
            aristasDesdeNodo.add(arista);
        }
    }
    return aristasDesdeNodo;
}

}

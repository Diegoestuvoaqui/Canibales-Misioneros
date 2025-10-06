package algorithm.noInformada;

import base.Nodo;
import base.Ronda;
import java.util.*;

public class BusquedaAncho {

    public Nodo busquedaAncho(Nodo raiz) {
        ArrayList<Nodo> nodos = new ArrayList<>();

        Ronda r = raiz.getInfo();
        r.estadoInicial();

        Queue<Nodo> cola = new LinkedList<>();
        Set<String> visitados = new HashSet<>();

        cola.offer(raiz);
        visitados.add(estadoClave(raiz.getInfo()));

        System.out.println("Iniciando búsqueda a lo ancho...\n");

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            nodos.add(actual);

            if (actual.getInfo().estadoMeta()) {
                System.out.println("\n¡Meta encontrada en nivel " + actual.getNivel() + "!");
                obtenerCamino(nodos, actual);
                return actual;
            }

            actual.generarHijos();

            for (Nodo hijo : actual.getHijos()) {
                String claveHijo = estadoClave(hijo.getInfo());
                if (!visitados.contains(claveHijo)) {
                    cola.offer(hijo);
                    visitados.add(claveHijo);
                    nodos.add(hijo);
                }
            }
        }

        System.out.println("No se encontró solución.");
        return null;
    }

    public void obtenerCamino(ArrayList<Nodo> todos, Nodo solucion) {
        List<Nodo> camino = new ArrayList<>();
        Nodo temp = solucion;

        // Retrocede buscando padres hasta llegar a la raíz
        while (true) {
            camino.add(temp);
            boolean encontrado = false;
            for (Nodo posiblePadre : todos) {
                if (posiblePadre.getHijos().contains(temp)) {
                    temp = posiblePadre;
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) break; // Llegamos a la raíz
        }

        // Invertir el camino para mostrar desde el inicio
        Collections.reverse(camino);

        System.out.println("\nCamino desde el inicio hasta la meta:");
        int paso = 0;
        for (Nodo n : camino) {
            System.out.println("Paso " + paso + " → " +
                    Arrays.toString(n.getInfo().getIzquierda()) + " | " +
                    Arrays.toString(n.getInfo().getDerecha()));
            paso++;
        }
    }

    private String estadoClave(Ronda r) {
        return Arrays.toString(r.getIzquierda()) + "|" + Arrays.toString(r.getDerecha());
    }
}

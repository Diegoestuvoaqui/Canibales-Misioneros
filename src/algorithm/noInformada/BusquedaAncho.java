package algorithm.noInformada;

import algorithm.Algorimos;
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
        visitados.add(Algorimos.estadoClave(raiz.getInfo()));

        System.out.println("Iniciando búsqueda a lo ancho...\n");

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
        nodos.add(actual);

            if (actual.getInfo().estadoMeta()) {
                System.out.println("\n¡Meta encontrada en nivel " + actual.getNivel() + "!");
                Algorimos.obtenerCamino(nodos, actual);
                return actual;
            }

            actual.generarHijos();

            for (Nodo hijo : actual.getHijos()) {
                String claveHijo = Algorimos.estadoClave(hijo.getInfo());
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



}

package algorithm.informada;

import algorithm.Algorimos;
import base.Nodo;
import base.Ronda;

import java.util.*;

public class BusquedaVoraz {
    public Nodo busquedaVoraz(Nodo raiz){
        ArrayList<Nodo> nodos = new ArrayList<>();//recorrido
        raiz.getInfo().estadoInicial();//iniciar


        PriorityQueue<Nodo>cola = new PriorityQueue<>(Comparator.comparingInt(Algorimos::heuristica));//ordenados por heuristica
        Set<String>visitados = new HashSet<>();

        cola.add(raiz);
        visitados.add(Algorimos.estadoClave(raiz.getInfo()));

        System.out.println("Iniciando búsqueda voraz...\n");

        while(!cola.isEmpty()){
            Nodo actual=cola.poll();
            nodos.add(actual);

            if(actual.getInfo().estadoMeta()){
                System.out.println("\n¡Meta encontrada en nivel " + actual.getNivel() + "!");
                Algorimos.obtenerCamino(nodos,actual);
                return actual;
            }

            actual.generarHijos();
            for(Nodo hijo:actual.getHijos()){
                String clave = Algorimos.estadoClave(hijo.getInfo());
                if(!visitados.contains(clave)){
                    visitados.add(clave);
                    cola.offer(hijo);
                    nodos.add(hijo);
                }
            }

        }

        System.out.println("No se encontró solución.");
        return null;
    }

}

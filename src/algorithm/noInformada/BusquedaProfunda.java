package algorithm.noInformada;

import algorithm.Algorimos;
import base.Nodo;
import base.Ronda;

import java.util.*;

public class BusquedaProfunda {
    public Nodo BusquedaProfunda(Nodo raiz){
        ArrayList<Nodo>nodos= new ArrayList<>();

        Ronda r= raiz.getInfo();
        r.estadoInicial();

        Deque<Nodo> pila= new LinkedList<>();
        Set<String> visitados= new HashSet<>();

        pila.push(raiz);
        visitados.add(Algorimos.estadoClave(raiz.getInfo()));

        while(!pila.isEmpty()){
            Nodo actual = pila.pop();
            nodos.add(actual);

            if(actual.getInfo().estadoMeta()){
                Algorimos.obtenerCamino(nodos,actual);
                System.out.println("\n¡Meta encontrada en nivel " + actual.getNivel() + "!");
                return actual;
            }

            actual.generarHijos();
            for(Nodo hijo:actual.getHijos()){
                String claveHijo = Algorimos.estadoClave(hijo.getInfo());
                if(!visitados.contains(claveHijo)){
                    pila.push(hijo);
                    visitados.add(claveHijo);
                    nodos.add(hijo);

                }
            }


        }
        System.out.println("No se encontró solución.");
        return null;
    }
}

package algorithm;

import base.Nodo;
import base.Ronda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Algorimos {

    // Búsqueda no informada
    public void busquedaProfunda() {} //hecha
    public void busquedaAncho() {}  //hecha
    
    // Búsqueda informada
    public void busquedaVoraz() {}
    public void busquedaPrimeroAlMejor() {}
    public void aStar() {} // A* es muy común en esta categoría
    
    // Búsqueda local y metaheurísticas
    public void escaladorColinas() {}
    public void busquedaTabu() {}
    public void recocidoSimulado() {}
    public void busquedaLocalIterativa() {}
    
    // Algoritmos evolutivos
    public void algoritmoGenetico() {}
    public void estrategiaEvolutiva() {}
    public void programacionEvolutiva() {}
    
    // Búsqueda con adversarios
    public void criterioMinimax() {}
    public void criterioPodaAlphaBeta() {}

    
    //Help

    //Calcular Camino con la lista de todos los nodos visitados
    public static void obtenerCamino(ArrayList<Nodo> todos, Nodo solucion) {
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

    //Clave para los visitados
    public static String estadoClave(Ronda r) {
        return Arrays.toString(r.getIzquierda()) + "|" + Arrays.toString(r.getDerecha());
    }

    public static int heuristica(Nodo nodo) {
        return  6-nodo.getInfo().distancia()+nodo.getInfo().riego();
    }

    
}
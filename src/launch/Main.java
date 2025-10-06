package launch;

import algorithm.noInformada.BusquedaAncho;
import base.Nodo;

public class Main {
    public static void main(String[] args) {
        Nodo raiz = new Nodo();
        BusquedaAncho bfs = new BusquedaAncho();
        long inicio = System.currentTimeMillis();
        bfs.busquedaAncho(raiz);//Breadth-first search
        long fin = System.currentTimeMillis();
        System.out.println("Breadth-first search: " + (fin - inicio) + " ms");
    }
}

package launch;

import algorithm.noInformada.BusquedaProfunda;
import algorithm.noInformada.BusquedaAncho;
import base.Nodo;
import base.Ronda;

import java.util.Scanner;

public class MenuBusqueda {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== MENÚ DE BÚSQUEDA =====");
            System.out.println("1. Búsqueda en Anchura (BFS)");//Breadth-first search
            System.out.println("2. Búsqueda en Profundidad (DFS)");//Depth-First Search
            System.out.println("15. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> ejecutarBusquedaAncho();
                case 2 -> ejecutarBusquedaProfunda();

                case 15 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 3);
    }

    private static void ejecutarBusquedaAncho() {
        System.out.println("\nEjecutando Búsqueda en Anchura (BFS)...");


        Nodo raiz1 = new Nodo();
        BusquedaAncho bfs = new BusquedaAncho();

        long inicio = System.currentTimeMillis();
        bfs.busquedaAncho(raiz1);
        long fin = System.currentTimeMillis();

        System.out.println("Tiempo total: " + (fin - inicio) + " ms");
    }

    private static void ejecutarBusquedaProfunda() {
        System.out.println("\nEjecutando Búsqueda en Profundidad (DFS)...");


        Nodo raiz2 = new Nodo();
        BusquedaProfunda dfs = new BusquedaProfunda();

        long inicio = System.currentTimeMillis();
        dfs.BusquedaProfunda(raiz2);
        long fin = System.currentTimeMillis();

        System.out.println("Tiempo total: " + (fin - inicio) + " ms");
    }
}

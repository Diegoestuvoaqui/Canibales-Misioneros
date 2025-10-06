package base;

import java.util.Arrays;
import java.util.LinkedList;

public class Nodo {
    protected Ronda ronda;
    protected LinkedList<Nodo> hijos;
    int nivel;

    public Nodo(){
        ronda = new Ronda();
        hijos = new LinkedList<>();
        nivel = 0;
    }

    public int getNivel() {
        return nivel;
    }

    public Ronda getInfo(){
        return this.ronda;
    }

    public void setRonda(Ronda ronda) {
        this.ronda = ronda;
    }

    public LinkedList<Nodo> getHijos(){
        return this.hijos;
    }

    public void generarHijos(){
        // Todos los movimientos posibles del barco (izquierda -> derecha)
        short[][] movimientosBarco = {
                {0, 0},   // 00 - vacío
                {0, 1},   // 01 - 1 caníbal
                {1, 0},   // 10 - 1 caníbal (en diferente posición)
                {0, 2},   // 02 - 1 misionero
                {2, 0},   // 20 - 1 misionero (en diferente posición)
                {1, 2},   // 12 - 1 caníbal + 1 misionero
                {2, 1},   // 21 - 1 misionero + 1 caníbal
                {1, 1},   // 11 - 2 caníbales
                {2, 2}    // 22 - 2 misioneros
        };

        // Para cada movimiento posible del barco
        for (short[] movimiento : movimientosBarco) {
            // Verificar si el movimiento es posible desde la orilla izquierda actual
            if (esMovimientoPosible(movimiento)) {
                // Aplicar el movimiento
                Ronda nuevoEstado = aplicarMovimiento(movimiento);

                // Validar el nuevo estado
                if (esEstadoValido(nuevoEstado)) {
                    Nodo hijo = new Nodo();
                    hijo.setRonda(nuevoEstado);
                    hijo.setNivel(this.nivel+1);
                    this.hijos.add(hijo);
                }
            }
        }
    }

    private void setNivel(int i) {
        this.nivel=i;
    }

    private boolean esMovimientoPosible(short[] movimiento) {
        short[] izquierda = ronda.getIzquierda();

        // Contar cuántos caníbales y misioneros disponibles hay en izquierda
        int canibalesDisponibles = 0;
        int misionerosDisponibles = 0;

        for (short persona : izquierda) {
            if (persona == 1) canibalesDisponibles++;
            if (persona == 2) misionerosDisponibles++;
        }

        // Verificar si tenemos suficientes personas para el movimiento
        int canibalesEnMovimiento = contarValor(movimiento, (short)1);
        int misionerosEnMovimiento = contarValor(movimiento, (short)2);

        return canibalesDisponibles >= canibalesEnMovimiento &&
                misionerosDisponibles >= misionerosEnMovimiento;
    }

    /*private Ronda aplicarMovimiento(short[] movimiento) {
        short[] nuevaIzquierda = Arrays.copyOf(ronda.getIzquierda(), 6);
        short[] nuevaDerecha = Arrays.copyOf(ronda.getDerecha(), 6);

        // Remover personas de izquierda (según el movimiento del barco)
        int removidos = 0;
        for (int i = 0; i < nuevaIzquierda.length && removidos < 2; i++) {
            for (int j = 0; j < movimiento.length; j++) {
                if (movimiento[j] != 0 && nuevaIzquierda[i] == movimiento[j]) {
                    nuevaIzquierda[i] = 0; // remover de izquierda
                    movimiento[j] = 0; // marcar como ya procesado
                    removidos++;
                    break;
                }
            }
        }

        // Agregar personas a derecha (lo que estaba en el barco)
        for (short persona : movimiento) {
            if (persona != 0) {
                int espacio = encontrarEspacioVacio(nuevaDerecha);
                if (espacio != -1) {
                    nuevaDerecha[espacio] = persona;
                }
            }
        }

        Ronda nuevoEstado = new Ronda();
        nuevoEstado.setIzquierda(nuevaIzquierda);
        nuevoEstado.setDerecha(nuevaDerecha);
        return nuevoEstado;
    }*/

    private Ronda aplicarMovimiento(short[] movimiento) {
        short[] nuevaIzquierda = Arrays.copyOf(ronda.getIzquierda(), 6);
        short[] nuevaDerecha = Arrays.copyOf(ronda.getDerecha(), 6);

        // Copiamos el movimiento original
        short[] movTemp = Arrays.copyOf(movimiento, movimiento.length);

        // Remover personas de izquierda
        int removidos = 0;
        for (int i = 0; i < nuevaIzquierda.length && removidos < 2; i++) {
            for (int j = 0; j < movTemp.length; j++) {
                if (movTemp[j] != 0 && nuevaIzquierda[i] == movTemp[j]) {
                    nuevaIzquierda[i] = 0;
                    movTemp[j] = 0; // Solo modificamos la copia
                    removidos++;
                    break;
                }
            }
        }

        // Agregar personas a derecha usando el movimiento original
        for (short persona : movimiento) {
            if (persona != 0) {
                int espacio = encontrarEspacioVacio(nuevaDerecha);
                if (espacio != -1) {
                    nuevaDerecha[espacio] = persona;
                }
            }
        }

        Ronda nuevoEstado = new Ronda();
        nuevoEstado.setIzquierda(nuevaIzquierda);
        nuevoEstado.setDerecha(nuevaDerecha);
        return nuevoEstado;
    }


    private int contarValor(short[] array, short valor) {
        int count = 0;
        for (short elemento : array) {
            if (elemento == valor) count++;
        }
        return count;
    }

    private int encontrarEspacioVacio(short[] orilla) {
        for (int i = 0; i < orilla.length; i++) {
            if (orilla[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private boolean esEstadoValido(Ronda estado) {
        return !tieneMasCanibalesQueMisioneros(estado.getIzquierda()) &&
                !tieneMasCanibalesQueMisioneros(estado.getDerecha());
    }

    private boolean tieneMasCanibalesQueMisioneros(short[] orilla) {
        int canibales = 0;
        int misioneros = 0;

        for (short persona : orilla) {
            if (persona == 1) canibales++;
            if (persona == 2) misioneros++;
        }

        // Los caníbales no pueden superar a los misioneros si hay misioneros presentes
        return misioneros > 0 && canibales > misioneros;
    }



}

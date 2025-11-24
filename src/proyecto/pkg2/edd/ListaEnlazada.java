/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.edd;

/** Lista simplemente enlazada
 * Guarda y maneja objetos a través de nodos simplemente enlazados
 * Utilizada para manejar colecciones de títulos o datos de frecuencia
 *
 * @author bettinacarnevali
 */
public class ListaEnlazada {
    private NodoLista cabeza;
    private int tamano;

    
    //CONSTRUCTOR
    public ListaEnlazada() {
        this.cabeza = null;
        this.tamano = 0;
    }
/**
     * Verifica si la lista está vacía 
     * @return true si la lista no contiene nodos, false en caso contrario.
     */
    public boolean estaVacia() {
        return getCabeza() == null;
    }
    
    public NodoLista getCabeza() {
        return this.cabeza;
    }
/**
     * Agrega un elemento al final de la lista
     * @param dato El objeto a agregar
     */
    public void agregar(Object dato) {
        NodoLista nuevoNodo = new NodoLista();
        nuevoNodo.setDato(dato);

        if (this.estaVacia()) {
            this.setCabeza(nuevoNodo);
        } else {
            NodoLista actual = this.getCabeza();
            while (actual.getSig() != null) {
                actual = actual.getSig();
            }
            actual.setSig(nuevoNodo);
        }
        this.setTamano(this.getTamano() + 1);
    }
/**
* Recorre e imprime todos los elementos de la lista 
*/
    public void recorrer() {
        if (this.estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }

        NodoLista actual = this.getCabeza();
        System.out.print("Lista Enlazada: ");
        
        while (actual != null) {
            System.out.print(actual.getDato() + " -> ");
            actual = actual.getSig();
        }
        System.out.println("NULL");
    }
/**
     * Busca un elemento determinado en la lista
     * @param datoBuscado El objeto a buscar
     * @return true si el objeto es encontrado, false si no
     */
    
    public boolean buscar(Object datoBuscado) {
        NodoLista actual = this.getCabeza();
        
        while (actual != null) {
            if (actual.getDato().equals(datoBuscado)) {
                return true;
            }
            actual = actual.getSig();
        }
        return false;
    }
/**
     * Borra la aparacion de un elemento de la lista
     * @param datoAEliminar El objeto a eliminar
     * @return true si el objeto fue encontrado y eliminado, false si no.
     */
    public boolean borrar(Object datoAEliminar) {
        if (this.estaVacia()) {
            return false;
        }

        if (this.getCabeza().getDato().equals(datoAEliminar)) {
            this.setCabeza(this.getCabeza().getSig());
            this.setTamano(this.getTamano() - 1);
            return true;
        }

        NodoLista actual = this.getCabeza();
        
        while (actual.getSig() != null && !actual.getSig().getDato().equals(datoAEliminar)) {
            actual = actual.getSig();
        }
        
        if (actual.getSig() != null) {
            NodoLista nodoAEliminar = actual.getSig();
            actual.setSig(nodoAEliminar.getSig());
            this.setTamano(this.getTamano() - 1);
            return true;
        }

        return false;
    }


    /**
     * @param cabeza the cabeza to set
     */
    public void setCabeza(NodoLista cabeza) {
        this.cabeza = cabeza;
    }

    /**
     * @return the tamano
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * @param tamano the tamano to set
     */
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
    /**
     * Genera un reporte que contiene objetos ArticuloFrecuencia.\
     * Muestra el resumen y la frecuencia de cada artículo
     * @return Una cadena de texto con el reporte de frecuencias
     */
    public String generarReporteFrecuencias() {
    StringBuilder sb = new StringBuilder();
    NodoLista actual = this.cabeza;
    
    while (actual != null) {
        if (actual.getDato() instanceof ArticuloFrecuencia) {
            ArticuloFrecuencia af = (ArticuloFrecuencia) actual.getDato();
            sb.append(" • Doc: ").append(af.getClaveResumen())
              .append(" (").append(af.getFrecuencia()).append(" veces)\n");
        }
        actual = actual.getSig();
    }
    return sb.toString();
}
}

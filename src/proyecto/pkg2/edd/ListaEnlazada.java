/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.edd;

/**
 *
 * @author bettinacarnevali
 */
public class ListaEnlazada {
    private NodoLista cabeza;
    private int tamano;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamano = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void agregar(Object dato) {
        NodoLista nuevoNodo = new NodoLista();
        nuevoNodo.setDato(dato);

        if (this.estaVacia()) {
            this.cabeza = nuevoNodo;
        } else {
            NodoLista actual = this.cabeza;
            while (actual.getSig() != null) {
                actual = actual.getSig();
            }
            actual.setSig(nuevoNodo);
        }
        this.tamano++;
    }

    public void recorrer() {
        if (this.estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }

        NodoLista actual = this.cabeza;
        System.out.print("Lista Enlazada: ");
        
        while (actual != null) {
            System.out.print(actual.getDato() + " -> ");
            actual = actual.getSig();
        }
        System.out.println("NULL");
    }

    public boolean buscar(Object datoBuscado) {
        NodoLista actual = this.cabeza;
        
        while (actual != null) {
            if (actual.getDato().equals(datoBuscado)) {
                return true;
            }
            actual = actual.getSig();
        }
        return false;
    }

    public boolean borrar(Object datoAEliminar) {
        if (this.estaVacia()) {
            return false;
        }

        if (this.cabeza.getDato().equals(datoAEliminar)) {
            this.cabeza = this.cabeza.getSig();
            this.tamano--;
            return true;
        }

        NodoLista actual = this.cabeza;
        
        while (actual.getSig() != null && !actual.getSig().getDato().equals(datoAEliminar)) {
            actual = actual.getSig();
        }
        
        if (actual.getSig() != null) {
            NodoLista nodoAEliminar = actual.getSig();
            actual.setSig(nodoAEliminar.getSig());
            this.tamano--;
            return true;
        }

        return false;
    }
}

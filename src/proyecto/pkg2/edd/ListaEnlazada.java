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
        return getCabeza() == null;
    }
    
    public NodoLista getCabeza() {
        return this.cabeza;
    }

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

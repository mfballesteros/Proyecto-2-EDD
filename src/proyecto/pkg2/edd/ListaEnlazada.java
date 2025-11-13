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
    private NodoLista head;
    private NodoLista tail;
    private int tamano;

    public ListaEnlazada() {
        this.head = null;
        this.tail = null;
        this.tamano = 0;
    }
    
    //Metodos Escenciales
    
    public boolean esVacia(){
        return head == null;
    }
    
    public void agregar(Object dato){
        NodoLista nuevoNodo = new NodoLista(dato);
        
    }
}

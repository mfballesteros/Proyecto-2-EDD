package proyecto.pkg2.edd;
import java.io.Serializable;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/** Nodo de Lista simplemente enlazada
 * Representa un nodo que contiene un dato y una referencia al siguiente nodo
 * en la secuencia de una lista enlazada simple.
 *
 * @author bettinacarnevali
 */
public class NodoLista implements Serializable {
    private Object dato;
    private NodoLista sig;

    //CONSTRUCTOR
    public NodoLista() {
        this.dato = null;
        this.sig = null;
    }
 /**
     * accede al dato almacenado en este nodo
     * @return El objeto de dato.
     */
    public Object getDato (){
        return dato;
    }
    /**
     * modifica el dato para este nodo
     * @param dato El nuevo objeto de dato a almacenar
     */
    
    public void setDato(Object dato){
        this.dato = dato;
        
    }
    /**
     * accede al siguiente nodo
     * @return El siguiente NodoLista, o null si no hay 
     */
    public NodoLista getSig(){
        return sig;
    }
    /**
     * modifica el nodo al que apuntará este nodo.
     * @param sig La referencia al siguiente NodoLista 
     */
    public void setSig(NodoLista sig){
        this.sig = sig;
    }
    
    
   
    
}

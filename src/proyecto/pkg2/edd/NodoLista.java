package proyecto.pkg2.edd;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bettinacarnevali
 */
public class NodoLista {
    private Object dato;
    private NodoLista sig;

    public NodoLista() {
        this.dato = dato;
        this.sig = null;
    }
    
    public Object getDato (){
        return dato;
    }
    
    public void setDato(Object dato){
        this.dato = dato;
        
    }
    
    public NodoLista getSig(){
        return sig;
    }
    
    public void setSig(NodoLista sig){
        this.sig = sig;
    }
    
    
   
    
}

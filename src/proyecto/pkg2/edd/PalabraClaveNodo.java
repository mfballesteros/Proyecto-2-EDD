/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.edd;

/**
 *
 * @author bettinacarnevali
 */
public class PalabraClaveNodo {
    private String palabraClave;
    private int altura;
    private PalabraClaveNodo hijoIzquierdo;
    private PalabraClaveNodo hijoDerecho;
    private ListaEnlazada articulosFrecuenciaList; 

    public PalabraClaveNodo(String palabra, String claveResumen) {
        this.palabraClave = palabra;
        this.altura = 1; 
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.articulosFrecuenciaList = new ListaEnlazada(); 
        ArticuloFrecuencia primeraFrecuencia = new ArticuloFrecuencia (claveResumen);
        this.articulosFrecuenciaList.agregar(primeraFrecuencia); 
    }
    
    public String getPalabraClave() {
        return this.palabraClave;
    }
    
    public int getAltura() {
        return this.altura;
    }
    
    public PalabraClaveNodo getHijoIzquierdo() {
        return this.hijoIzquierdo;
    }
    
    public PalabraClaveNodo getHijoDerecho() {
        return this.hijoDerecho;
    }
    
    public ListaEnlazada getArticulosFrecuenciaList() {
        return this.articulosFrecuenciaList;
    }
    
    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    public void setHijoIzquierdo(PalabraClaveNodo hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }
    
    public void setHijoDerecho(PalabraClaveNodo hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public void actualizarFrecuencia(String claveResumen) {
        NodoLista actual = this.articulosFrecuenciaList.getCabeza();
        while (actual != null) {
            ArticuloFrecuencia af = (ArticuloFrecuencia) actual.getDato();
            if (af.getClaveResumen().equals(claveResumen)) {
                af.setFrecuencia(af.getFrecuencia() + 1);
                return;
            }
            actual = actual.getSig();
        }
        
        ArticuloFrecuencia nuevaFrecuencia = new ArticuloFrecuencia(claveResumen);
        this.articulosFrecuenciaList.agregar(nuevaFrecuencia);
    }
    
}
    
    

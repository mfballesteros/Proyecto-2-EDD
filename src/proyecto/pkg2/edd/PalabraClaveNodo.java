/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.edd;

/**Nodo de Árbol AVL para Palabras Clave
 * Representa un nodo en el Árbol AVL de palabras clave
 * Almacena la palabra clave, su altura las referencias a hijos
 * y una lista enlazada con los artículos asociados y la frecuencia
 * @author bettinacarnevali
 */
public class PalabraClaveNodo {
    private String palabraClave;
    private int altura;
    private PalabraClaveNodo hijoIzquierdo;
    private PalabraClaveNodo hijoDerecho;
    private ListaEnlazada articulosFrecuenciaList; 

/**
     * Constructor
     * * @param palabra La palabra clave que se almacenará en el nodo.
     * @param claveResumen El título del resumen donde aparece la palabra por primera vez.
     */
    
    public PalabraClaveNodo(String palabra, String claveResumen) {
        this.palabraClave = palabra;
        this.altura = 1; 
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.articulosFrecuenciaList = new ListaEnlazada(); 
        ArticuloFrecuencia primeraFrecuencia = new ArticuloFrecuencia (claveResumen);
        this.articulosFrecuenciaList.agregar(primeraFrecuencia); 
    }
/**
     * Obtiene la palabra clave almacenada en el nodo.
     * @return La palabra clave.
     */ 
    public String getPalabraClave() {
        return this.palabraClave;
    }
/**
     * Obtiene la altura del nodo en el árbol AVL
     * @return La altura del nodo.
     */
    public int getAltura() {
        return this.altura;
    }
 /**
     * Obtiene el nodo hijo izquierdo
     * @return El nodo hijo izquierdo
     */   
    public PalabraClaveNodo getHijoIzquierdo() {
        return this.hijoIzquierdo;
    }
/**
     * Obtiene el nodo hijo derecho.
     * @return El nodo hijo derecho.
     */    
    public PalabraClaveNodo getHijoDerecho() {
        return this.hijoDerecho;
    }
 /**
     * Obtiene la lista enlazada de artículos y sus frecuencias asociadas a esa palabra clave
     * @return La lista enlazada de ArticuloFrecuencia.
     */   
    public ListaEnlazada getArticulosFrecuenciaList() {
        return this.articulosFrecuenciaList;
    }
/**
     * Establece una nueva altura para el nodo.
     * @param altura La nueva altura.
     */    
    public void setAltura(int altura) {
        this.altura = altura;
    }
 /**
     * modifica un nuevo HI para el nodo.
     * @param hijoIzquierdo sera el nuevo HI 
     */   
    public void setHijoIzquierdo(PalabraClaveNodo hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }
 /**
     * modifica un nuevo HD para el nodo.
     * @param hijoDerecho sera el nuevo HD
     */       
    public void setHijoDerecho(PalabraClaveNodo hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
/**
     * Actualiza la frecuencia de la palabra clave dentro de un resumen específico
     * Si el resumen ya se ecnuentra en la lista de artículos, incrementa su contador.
     * Si no está, agrega un nuevo ArticuloFrecuencia con una frecuencia inicial de 1.
     * * @param claveResumen El título del resumen a actualizar.
     */
    
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
    
    

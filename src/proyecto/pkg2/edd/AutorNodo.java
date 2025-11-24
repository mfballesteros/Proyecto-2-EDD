/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.edd;

/** Representa un nodo dentro del Árbol AVL de Autores
 * Almacena el nombre de un autor 
 * Incluye una lista enlazada interna que alamcena los titulos de investigacion relacionados con el autor
 * @author bettinacarnevali
 */

public class AutorNodo {
    private String nombreAutor;
    private int altura;
    private AutorNodo hijoIzquierdo;
    private AutorNodo hijoDerecho;
    private ListaEnlazada investigacionesList; 

    //CONSTRUCTOR
    public AutorNodo(String nombreAutor, String claveResumen) {
        this.nombreAutor = nombreAutor;
        this.altura = 1; 
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.investigacionesList = new ListaEnlazada();
        this.investigacionesList.agregar(claveResumen);
    }
    
/**
* Obtiene el nombre del autor.
* * @return NombreAutor
*/
    
    public String getNombreAutor() {
        return nombreAutor;
    }
    
/**
* Obtiene la altura actual del nodo en el arbol
* * @return Int de la altura 
*/
    
    public int getAltura() {
        return altura;
    }
    
/**
* Obtiene el nodo hijo izquierdo.
* * @return hijo izquierdo o null si no existe
*/
    public AutorNodo getHijoIzquierdo() {
        return hijoIzquierdo;
    }

/**
 * Obtiene el nodo hijo derecho.
* * @return hijo derecho o null si no existe
*/
    
    public AutorNodo getHijoDerecho() {
        return hijoDerecho;
    }
/**
* Obtiene la lista enlazada que contiene todas las claves de resumen 
* (títulos de investigación) asociadas a este autor
* * @return ListaEnlazada de investigaciones.
*/
    
    public ListaEnlazada getInvestigacionesList() {
        return investigacionesList;
    }

/**
* Establece o actualiza la altura del nodo.
* * @param altura El nuevo valor entero de la altura
*/
    
    public void setAltura(int altura) {
        this.altura = altura;
    }
/**
* Establece el nodo hijo izquierdo.
* * @param hijoIzquierdo El AutorNodo que será el nuevo hijo izquierdo
*/
    public void setHijoIzquierdo(AutorNodo hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

/**
 * Establece el nodo hijo derecho.
* * @param hijoDerecho El AutorNodo que será el nuevo hijo derecho
*/
    
    public void setHijoDerecho(AutorNodo hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
/**
* Agrega una nueva clave de resumen (título de investigación) a la lista 
* interna del autor, solo si esta clave aún no existe en la lista.
* * @param claveResumen La clave del resumen a agregar.
*/    
    public void agregarInvestigacion(String claveResumen) {
        if (!this.investigacionesList.buscar(claveResumen)) {
            this.investigacionesList.agregar(claveResumen);
        }
    }
}
    

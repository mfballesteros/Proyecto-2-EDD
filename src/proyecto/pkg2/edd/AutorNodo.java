/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.edd;

/**
 *
 * @author bettinacarnevali
 */

public class AutorNodo {
    private String nombreAutor;
    private int altura;
    private AutorNodo hijoIzquierdo;
    private AutorNodo hijoDerecho;
    private ListaEnlazada investigacionesList; 

    public AutorNodo(String nombreAutor, String claveResumen) {
        this.nombreAutor = nombreAutor;
        this.altura = 1; 
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.investigacionesList = new ListaEnlazada();
        this.investigacionesList.agregar(claveResumen);
    }
    
    public String getNombreAutor() {
        return nombreAutor;
    }

    public int getAltura() {
        return altura;
    }

    public AutorNodo getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public AutorNodo getHijoDerecho() {
        return hijoDerecho;
    }

    public ListaEnlazada getInvestigacionesList() {
        return investigacionesList;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setHijoIzquierdo(AutorNodo hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public void setHijoDerecho(AutorNodo hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
    
    public void agregarInvestigacion(String claveResumen) {
        if (!this.investigacionesList.buscar(claveResumen)) {
            this.investigacionesList.agregar(claveResumen);
        }
    }
}
    

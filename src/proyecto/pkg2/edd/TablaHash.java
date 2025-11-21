/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.edd;

/**
 *Clase en la que se crea la tabla Hash, crea la tabla como un arreglo de listas 
 * Usa como valor a la clase Resumen
 * Se implementan los metodos agregar elementos y buscar elementos 
 * 
 *
 * @author danie_xe5djpj
 */
// ATRIBUTOS DE LA CLASE
public class TablaHash {
    private ListaEnlazada[] tabla; //cada posicion del array es una lista
    private int capacidad;
    private int tamaño;
    
//CONSTRUCTOR
    public TablaHash(int capacidad) {
        this.tabla = new ListaEnlazada [capacidad];
        this.capacidad = capacidad;
        this.tamaño = 0;
         for (int i = 0; i < capacidad; i++) {
            tabla[i] = new ListaEnlazada();
        }
    }

    /**
     * @return the tabla
     */
    public ListaEnlazada[] getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(ListaEnlazada[] tabla) {
        this.tabla = tabla;
    }

    /**
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * @return the tamaño
     */
    public int getTamaño() {
        return tamaño;
    }

    /**
     * @param tamaño the tamaño to set
     */
    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
    
    
    
    public void AgregarElem (Resumen resumen) {
        int indice = resumen.obtenerHash() % capacidad;
        tabla[indice].agregar(resumen);
    }

    
    
    public Resumen BuscarElem (String titulo) {
        Resumen temp = new Resumen(titulo, "", "", "");
        int indice = temp.obtenerHash() % capacidad;
        
        NodoLista actual = tabla[indice].getCabeza();
        
        while (actual != null) {
            Resumen resumen = (Resumen) actual.getDato();

            if (compararTitulos(resumen.getTitulo(), titulo)) {
                return resumen;
            }
            actual = actual.getSig();
        }
        return null;
    }

    
    
    private boolean compararTitulos(String titulo1, String titulo2) {
        if (titulo1 == null && titulo2 == null) 
            return true;
        if (titulo1 == null || titulo2 == null) 
            return false;
        if (titulo1.length() != titulo2.length()) 
            return false;
        
        for (int i = 0; i < titulo1.length(); i++) {
            if (titulo1.charAt(i) != titulo2.charAt(i)) 
                return false;
        }
        return true;
    }
}
    
    
    

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
    
    
    
    
    public boolean AgregarElem (Resumen resumen) {
        if (BuscarElem(resumen.getTitulo()) != null) {
            return false;
    }
        int indice = resumen.obtenerHash() % capacidad;
        if (indice < 0) indice *= -1;
        tabla[indice].agregar(resumen);
        tamaño++;
        return true;
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
    
    public String[] obtenerTitulos() {
        java.util.ArrayList<String> listaTitulos = new java.util.ArrayList<>();

        for (int i = 0; i < this.capacidad; i++) {
            NodoLista actual = this.tabla[i].getCabeza(); 

            while (actual != null) {
             
                Resumen r = (Resumen) actual.getDato(); 
                listaTitulos.add(r.getTitulo());
                
                actual = actual.getSig(); 
            }
        }
        return listaTitulos.toArray(new String[0]);
    }
    private class Entrada {
    String clave;
    Object valor; // Aquí guardaremos la ListaEnlazada de títulos

    public Entrada(String clave, Object valor) {
        this.clave = clave;
        this.valor = valor;
        }
    }
    private int obtenerHashString(String texto) {
    int hash = 0;
    // Algoritmo estándar (x31) para dispersar bien las letras
    for (int i = 0; i < texto.length(); i++) {
        hash = (31 * hash + texto.charAt(i)) % capacidad;
    }
    if (hash < 0) hash *= -1; // Evitamos negativos
    return hash;
    }
    public void insertar(String clave, Object valor) {
    int indice = obtenerHashString(clave);
    
    // Envolvemos los datos en la clase Entrada para no perder la clave
    Entrada nuevaEntrada = new Entrada(clave, valor);
    
    // Usamos tu método existente .agregar() de la lista enlazada
    tabla[indice].agregar(nuevaEntrada);
    }
    public Object buscar(String claveBuscada) {
    int indice = obtenerHashString(claveBuscada);
    
    // Obtenemos la cabeza de la lista en esa posición
    NodoLista actual = tabla[indice].getCabeza();

    // Recorremos la lista por si hubo colisiones
    while (actual != null) {
        Object dato = actual.getDato();
        
        // Verificamos si lo que hay ahí es una de nuestras Entradas
        if (dato instanceof Entrada) {
            Entrada e = (Entrada) dato;
            // Comparamos si es la palabra que buscamos
            if (e.clave.equalsIgnoreCase(claveBuscada)) {
                return e.valor; // ¡Encontrado! Devolvemos la lista de títulos
            }
        }
        actual = actual.getSig();
     }
    return null; // No existe esa palabra
    }
    
}
    
    
    

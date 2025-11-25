/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.edd;
import java.io.Serializable;
import java.io.*;
/**
 *Clase en la que se crea la tabla Hash, crea la tabla como un arreglo de listas 
 * Usa como valor a la clase Resumen
 * Se implementan los metodos agregar elementos y buscar elementos 
 * 
 *
 * @author danie_xe5djpj
 */
// ATRIBUTOS DE LA CLASE
public class TablaHash implements Serializable{
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

    /** obtiene el arreglo de listas enlazadas
     * @return the tabla
     */
    public ListaEnlazada[] getTabla() {
        return tabla;
    }

/**
     * Modifica el arreglo de la tabla
     * @param tabla El nuevo arreglo de ListasEnlazada a establecer.
     */
    public void setTabla(ListaEnlazada[] tabla) {
        this.tabla = tabla;
    }

    /** Obtiene la capacidad del arreglo de la tabla
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /** Modifica la capacidad del arreglo de la tabla
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /** arroja el numero de elementos en la tabla almacenados
     * @return the tamaño
     */
    public int getTamaño() {
        return tamaño;
    }

    /** Modifica el contador de elementos almacenados
     * @param tamaño the tamaño to set
     */
    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
    
/**
     * Agrega un objeto (Resumen) a la tabla hash principal
     * Utiliza el título del resumen para calcular el indice hash
     * @param resumen El objeto Resumen a insertar.
     * @return true si el resumen fue agregado exitosamente (no era duplicado), false en caso contrario.
     */    

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
/**
     * Busca un (Resumen)en la tabla hash utilizando su titulo.
     * @param titulo El título del resumen a buscar
     * @return El objeto Resumen encontrado, o null si no se encuentra
     */
    
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

/**
     * Método para realizar una comparación de dos titulos
     * @param titulo1 El primer titulo.
     * @param titulo2 El segundo titulo.
     * @return true si los titulos son iguales, false caso contrario
     */    
    
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
 /**
     * Recorre la Hash Table y extrae todos los títulos de los resumenes almacenados
     * @return Un arreglo de Strings que contiene todos los titulos de los resumenes.
     */
    
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
    
/**
     * Clase utilizada para almacenar pares clave-valor dentro de la Tabla Hash
     */   
    
    private class Entrada {
    String clave;
    Object valor; // Aquí guardaremos la ListaEnlazada de títulos
//CONSTRUCTOR
    public Entrada(String clave, Object valor) {
        this.clave = clave;
        this.valor = valor;
        }
    }
    
/**
     * Calcula el índice hash para una cadena de texto 
     * Utiliza un algoritmo de hash y aplica el modulo de la capacidad de la tabla.
     * @param texto La CLAVE
     * @return El índice (posición) dentro del arreglo de la tabla.
     */
    
    private int obtenerHashString(String texto) {
    int hash = 0;
    // Algoritmo estándar (x31) para dispersar bien las letras
    for (int i = 0; i < texto.length(); i++) {
        hash = (31 * hash + texto.charAt(i)) % capacidad;
    }
    if (hash < 0) hash *= -1; // Evitamos negativos
    return hash;
    }
    
/**
     * Inserta una entrada (clave-valor) en la tabla hash. 
     * En caso de colisión, se agrega al final de la lista enlazada en ese indice.
     * @param clave La clave a insertar
     * @param valor El valor asociado a la clave 
     */   
    
    public void insertar(String clave, Object valor) {
    int indice = obtenerHashString(clave);
    Entrada nuevaEntrada = new Entrada(clave, valor);
    tabla[indice].agregar(nuevaEntrada);
    }

/**
     * Busca el valor asociado a una clave específica en la tabla 
     * Recorre la Lista Enlazada en la posición hash calculada 
     * @param claveBuscada La clave a buscar
     * @return El objeto valor asociado a la clave 
     */
    
    public Object buscar(String claveBuscada) {
    int indice = obtenerHashString(claveBuscada);
    NodoLista actual = tabla[indice].getCabeza();
    while (actual != null) {
        Object dato = actual.getDato();
        if (dato instanceof Entrada) {
            Entrada e = (Entrada) dato;
            if (e.clave.equalsIgnoreCase(claveBuscada)) {
                return e.valor; 
            }
        }
        actual = actual.getSig();
     }
    return null;
    }
    
 /**
     * Guarda el estado actual de la tabla Hash, los archivos contenidos
     * * Implementada en la interfaz 
     * @param nombreArchivo El nombre del archivo donde se serializará la tabla 
     * @return true si el guardado fue exitoso
     */ 
    
    public boolean guardarTabla(String nombreArchivo) {
        try {
            FileOutputStream archivo = new FileOutputStream(nombreArchivo);
            ObjectOutputStream salida = new ObjectOutputStream(archivo);
            salida.writeObject(this);
            salida.close();
            archivo.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar la tabla: " + e.getMessage());
            return false;
        }
    }
/**
     * Carga una Tabla Hash previamente guardada (serializada) desde un archivo
     * @param nombreArchivo El nombre del archivo serializado a cargar 
     * @param capacidad La capacidad inicial a usar si se debe crear una nueva tabla vacia
     * @return Tabla Hash cargada desde el archivo
     */
    
    public static TablaHash cargarTabla(String nombreArchivo, int capacidad) {
    try {
        FileInputStream archivo = new FileInputStream(nombreArchivo);
        ObjectInputStream entrada = new ObjectInputStream(archivo);
        TablaHash tablaCargada = (TablaHash) entrada.readObject();
        entrada.close();
        archivo.close();
        System.out.println("Tabla hash cargada exitosamente desde el archivo.");
        return tablaCargada;
    } catch (FileNotFoundException e) {
        System.out.println("Archivo de repositorio no encontrado. Creando nueva tabla...");
        return new TablaHash(capacidad);
    } catch (IOException | ClassNotFoundException e) {
        System.err.println("Error al cargar la tabla. Creando nueva tabla: " + e.getMessage());
        return new TablaHash(capacidad);
    }
}
}
    
 
    
    

    
    
    

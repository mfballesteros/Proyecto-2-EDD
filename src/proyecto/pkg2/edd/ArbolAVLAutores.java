/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.edd;

/** Implementacion de arbol AVL que guarda y maneja los autores de las investigaciones
 *Cada nodo almacena el nombre del autor, si existe se agrega a una lista 
 * Este arbol se mantiene balanceado automaticamente
 * @author bettinacarnevali
 */

public class ArbolAVLAutores {
    private AutorNodo raiz;
    
//CONSTRUCTOR 
    public ArbolAVLAutores() {
        this.raiz = null;
    }


 /** Inserta un nuevo autor y su investigacion respectiva
 ** @param nombreAutor nombre del Autor a insertar
 * @param tituloInvestigacion titulo de la investigacion del autor
 */
    
    public void insertarAutor(String nombreAutor, String tituloInvestigacion) {
        raiz = insertar(raiz, nombreAutor, tituloInvestigacion);
    }

    // --- 2. LÓGICA INTERNA DE INSERCIÓN Y BALANCEO ---
    private AutorNodo insertar(AutorNodo nodo, String nombre, String titulo) {
if (nombre != null) {
        nombre = nombre.trim().replaceAll("\\r", "");
    }
    // -------------------------------------

    // 1. Inserción normal de BST
    if (nodo == null) {
        return new AutorNodo(nombre, titulo);
    }

    // Usamos compareToIgnoreCase para ordenar alfabéticamente
    int comparacion = nombre.compareToIgnoreCase(nodo.getNombreAutor());

    if (comparacion < 0) {
        nodo.setHijoIzquierdo(insertar(nodo.getHijoIzquierdo(), nombre, titulo));
    } else if (comparacion > 0) {
        nodo.setHijoDerecho(insertar(nodo.getHijoDerecho(), nombre, titulo));
    } else {
        // CASO CLAVE: El autor YA EXISTE.
        // Aquí es donde evitamos el duplicado visual.
        nodo.agregarInvestigacion(titulo);
        return nodo; 
    }

    // 2. Actualizar altura
    nodo.setAltura(1 + Math.max(obtenerAltura(nodo.getHijoIzquierdo()), obtenerAltura(nodo.getHijoDerecho())));

    // 3. Obtener factor de equilibrio
    int balance = obtenerEquilibrio(nodo);

    // 4. Casos de Rotación (Balanceo AVL)
    
    // Caso Izquierda-Izquierda
    if (balance > 1 && nombre.compareToIgnoreCase(nodo.getHijoIzquierdo().getNombreAutor()) < 0) {
        return rotacionDerecha(nodo);
    }

    // Caso Derecha-Derecha
    if (balance < -1 && nombre.compareToIgnoreCase(nodo.getHijoDerecho().getNombreAutor()) > 0) {
        return rotacionIzquierda(nodo);
    }

    // Caso Izquierda-Derecha
    if (balance > 1 && nombre.compareToIgnoreCase(nodo.getHijoIzquierdo().getNombreAutor()) > 0) {
        nodo.setHijoIzquierdo(rotacionIzquierda(nodo.getHijoIzquierdo()));
        return rotacionDerecha(nodo);
    }

    // Caso Derecha-Izquierda
    if (balance < -1 && nombre.compareToIgnoreCase(nodo.getHijoDerecho().getNombreAutor()) < 0) {
        nodo.setHijoDerecho(rotacionDerecha(nodo.getHijoDerecho()));
        return rotacionIzquierda(nodo);
    }

    return nodo;
    }

    // --- 3. MÉTODOS DE BÚSQUEDA (O(log n)) ---
    
 /** Arroja la lista de investigaciones del autor buscado 
 ** @param nombreAutor nombre del Autor a buscar
 * @return ListaEnlazada con los títulos de investigación del autor
 */
    
    public ListaEnlazada buscarInvestigacionesDe(String nombreAutor) {
        AutorNodo nodo = buscarNodo(raiz, nombreAutor);
        if (nodo != null) {
            return nodo.getInvestigacionesList();
        }
        return null;
    }
    
    // Método auxiliar recursivo para buscar
    private AutorNodo buscarNodo(AutorNodo nodo, String nombre) {
        if (nodo == null) return null;
        
        int cmp = nombre.compareToIgnoreCase(nodo.getNombreAutor());
        
        if (cmp < 0) return buscarNodo(nodo.getHijoIzquierdo(), nombre);
        else if (cmp > 0) return buscarNodo(nodo.getHijoDerecho(), nombre);
        else return nodo; // Encontrado
    }

    // --- 4. MÉTODO PARA LISTADO ALFABÉTICO (RECORRIDO IN-ORDER) ---
    
 /** Arroja arreglo de strings con los autores ordenados alfabeticamente (inorden) 
 ** @return Un arreglo de strings con nombres de autores
 */
        
    
    public String[] obtenerAutoresOrdenados() {
        ListaEnlazada listaNombres = new ListaEnlazada();
        inOrderRec(raiz, listaNombres);
        
        // Convertimos el ArrayList a un arreglo de Strings simple para el JComboBox
        return listaNombres.toArrayString();
    }

    private void inOrderRec(AutorNodo nodo,ListaEnlazada lista) {
        if (nodo != null) {
            inOrderRec(nodo.getHijoIzquierdo(), lista);
            lista.agregar(nodo.getNombreAutor()); // Visita la raíz (guarda nombre)
            inOrderRec(nodo.getHijoDerecho(), lista);
        }
    }

    // --- 5. MÉTODOS AUXILIARES DE AVL (Altura y Rotaciones) ---

    private int obtenerAltura(AutorNodo n) {
        return (n == null) ? 0 : n.getAltura();
    }

    private int obtenerEquilibrio(AutorNodo n) {
        return (n == null) ? 0 : obtenerAltura(n.getHijoIzquierdo()) - obtenerAltura(n.getHijoDerecho());
    }

    private AutorNodo rotacionDerecha(AutorNodo y) {
        AutorNodo x = y.getHijoIzquierdo();
        AutorNodo T2 = x.getHijoDerecho();

        // Realizar rotación
        x.setHijoDerecho(y);
        y.setHijoIzquierdo(T2);

        // Actualizar alturas
        y.setAltura(Math.max(obtenerAltura(y.getHijoIzquierdo()), obtenerAltura(y.getHijoDerecho())) + 1);
        x.setAltura(Math.max(obtenerAltura(x.getHijoIzquierdo()), obtenerAltura(x.getHijoDerecho())) + 1);

        return x; // Nueva raíz
    }

    private AutorNodo rotacionIzquierda(AutorNodo x) {
        AutorNodo y = x.getHijoDerecho();
        AutorNodo T2 = y.getHijoIzquierdo();

        // Realizar rotación
        y.setHijoIzquierdo(x);
        x.setHijoDerecho(T2);

        // Actualizar alturas
        x.setAltura(Math.max(obtenerAltura(x.getHijoIzquierdo()), obtenerAltura(x.getHijoDerecho())) + 1);
        y.setAltura(Math.max(obtenerAltura(y.getHijoIzquierdo()), obtenerAltura(y.getHijoDerecho())) + 1);

        return y; // Nueva raíz
    }
}

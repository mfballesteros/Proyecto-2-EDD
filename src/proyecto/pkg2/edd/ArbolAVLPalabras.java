/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.edd;

import java.text.Collator; // Necesario para la comparación en español
import java.util.Locale;   // Necesario para especificar el idioma (es, ES)

/**Implementacion de arbol AVL para guardar y usar palabras claves dentro de repo de articulos
 * Los nodos almacenan la frecuencia de cada palabra clave por artículo, actualizando la frecuencia
 * si la palabra ya existe.
 * @author bettinacarnevali
 */
public class ArbolAVLPalabras {
    PalabraClaveNodo raiz;
    private final Collator comparadorEspanol;
    
    //CONSTRUCTOR
    public ArbolAVLPalabras() {
        this.raiz = null;
        this.comparadorEspanol = Collator.getInstance(new Locale("es", "ES"));
        this.comparadorEspanol.setStrength(Collator.PRIMARY); 
    }
    
    private int obtenerAltura(PalabraClaveNodo nodo) {
        return (nodo == null) ? 0 : nodo.getAltura();
    }
    
    
/**
     * Inserta una nueva palabra clave en el árbol o actualiza su frecuencia
     * *.
     * * @param palabra La palabra clave a insertar o actualizar
     * @param claveResumen La clave Unica del resumen o articulo al que pertenece la palabra
     */

    
    public void insertarOActualizar(String palabra, String claveResumen) {
        this.raiz = insertarRecursivo(this.raiz, palabra, claveResumen);
    }
    
    private PalabraClaveNodo insertarRecursivo(PalabraClaveNodo actual, String palabra, String claveResumen) {
        if (actual == null) {
            return new PalabraClaveNodo(palabra, claveResumen); 
        }

        int resultadoComparacion = this.comparadorEspanol.compare(palabra, actual.getPalabraClave());

        if (resultadoComparacion < 0) {
            actual.setHijoIzquierdo(insertarRecursivo(actual.getHijoIzquierdo(), palabra, claveResumen));
        } else if (resultadoComparacion > 0) {
            actual.setHijoDerecho(insertarRecursivo(actual.getHijoDerecho(), palabra, claveResumen));
        } else {
            actual.actualizarFrecuencia(claveResumen);
            return actual;
        }
        
        actual.setAltura(1 + Math.max(obtenerAltura(actual.getHijoIzquierdo()), obtenerAltura(actual.getHijoDerecho())));
        return balancear(actual);
    }
    
    private int obtenerFactorBalanceo(PalabraClaveNodo nodo) {
        if (nodo == null) {
            return 0;
        }
        // Altura Izquierda - Altura Derecha
        return obtenerAltura(nodo.getHijoIzquierdo()) - obtenerAltura(nodo.getHijoDerecho());
    }
    
    private PalabraClaveNodo rotarDerecha(PalabraClaveNodo y) {
        PalabraClaveNodo x = y.getHijoIzquierdo();
        PalabraClaveNodo T2 = x.getHijoDerecho();

        //Realizar la rotación (cambio de punteros)
        x.setHijoDerecho(y);
        y.setHijoIzquierdo(T2);

        // Actualizar alturas, primero el nodo 'y', luego el nuevo padre 'x')
        y.setAltura(1 + Math.max(obtenerAltura(y.getHijoIzquierdo()), obtenerAltura(y.getHijoDerecho())));
        x.setAltura(1 + Math.max(obtenerAltura(x.getHijoIzquierdo()), obtenerAltura(x.getHijoDerecho())));
         return x;
    }
    
    private PalabraClaveNodo rotarIzquierda(PalabraClaveNodo x) {
        PalabraClaveNodo y = x.getHijoDerecho();
        PalabraClaveNodo T2 = y.getHijoIzquierdo();
        
        y.setHijoIzquierdo(x);
        x.setHijoDerecho(T2);
        
        x.setAltura(1 + Math.max(obtenerAltura(x.getHijoIzquierdo()), obtenerAltura(x.getHijoDerecho())));
        y.setAltura(1 + Math.max(obtenerAltura(y.getHijoIzquierdo()), obtenerAltura(y.getHijoDerecho())));

      
        return y;
    }
    
    
    private PalabraClaveNodo balancear(PalabraClaveNodo actual) {
        actual.setAltura(1 + Math.max(obtenerAltura(actual.getHijoIzquierdo()), obtenerAltura(actual.getHijoDerecho())));
        int factorBalanceo = obtenerFactorBalanceo(actual);

        if (factorBalanceo > 1) {
            
            if (obtenerFactorBalanceo(actual.getHijoIzquierdo()) >= 0) {
                return rotarDerecha(actual);
            }
            if (obtenerFactorBalanceo(actual.getHijoIzquierdo()) < 0) {
                actual.setHijoIzquierdo(rotarIzquierda(actual.getHijoIzquierdo()));
                return rotarDerecha(actual);
            }
        }

        if (factorBalanceo < -1) {
            if (obtenerFactorBalanceo(actual.getHijoDerecho()) <= 0) {
                return rotarIzquierda(actual);
            }
            if (obtenerFactorBalanceo(actual.getHijoDerecho()) > 0) {
                actual.setHijoDerecho(rotarDerecha(actual.getHijoDerecho()));
                return rotarIzquierda(actual);
            }
        }
        return actual;
    }
    
// Métodos públicos para iniciar los recorridos desde la raíz
    /**
     * Realiza un recorrido Preorden (R, H Izquierdo,H Derecho) sobre el árbol 
     * imprime las palabras clave en la consola
     */

    public void recorrerPreorden() {
        System.out.print("Preorden: ");
        recorrerPreordenRecursivo(this.raiz);
        System.out.println();
    }
    
    /**
     * Realiza un recorrido Inorden ( H Izquierdo, R, H Derecho) sobre el árbol 
     * imprime las palabras clave en la consola, Este se muestra en orden alfabetico
     */
    public void recorrerInorden() {
        System.out.print("Inorden: ");
        recorrerInordenRecursivo(this.raiz);
        System.out.println();
    }
    
    /**
     * Realiza un recorrido Postorden ( H Izquierdo,H Derecho, R) sobre el árbol 
     * imprime las palabras clave en la consola
     */
    
    public void recorrerPostorden() {
        System.out.print("Postorden: ");
        recorrerPostordenRecursivo(this.raiz);
        System.out.println();
    }

    // Métodos privados recursivos
    private void recorrerPreordenRecursivo(PalabraClaveNodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.getPalabraClave() + " ");
            recorrerPreordenRecursivo(nodo.getHijoIzquierdo());
            recorrerPreordenRecursivo(nodo.getHijoDerecho());
        }
    }

    private void recorrerInordenRecursivo(PalabraClaveNodo nodo) {
        if (nodo != null) {
            recorrerInordenRecursivo(nodo.getHijoIzquierdo());
            System.out.print(nodo.getPalabraClave() + " "); // ¡Aquí se verifica el orden!
            recorrerInordenRecursivo(nodo.getHijoDerecho());
        }
    }

    private void recorrerPostordenRecursivo(PalabraClaveNodo nodo) {
        if (nodo != null) {
            recorrerPostordenRecursivo(nodo.getHijoIzquierdo());
            recorrerPostordenRecursivo(nodo.getHijoDerecho());
            System.out.print(nodo.getPalabraClave() + " ");
        }
    }
    
/**
     * Crea y arroja ListaEnlazada que contiene todos los nodos 
     *  toma la PalabraClaveNodo del árbol, ordenados alfabéticamente (recorrido Inorden).
     * * @return Una ListaEnlazada con los nodos en orden alfabético
     */    
    
    public ListaEnlazada obtenerListaOrdenada() {

    ListaEnlazada listaRetorno = new ListaEnlazada();
    llenarListaInOrder(this.raiz, listaRetorno);
    return listaRetorno;
}

private void llenarListaInOrder(PalabraClaveNodo nodo, ListaEnlazada lista) {
    if (nodo != null) {
        llenarListaInOrder(nodo.getHijoIzquierdo(), lista);
        lista.agregar(nodo); 
        llenarListaInOrder(nodo.getHijoDerecho(), lista);
    }
  }

/**
     * Busca la palabra clave y el articulo y fija su frecuencia en cero. 
     * * @param palabra La palabra clave cuyo contador se debe modificar
     * @param tituloResumen La clave del resumen o artículo que contiene la palabra
     */

public void fijarFrecuenciaEnCero(String palabra, String tituloResumen) {
        PalabraClaveNodo nodo = buscarNodo(this.raiz, palabra);
        
        if (nodo != null) {
            NodoLista aux = nodo.getArticulosFrecuenciaList().getCabeza();
            while (aux != null) {
                ArticuloFrecuencia af = (ArticuloFrecuencia) aux.getDato();

                if (af.getClaveResumen().equals(tituloResumen)) {
                    af.setFrecuencia(0);
                    return; 
                }
                aux = aux.getSig();
            }
        }
    }

    private PalabraClaveNodo buscarNodo(PalabraClaveNodo actual, String palabra) {
        if (actual == null) return null;
        
        int comparacion = this.comparadorEspanol.compare(palabra, actual.getPalabraClave());
        
        if (comparacion == 0) return actual;
        
        return (comparacion < 0) 
            ? buscarNodo(actual.getHijoIzquierdo(), palabra) 
            : buscarNodo(actual.getHijoDerecho(), palabra);
    }
}

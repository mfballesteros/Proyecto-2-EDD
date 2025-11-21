/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.edd;

import java.text.Collator; // Necesario para la comparación en español
import java.util.Locale;   // Necesario para especificar el idioma (es, ES)
/**
 *
 * @author bettinacarnevali
 */
public class ArbolAVLPalabras {
    PalabraClaveNodo raiz;
    private final Collator comparadorEspanol;
    
    public ArbolAVLPalabras() {
        this.raiz = null;
        this.comparadorEspanol = Collator.getInstance(new Locale("es", "ES"));
        this.comparadorEspanol.setStrength(Collator.PRIMARY); 
    }
    
    private int obtenerAltura(PalabraClaveNodo nodo) {
        return (nodo == null) ? 0 : nodo.getAltura();
    }
    
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
    public void recorrerPreorden() {
        System.out.print("Preorden: ");
        recorrerPreordenRecursivo(this.raiz);
        System.out.println();
    }

    public void recorrerInorden() {
        System.out.print("Inorden: ");
        recorrerInordenRecursivo(this.raiz);
        System.out.println();
    }

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
}

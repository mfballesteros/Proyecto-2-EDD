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
        
        return actual; 
    }
}

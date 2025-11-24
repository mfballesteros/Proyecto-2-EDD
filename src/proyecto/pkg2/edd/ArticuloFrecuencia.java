/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.edd;

/**Guarda el registro de frecuencia con una palabra clave que aparece en un resumen
 * Esto se guarda en la lista de cada nodo de palabra clave del ArbolAVLPalabras para
 * rastrear las apariciones por documento
 * 
 * @author bettinacarnevali
 */
public class ArticuloFrecuencia {
    private String claveResumen;
    private int frecuencia;

    //CONSTRUCTOR
    public ArticuloFrecuencia(String claveResumen) {
        this.claveResumen = claveResumen;
        this.frecuencia = 1;
    }

    /**Obtiene la clave del resumen al que pertenece la frecuencia
     * @return the claveResumen
     */
    public String getClaveResumen() {
        return claveResumen;
    }

    /** Obtiene el valor actual de la frecuencia con la que la palabra aparece en el artículo
     * @return the frecuencia
     */
    public int getFrecuencia() {
        return frecuencia;
    }

    /**Cambia el valor de la frecuencia lo modifica
     * @param frecuencia the frecuencia to set
     */
    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    
    
    
    
}

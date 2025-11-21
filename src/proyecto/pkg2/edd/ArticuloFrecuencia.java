/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.edd;

/**
 *
 * @author bettinacarnevali
 */
public class ArticuloFrecuencia {
    private String claveResumen;
    private int frecuencia;

    public ArticuloFrecuencia(String claveResumen) {
        this.claveResumen = claveResumen;
        this.frecuencia = 1;
    }

    /**
     * @return the claveResumen
     */
    public String getClaveResumen() {
        return claveResumen;
    }

    /**
     * @return the frecuencia
     */
    public int getFrecuencia() {
        return frecuencia;
    }

    /**
     * @param frecuencia the frecuencia to set
     */
    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.edd;

/** Resumen de Artículo Científico
 * Representa la información clave de un artículo científico (Título, Autores, Resumen y Palabras Clave).
 * Esta clase se utiliza como el valor principal almacenado en la Tabla Hash, siendo el Título su identificador clave.
 * @author danie_xe5djpj
 */
public class Resumen { //ESTE SERA EL VALOR DEL KEY VALUE EN HASHTABLE
    private String titulo;
    private String autores;
    private String resumen;
    private String pclaves;
    
//CONSTRUCTOR
    public Resumen(String titulo, String autores, String resumen, String pclaves) {
        this.titulo = titulo;
        this.autores = autores;
        this.resumen = resumen;
        this.pclaves = pclaves;
    }

    /**accede al titulo del resumen
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /** modifica el titulo del resumen
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**accede a la cadena de autores
     * @return the autores
     */
    public String getAutores() {
        return autores;
    }

    /** modifica cadena de autores
     * @param autores the autores to set
     */
    public void setAutores(String autores) {
        this.autores = autores;
    }

    /** accede al cuerpo del resumen
     * @return the resumen
     */
    public String getResumen() {
        return resumen;
    }

    /** modifica el cuerpo del resumen
     * @param resumen the resumen to set
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /** accede a la cadena de palabras clave
     * @return the pclaves
     */
    public String getPclaves() {
        return pclaves;
    }

    /** modifica cadena de palabras clave
     * @param pclaves the pclaves to set
     */
    public void setPclaves(String pclaves) {
        this.pclaves = pclaves;
    }
    
/**
     * Compara si este resumen es igual a otro
     * Verifica todos los atributos
     * Maneja duplicados en la Hash Table
     * @param otro El objeto Resumen con el que se va a comparar (la clase)
     * @return true si todos los atributos de la clase son iguales, false en caso contrario
     */
    
    //METODOS EN CLASE RESUMEN (KEY DEL HASH TABLE), LEERA ARCHIVOS
    
    //SI UN STRING IGUAL AL OTRO
    public boolean esIgual(Resumen otro) { //maneja duplicados
        if (this == otro) {
            return true;
        }
        if (otro == null) {
            return false;
        }
        
        if (!compararStrings(this.titulo, otro.titulo)) {
            return false;
        }
        if (!compararStrings(this.autores, otro.autores)) {
            return false;
        }
        if (!compararStrings(this.resumen, otro.resumen)) {
            return false;
        }
        if (!compararStrings(this.pclaves, otro.pclaves)) {
            return false;
        }
        return true;
    }

/**
     * Genera una funcion HASH, decide donde guardar el titulo del resumen
     * Esta función se utiliza para determinar la posición inicial en la Tabla Hash.
     * @return El código hash (int) calculado a partir del titulo.
     */
    public int obtenerHash() { //funcion hash, DECIDE DONDE GUARDAR en base al titulo
        if (titulo == null) return 0;
        
        int hash = 19;
        for (int i = 0; i < titulo.length(); i++) {
            hash = (hash * 41) + titulo.charAt(i);
        }
        if (hash < 0) hash = -hash;
        return hash;
    }

/**
     * Método  para comparar dos cadenas de texto
     * Maneja casos nulos y compara caracter por caracter.
     * @param s1 Primera cadena.
     * @param s2 Segunda cadena.
     * @return true si ambas cadenas son idénticas o ambas son nulas; false en caso contrario.
     */    
    private boolean compararStrings(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) return false;
        }
        return true;
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.edd;

/**
 *
 * @author danie_xe5djpj
 */
public class Resumen { //ESTE SERA EL VALOR DEL KEY VALUE EN HASHTABLE
    private String titulo;
    private String autores;
    private String resumen;
    private String pclaves;

    public Resumen(String titulo, String autores, String resumen, String pclaves) {
        this.titulo = titulo;
        this.autores = autores;
        this.resumen = resumen;
        this.pclaves = pclaves;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the autores
     */
    public String getAutores() {
        return autores;
    }

    /**
     * @param autores the autores to set
     */
    public void setAutores(String autores) {
        this.autores = autores;
    }

    /**
     * @return the resumen
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * @param resumen the resumen to set
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /**
     * @return the pclaves
     */
    public String getPclaves() {
        return pclaves;
    }

    /**
     * @param pclaves the pclaves to set
     */
    public void setPclaves(String pclaves) {
        this.pclaves = pclaves;
    }
    
    
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


    public int obtenerHash() { //funcion hash, DECIDE DONDE GUARDAR en base al titulo
        if (titulo == null) return 0;
        
        int hash = 19;
        for (int i = 0; i < titulo.length(); i++) {
            hash = (hash * 41) + titulo.charAt(i);
        }
        if (hash < 0) hash = -hash;
        return hash;
    }

    
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


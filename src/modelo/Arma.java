/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Arma {
    // Atributos exigidos por Ley 30299 (SUCAMEC)

    private String numSerie;
    private String marca;
    private String modelo;
    private String calibre;
    private String numTarjetaSucamec;
    private String estado;        // "DISPONIBLE" | "ASIGNADA" | "MANTENIMIENTO"
    private String fechaIngreso;

    // Constructor
    public Arma(String numSerie, String marca, String modelo,
            String calibre, String numTarjetaSucamec, String fechaIngreso) {
        this.numSerie = numSerie;
        this.marca = marca;
        this.modelo = modelo;
        this.calibre = calibre;
        this.numTarjetaSucamec = numTarjetaSucamec;
        this.estado = "DISPONIBLE"; // estado inicial
        this.fechaIngreso = fechaIngreso;
    }

    // Getters
    public String getNumSerie() {
        return numSerie;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCalibre() {
        return calibre;
    }

    public String getNumTarjetaSucamec() {
        return numTarjetaSucamec;
    }

    public String getEstado() {
        return estado;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    // Setter solo para estado (el único que cambia en operación)
    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Método utilitario — para mostrar en tablas Swing
    @Override
    public String toString() {
        return numSerie + " | " + marca + " " + modelo
                + " | Cal. " + calibre + " | " + estado;
    }
}

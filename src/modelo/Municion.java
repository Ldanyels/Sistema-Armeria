/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Municion {

    private String tipo;       // "PLOMO", "GOMA", "FOGUEO"
    private String calibre;    // ".38", "9mm", ".40"
    private int cantidad;   // stock actual en unidades
    private String lote;       // número de lote del proveedor
    private String fechaIngreso;

    public Municion(String tipo, String calibre,
            int cantidad, String lote, String fechaIngreso) {
        this.tipo = tipo;
        this.calibre = calibre;
        this.cantidad = cantidad;
        this.lote = lote;
        this.fechaIngreso = fechaIngreso;
    }

    // Getters
    public String getTipo() {
        return tipo;
    }

    public String getCalibre() {
        return calibre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getLote() {
        return lote;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    // La cantidad SÍ cambia con ingresos y salidas
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Incrementar stock (ingreso)
    public void agregarStock(int cantidad) {
        this.cantidad += cantidad;
    }

    // Reducir stock (salida) — valida que no quede negativo
    public boolean retirarStock(int cantidad) {
        if (cantidad > this.cantidad) {
            return false; // no hay suficiente stock
        }
        this.cantidad -= cantidad;
        return true;
    }

    @Override
    public String toString() {
        return calibre + " " + tipo + " — Stock: " + cantidad + " und | Lote: " + lote;
    }
}

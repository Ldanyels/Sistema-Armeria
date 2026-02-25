/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Movimiento {

    // Tipos de movimiento posibles
    public static final String INGRESO_ARMA = "INGRESO_ARMA";
    public static final String INGRESO_MUNICION = "INGRESO_MUNICION";
    public static final String SALIDA_ASIGNACION = "SALIDA_ASIGNACION";
    public static final String DEVOLUCION = "DEVOLUCION";

    private int id;            // número correlativo
    private String tipo;          // usar las constantes de arriba
    private String descripcion;   // detalle del movimiento
    private String docSustento;   // número de documento de sustento
    private String fechaHora;     // fecha y hora del registro
    private String usuarioRegistro; // quién lo registró

    public Movimiento(int id, String tipo, String descripcion,
            String docSustento, String fechaHora,
            String usuarioRegistro) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.docSustento = docSustento;
        this.fechaHora = fechaHora;
        this.usuarioRegistro = usuarioRegistro;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDocSustento() {
        return docSustento;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }
}

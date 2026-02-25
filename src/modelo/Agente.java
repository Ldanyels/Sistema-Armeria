/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Agente {

    private String dni;
    private String nombreCompleto;
    private String numLicenciaSucamec;  // exigido por Ley 28879
    private String vencimientoLicencia; // formato "DD/MM/YYYY"
    private String puesto;              // puesto o sede donde trabaja
    private boolean activo;

    public Agente(String dni, String nombreCompleto,
            String numLicenciaSucamec, String vencimientoLicencia,
            String puesto) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.numLicenciaSucamec = numLicenciaSucamec;
        this.vencimientoLicencia = vencimientoLicencia;
        this.puesto = puesto;
        this.activo = true;
    }

    // Getters
    public String getDni() {
        return dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getNumLicenciaSucamec() {
        return numLicenciaSucamec;
    }

    public String getVencimientoLicencia() {
        return vencimientoLicencia;
    }

    public String getPuesto() {
        return puesto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean a) {
        this.activo = a;
    }

    @Override
    public String toString() {
        return dni + " | " + nombreCompleto + " | Lic: " + numLicenciaSucamec;
    }
}

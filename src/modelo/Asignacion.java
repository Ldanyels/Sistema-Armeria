/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Asignacion {

    private int id;
    private Arma arma;// el arma entregada
    private Agente agente;// quién la recibe
    private int municionEnviada; // unidades de munición entregadas
    private String fechaSalida;// "DD/MM/YYYY HH:mm"
    private String fechaRetorno;// null si aún no devolvió
    private String docSustento;// número de orden de servicio
    private String puesto;// lugar de destino del agente
    private boolean activa;// true = no devuelta aún
    private String observaciones;

    public Asignacion(int id, Arma arma, Agente agente,
            int municionEnviada, String fechaSalida,
            String docSustento, String puesto) {
        this.id = id;
        this.arma = arma;
        this.agente = agente;
        this.municionEnviada = municionEnviada;
        this.fechaSalida = fechaSalida;
        this.docSustento = docSustento;
        this.puesto = puesto;
        this.activa = true;
        this.fechaRetorno = null;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Arma getArma() {
        return arma;
    }

    public Agente getAgente() {
        return agente;
    }

    public int getMunicionEnviada() {
        return municionEnviada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public String getFechaRetorno() {
        return fechaRetorno;
    }

    public String getDocSustento() {
        return docSustento;
    }

    public String getPuesto() {
        return puesto;
    }

    public boolean isActiva() {
        return activa;
    }

    public String getObservaciones() {
        return observaciones;
    }

    // Registrar devolución
    public void registrarDevolucion(String fechaRetorno, String obs) {
        this.fechaRetorno = fechaRetorno;
        this.observaciones = obs;
        this.activa = false;
    }
}

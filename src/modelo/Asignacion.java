package modelo;

/**
 * CLASE: Asignacion Registra la entrega de un arma a un efectivo de seguridad.
 * Equivale al "Acta de Entrega" que exige SUCAMEC.
 *
 * Contiene referencias a objetos Arma (uso de objetos dentro de objetos = POO).
 */
public class Asignacion {

    // ── ATRIBUTOS ────────────────────────────────────────────
    private int id;                // Número único de asignación
    private Arma arma;             // El arma que se entregó (objeto Arma)
    private String nombreAgente;      // Nombre del efectivo que recibe el arma
    private String dniAgente;         // DNI del efectivo
    private String licenciaSucamec;   // Licencia SUCAMEC del efectivo
    private int municionEntregada; // Cuántas balas se entregaron
    private String fechaSalida;       // Fecha y hora de salida
    private String puestoDestino;     // A qué puesto va el efectivo
    private String docSustento;       // N° de orden de servicio (obligatorio SUCAMEC)
    private boolean activa;           // true = aún no devuelta, false = ya devuelta
    private String fechaRetorno;      // Fecha de devolución (vacío si aún activa)
    private int municionDevuelta;  // Munición que regresó con el arma
    private String observaciones;     // Observaciones al devolver

    // ── CONSTRUCTOR ──────────────────────────────────────────
    public Asignacion(int id, Arma arma, String nombreAgente, String dniAgente,
            String licenciaSucamec, int municionEntregada,
            String fechaSalida, String puestoDestino, String docSustento) {
        this.id = id;
        this.arma = arma;
        this.nombreAgente = nombreAgente;
        this.dniAgente = dniAgente;
        this.licenciaSucamec = licenciaSucamec;
        this.municionEntregada = municionEntregada;
        this.fechaSalida = fechaSalida;
        this.puestoDestino = puestoDestino;
        this.docSustento = docSustento;
        this.activa = true;       // inicia como activa
        this.fechaRetorno = "";
        this.municionDevuelta = 0;
        this.observaciones = "";
    }

    // ── GETTERS ──────────────────────────────────────────────
    public int getId() {
        return id;
    }

    public Arma getArma() {
        return arma;
    }

    public String getNombreAgente() {
        return nombreAgente;
    }

    public String getDniAgente() {
        return dniAgente;
    }

    public String getLicenciaSucamec() {
        return licenciaSucamec;
    }

    public int getMunicionEntregada() {
        return municionEntregada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public String getPuestoDestino() {
        return puestoDestino;
    }

    public String getDocSustento() {
        return docSustento;
    }

    public boolean isActiva() {
        return activa;
    }

    public String getFechaRetorno() {
        return fechaRetorno;
    }

    public int getMunicionDevuelta() {
        return municionDevuelta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Registra la devolución del arma. Se llama cuando el efectivo regresa el
     * arma al almacén.
     */
    public void cerrarDevolucion(String fechaRetorno, int municionDevuelta, String obs) {
        this.fechaRetorno = fechaRetorno;
        this.municionDevuelta = municionDevuelta;
        this.observaciones = obs;
        this.activa = false; // la asignación queda cerrada
    }

    /**
     * toString: para mostrar en el JComboBox de devoluciones.
     */
    @Override
    public String toString() {
        return "ID:" + id + " | " + arma.getNumSerie()
                + " → " + nombreAgente + " | Sal: " + fechaSalida;
    }
}

package modelo;

/**
 * CLASE: Arma Representa un arma de fuego registrada en el sistema. Aplica
 * encapsulamiento (atributos privados + getters/setters).
 */
public class Arma {

    // ── ATRIBUTOS (todos privados = encapsulamiento) ──────────
    private String numSerie;          // Número de serie del arma
    private String marca;             // Marca: Glock, Beretta, etc.
    private String calibre;           // Calibre: 9mm, .38, .45
    private String tipoArma;          // PISTOLA, REVOLVER, ESCOPETA
    private String numTarjetaSucamec; // N° tarjeta SUCAMEC (obligatorio por Ley 30299)
    private String estado;            // DISPONIBLE o ASIGNADA
    private String fechaIngreso;      // Fecha en que entró al sistema

    // ── CONSTRUCTOR ──────────────────────────────────────────
    public Arma(String numSerie, String marca, String calibre,
            String tipoArma, String numTarjetaSucamec, String fechaIngreso) {
        this.numSerie = numSerie;
        this.marca = marca;
        this.calibre = calibre;
        this.tipoArma = tipoArma;
        this.numTarjetaSucamec = numTarjetaSucamec;
        this.fechaIngreso = fechaIngreso;
        this.estado = "DISPONIBLE"; // toda arma que ingresa empieza disponible
    }

    // ── GETTERS (para leer los atributos) ────────────────────
    public String getNumSerie() {
        return numSerie;
    }

    public String getMarca() {
        return marca;
    }

    public String getCalibre() {
        return calibre;
    }

    public String getTipoArma() {
        return tipoArma;
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

    // ── SETTER (solo estado puede cambiar) ───────────────────
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Método de utilidad: indica si el arma puede ser asignada. SUCAMEC exige
     * que el arma esté DISPONIBLE antes de asignarla.
     */
    public boolean estaDisponible() {
        return "DISPONIBLE".equals(this.estado);
    }

    /**
     * toString: permite mostrar el arma en un JComboBox o en texto.
     */
    @Override
    public String toString() {
        return "[" + numSerie + "] " + marca + " | Cal." + calibre
                + " | " + tipoArma + " | " + estado;
    }
}

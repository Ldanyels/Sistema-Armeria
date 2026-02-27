package modelo;

/**
 * CLASE: Municion Representa el stock de munición por calibre y tipo. SUCAMEC
 * controla la cantidad y tipo de munición (Ley 30299).
 */
public class Municion {

    // ── ATRIBUTOS ────────────────────────────────────────────
    private String tipo;         // PLOMO, GOMA, FOGUEO
    private String calibre;      // 9mm, .38, .45
    private int cantidad;     // Stock actual en unidades (rds)
    private String lote;         // Número de lote del proveedor

    // ── CONSTRUCTOR ──────────────────────────────────────────
    public Municion(String tipo, String calibre, int cantidad, String lote) {
        this.tipo = tipo;
        this.calibre = calibre;
        this.cantidad = cantidad;
        this.lote = lote;
    }

    // ── GETTERS ──────────────────────────────────────────────
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

    // ── MÉTODOS DE NEGOCIO ────────────────────────────────────
    /**
     * Agrega unidades al stock (cuando llega un nuevo lote). Usa un acumulador:
     * cantidad = cantidad + extra
     */
    public void agregarStock(int extra) {
        this.cantidad = this.cantidad + extra;
    }

    /**
     * Retira unidades del stock (cuando se asigna un arma con munición).
     * Primero verifica que haya suficiente stock (condicional). Retorna true si
     * se pudo retirar, false si no hay suficiente.
     */
    public boolean retirarStock(int pedido) {
        if (pedido > this.cantidad) {
            return false; // no hay suficiente stock
        }
        this.cantidad = this.cantidad - pedido;
        return true;
    }

    /**
     * toString: para mostrar en JComboBox y tablas.
     */
    @Override
    public String toString() {
        return calibre + " " + tipo + " | Stock: " + cantidad + " rds | Lote: " + lote;
    }
}

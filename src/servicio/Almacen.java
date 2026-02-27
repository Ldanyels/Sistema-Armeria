package servicio;

import modelo.Arma;
import modelo.Municion;
import modelo.Asignacion;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * CLASE: Almacen
 *
 * Es el "cerebro" del sistema. Aquí se almacenan todos los datos usando
 * ARREGLOS (no ArrayList), y aquí están todas las operaciones del sistema:
 *
 * - Registrar un arma - Registrar munición - Asignar un arma a un efectivo
 * (salida) - Registrar la devolución de un arma - Consultas de inventario
 *
 * ESTRUCTURAS USADAS (requisitos del curso): Arreglos 1D (vectores) : armas[],
 * municiones[], asignaciones[] Arreglo 2D (matriz) : resumen[][] — cruza
 * asignación con munición Bucles for : búsqueda y recorrido de arreglos
 * Condicionales if/else : validaciones antes de cada operación Acumuladores :
 * contadores de armas, municiones, asignaciones
 */
public class Almacen {

    // ── ARREGLOS 1D (vectores) ────────────────────────────────
    private Arma[] armas;        // Todas las armas registradas
    private Municion[] municiones;   // Stock de munición
    private Asignacion[] asignaciones; // Historial de asignaciones

    // ── CONTADORES (acumuladores) ─────────────────────────────
    private int totalArmas = 0;
    private int totalMuniciones = 0;
    private int totalAsignaciones = 0;

    // ── ARREGLO 2D (matriz) ───────────────────────────────────
    // resumen[i][0] = ID de asignación activa
    // resumen[i][1] = munición entregada en esa asignación
    // Permite ver rápido qué asignaciones tienen munición pendiente
    private int[][] resumen;

    // Capacidad máxima del sistema
    private static final int MAX = 50;

    // ── CONSTRUCTOR ──────────────────────────────────────────
    public Almacen() {
        armas = new Arma[MAX];
        municiones = new Municion[MAX];
        asignaciones = new Asignacion[MAX * 5];
        resumen = new int[MAX * 5][2]; // ARREGLO 2D
    }

    // ════════════════════════════════════════════════════════
    //  MÓDULO 1 — REGISTRO DE ARMAS
    // ════════════════════════════════════════════════════════
    /**
     * Registra un arma nueva en el inventario.
     *
     * Validaciones SUCAMEC (Ley 30299): 1. El número de serie no puede
     * repetirse. 2. El número de tarjeta SUCAMEC no puede repetirse. 3. No se
     * puede exceder la capacidad del arreglo.
     *
     * @return "OK" si se registró, o un mensaje de error.
     */
    public String registrarArma(Arma arma) {

        // Validación 1: arreglo lleno
        if (totalArmas >= MAX) {
            return "ERROR: El inventario está lleno (máximo " + MAX + " armas).";
        }

        // Validación 2: número de serie duplicado (BUCLE + CONDICIONAL)
        for (int i = 0; i < totalArmas; i++) {
            if (armas[i].getNumSerie().equalsIgnoreCase(arma.getNumSerie())) {
                return "ERROR: Ya existe un arma con número de serie: " + arma.getNumSerie();
            }
        }

        // Validación 3: tarjeta SUCAMEC duplicada (BUCLE + CONDICIONAL)
        for (int i = 0; i < totalArmas; i++) {
            if (armas[i].getNumTarjetaSucamec().equalsIgnoreCase(arma.getNumTarjetaSucamec())) {
                return "ERROR: Ya existe un arma con tarjeta SUCAMEC: " + arma.getNumTarjetaSucamec();
            }
        }

        // Guardar en el arreglo y aumentar el contador
        armas[totalArmas] = arma;
        totalArmas++;       // ACUMULADOR

        return "OK";
    }

    /**
     * Retorna todas las armas del inventario.
     */
    public Arma[] getArmas() {
        Arma[] resultado = new Arma[totalArmas];
        for (int i = 0; i < totalArmas; i++) {
            resultado[i] = armas[i];
        }
        return resultado;
    }

    /**
     * Retorna solo las armas con estado "DISPONIBLE". Usa BUCLE + CONDICIONAL
     * para filtrar el arreglo.
     */
    public Arma[] getArmasDisponibles() {
        Arma[] temp = new Arma[totalArmas];
        int count = 0;

        for (int i = 0; i < totalArmas; i++) {
            if (armas[i].estaDisponible()) {
                temp[count] = armas[i];
                count++;
            }
        }

        Arma[] resultado = new Arma[count];
        for (int i = 0; i < count; i++) {
            resultado[i] = temp[i];
        }
        return resultado;
    }

    // ════════════════════════════════════════════════════════
    //  MÓDULO 2 — REGISTRO DE MUNICIÓN
    // ════════════════════════════════════════════════════════
    /**
     * Ingresa munición al almacén. Si ya existe ese calibre+tipo, suma al stock
     * existente. Si es nuevo, lo agrega al arreglo.
     *
     * @return "OK" o mensaje de error.
     */
    public String registrarMunicion(Municion nueva) {

        if (nueva.getCantidad() <= 0) {
            return "ERROR: La cantidad debe ser mayor a cero.";
        }

        // Buscar si ya existe ese tipo+calibre (BUCLE)
        for (int i = 0; i < totalMuniciones; i++) {
            if (municiones[i].getCalibre().equalsIgnoreCase(nueva.getCalibre())
                    && municiones[i].getTipo().equalsIgnoreCase(nueva.getTipo())) {
                // Ya existe: solo acumula el stock
                municiones[i].agregarStock(nueva.getCantidad());
                return "OK: Stock actualizado. Nuevo total: " + municiones[i].getCantidad() + " rds";
            }
        }

        // No existe: agregar como nuevo tipo
        if (totalMuniciones >= MAX) {
            return "ERROR: Catálogo de municiones lleno.";
        }
        municiones[totalMuniciones] = nueva;
        totalMuniciones++;   // ACUMULADOR

        return "OK";
    }

    /**
     * Retorna todo el inventario de munición.
     */
    public Municion[] getMuniciones() {
        Municion[] resultado = new Municion[totalMuniciones];
        for (int i = 0; i < totalMuniciones; i++) {
            resultado[i] = municiones[i];
        }
        return resultado;
    }

    // ════════════════════════════════════════════════════════
    //  MÓDULO 3 — ASIGNACIÓN (SALIDA DE ARMA)
    // ════════════════════════════════════════════════════════
    /**
     * Asigna un arma a un efectivo de seguridad.
     *
     * Validaciones SUCAMEC: 1. El arma debe estar DISPONIBLE. 2. Debe haber
     * suficiente munición del calibre correcto. 3. El documento de sustento es
     * obligatorio. 4. El DNI y la licencia SUCAMEC son obligatorios.
     *
     * @return "OK" o mensaje de error.
     */
    public String asignarArma(Arma arma, String nombreAgente, String dniAgente,
            String licenciaSucamec, int municionPedida,
            String puestoDestino, String docSustento) {

        // Validación 1: arma disponible
        if (!arma.estaDisponible()) {
            return "ERROR: El arma " + arma.getNumSerie()
                    + " no está disponible. Estado actual: " + arma.getEstado();
        }

        // Validación 2: campos obligatorios
        if (dniAgente.isEmpty() || licenciaSucamec.isEmpty() || docSustento.isEmpty()) {
            return "ERROR: DNI, licencia SUCAMEC y documento de sustento son obligatorios.";
        }

        // Validación 3: munición suficiente (si se solicita)
        if (municionPedida > 0) {
            boolean hayMunicion = false;
            for (int i = 0; i < totalMuniciones; i++) {
                if (municiones[i].getCalibre().equalsIgnoreCase(arma.getCalibre())) {
                    if (!municiones[i].retirarStock(municionPedida)) {
                        return "ERROR: Stock insuficiente de munición " + arma.getCalibre()
                                + ". Disponible: " + municiones[i].getCantidad() + " rds";
                    }
                    hayMunicion = true;
                    break;
                }
            }
            if (!hayMunicion) {
                return "ERROR: No hay munición de calibre " + arma.getCalibre() + " registrada.";
            }
        }

        // ── Crear la asignación ───────────────────────────────
        String fechaHora = obtenerFecha();
        int nuevoId = totalAsignaciones + 1;

        Asignacion nueva = new Asignacion(
                nuevoId, arma, nombreAgente, dniAgente,
                licenciaSucamec, municionPedida,
                fechaHora, puestoDestino, docSustento
        );

        // Guardar en el arreglo
        asignaciones[totalAsignaciones] = nueva;

        // Guardar en la MATRIZ 2D: [id_asignacion, municion_entregada]
        resumen[totalAsignaciones][0] = nuevoId;
        resumen[totalAsignaciones][1] = municionPedida;

        totalAsignaciones++; // ACUMULADOR

        // Cambiar estado del arma
        arma.setEstado("ASIGNADA");

        return "OK";
    }

    // ════════════════════════════════════════════════════════
    //  MÓDULO 4 — DEVOLUCIÓN DE ARMA
    // ════════════════════════════════════════════════════════
    /**
     * Registra la devolución de un arma.
     *
     * Validaciones SUCAMEC: 1. La asignación debe existir y estar activa. 2. La
     * munición devuelta no puede ser mayor a la entregada. 3. La munición
     * sobrante regresa al stock.
     *
     * @return "OK" o mensaje de error.
     */
    public String registrarDevolucion(int idAsignacion, int municionDevuelta,
            String observaciones) {

        // Buscar la asignación activa (BUCLE + CONDICIONAL)
        for (int i = 0; i < totalAsignaciones; i++) {
            Asignacion asig = asignaciones[i];

            if (asig.getId() == idAsignacion && asig.isActiva()) {

                // Validación: munición devuelta no puede superar la entregada
                if (municionDevuelta > asig.getMunicionEntregada()) {
                    return "ERROR: La munición a devolver (" + municionDevuelta
                            + ") supera la entregada (" + asig.getMunicionEntregada() + ").";
                }

                // Cerrar la asignación
                asig.cerrarDevolucion(obtenerFecha(), municionDevuelta, observaciones);

                // El arma vuelve a DISPONIBLE
                asig.getArma().setEstado("DISPONIBLE");

                // Reintegrar munición sobrante al stock
                if (municionDevuelta > 0) {
                    String calibre = asig.getArma().getCalibre();
                    boolean encontrado = false;

                    for (int j = 0; j < totalMuniciones; j++) {
                        if (municiones[j].getCalibre().equalsIgnoreCase(calibre)) {
                            municiones[j].agregarStock(municionDevuelta);
                            encontrado = true;
                            break;
                        }
                    }

                    // Si no existe ese calibre (caso raro), crea uno nuevo
                    if (!encontrado && totalMuniciones < MAX) {
                        municiones[totalMuniciones] = new Municion(
                                "PLOMO", calibre, municionDevuelta, "DEV-" + idAsignacion);
                        totalMuniciones++;
                    }
                }

                // Limpiar la fila en la MATRIZ 2D
                resumen[i][0] = 0;
                resumen[i][1] = 0;

                return "OK";
            }
        }

        return "ERROR: No se encontró una asignación activa con ID: " + idAsignacion;
    }

    // ════════════════════════════════════════════════════════
    //  CONSULTAS
    // ════════════════════════════════════════════════════════
    /**
     * Retorna solo las asignaciones que aún están activas.
     */
    public Asignacion[] getAsignacionesActivas() {
        Asignacion[] temp = new Asignacion[totalAsignaciones];
        int count = 0;

        for (int i = 0; i < totalAsignaciones; i++) {
            if (asignaciones[i].isActiva()) {
                temp[count] = asignaciones[i];
                count++;
            }
        }

        Asignacion[] resultado = new Asignacion[count];
        for (int i = 0; i < count; i++) {
            resultado[i] = temp[i];
        }
        return resultado;
    }

    /**
     * Retorna el historial completo de asignaciones (activas y cerradas).
     */
    public Asignacion[] getTodasAsignaciones() {
        Asignacion[] resultado = new Asignacion[totalAsignaciones];
        for (int i = 0; i < totalAsignaciones; i++) {
            resultado[i] = asignaciones[i];
        }
        return resultado;
    }

    /**
     * Retorna la matriz 2D de resumen.
     */
    public int[][] getResumen() {
        return resumen;
    }

    /**
     * Cuenta cuántas armas hay por estado. Retorna arreglo: [disponibles,
     * asignadas]
     */
    public int[] contarArmasPorEstado() {
        int[] conteo = new int[2]; // [0]=DISPONIBLE, [1]=ASIGNADA
        for (int i = 0; i < totalArmas; i++) {
            if ("DISPONIBLE".equals(armas[i].getEstado())) {
                conteo[0]++;
            } else {
                conteo[1]++;
            }
        }
        return conteo;
    }

    // ── MÉTODO PRIVADO DE APOYO ───────────────────────────────
    private String obtenerFecha() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import modelo.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InventarioService {

    private Arma[] armas;
    private Municion[] municiones;
    private Agente[] agentes;
    private Movimiento[] movimientos;
    private Asignacion[] asignaciones;

    // contadores de elementos reales en cada arreglo
    private int totalArmas, totalMuniciones, totalAgentes;
    private int totalMovimientos, totalAsignaciones;
    private int contadorMovimientos = 1;

    // ── MATRIZ (arreglo 2D) ─────────────────────────────────
    // resumen[i][0] = id asignacion activa del agente i
    // resumen[i][1] = cantidad municion entregada
    private int[][] resumenAsignaciones;

    private static final int MAX = 100;

    // Constructor: inicializa todos los arreglos
    public InventarioService() {
        armas = new Arma[MAX];
        municiones = new Municion[MAX];
        agentes = new Agente[MAX];
        movimientos = new Movimiento[MAX * 5];
        asignaciones = new Asignacion[MAX * 10];
        resumenAsignaciones = new int[MAX][2]; // MATRIZ
    }

    // ── ARMAS ───────────────────────────────────────────────
    public boolean registrarArma(Arma arma) {
        // Condicional: verificar duplicado por número de serie
        for (int i = 0; i < totalArmas; i++) {
            if (armas[i].getNumSerie().equals(arma.getNumSerie())) {
                return false; // ya existe
            }
        }
        armas[totalArmas++] = arma;
        registrarMovimiento(Movimiento.INGRESO_ARMA,
                "Ingreso de arma: " + arma.getNumSerie()
                + " | " + arma.getMarca() + " " + arma.getModelo(),
                "REGISTRO_INICIAL");
        return true;
    }

    public Arma[] getArmas() {
        Arma[] resultado = new Arma[totalArmas];
        for (int i = 0; i < totalArmas; i++) {
            resultado[i] = armas[i];
        }
        return resultado;
    }

    public Arma[] getArmasDisponibles() {
        // Bucle + condicional para filtrar armas disponibles
        Arma[] temp = new Arma[totalArmas];
        int count = 0;
        for (int i = 0; i < totalArmas; i++) {
            if ("DISPONIBLE".equals(armas[i].getEstado())) {
                temp[count++] = armas[i];
            }
        }
        Arma[] resultado = new Arma[count];
        for (int i = 0; i < count; i++) {
            resultado[i] = temp[i];
        }
        return resultado;
    }

    // ── MUNICIÓN ────────────────────────────────────────────
    public void ingresarMunicion(Municion nuevaMun, String docSustento) {
        // Buscar si ya existe ese calibre+tipo para sumar stock
        for (int i = 0; i < totalMuniciones; i++) {
            if (municiones[i].getCalibre().equals(nuevaMun.getCalibre())
                    && municiones[i].getTipo().equals(nuevaMun.getTipo())) {
                municiones[i].agregarStock(nuevaMun.getCantidad());
                registrarMovimiento(Movimiento.INGRESO_MUNICION,
                        "Ingreso " + nuevaMun.getCantidad() + " rds "
                        + nuevaMun.getCalibre() + " " + nuevaMun.getTipo(),
                        docSustento);
                return;
            }
        }
        // Si no existe, agregar nuevo tipo
        municiones[totalMuniciones++] = nuevaMun;
        registrarMovimiento(Movimiento.INGRESO_MUNICION,
                "Nuevo tipo munición: " + nuevaMun.getCalibre()
                + " " + nuevaMun.getTipo() + " — " + nuevaMun.getCantidad() + " rds",
                docSustento);
    }

    public Municion[] getMuniciones() {
        Municion[] r = new Municion[totalMuniciones];
        for (int i = 0; i < totalMuniciones; i++) {
            r[i] = municiones[i];
        }
        return r;
    }

    // ── ASIGNACIÓN (SALIDA) ─────────────────────────────────
    public String asignarArma(Arma arma, Agente agente,
            int municion, String calibre,
            String docSustento, String puesto) {

        // Validación 1: arma disponible
        if (!"DISPONIBLE".equals(arma.getEstado())) {
            return "ERROR: El arma no está disponible (estado: " + arma.getEstado() + ")";
        }

        // Validación 2: agente activo
        if (!agente.isActivo()) {
            return "ERROR: El agente no está activo en el sistema";
        }

        // Validación 3: stock de munición suficiente
        boolean munRetirada = false;
        for (int i = 0; i < totalMuniciones; i++) {
            if (municiones[i].getCalibre().equals(calibre)) {
                munRetirada = municiones[i].retirarStock(municion);
                break;
            }
        }
        if (!munRetirada && municion > 0) {
            return "ERROR: Stock insuficiente de munición " + calibre;
        }

        // Ejecutar asignación
        String fecha = fechaActual();
        Asignacion asig = new Asignacion(
                totalAsignaciones + 1, arma, agente,
                municion, fecha, docSustento, puesto);
        asignaciones[totalAsignaciones] = asig;

        // Actualizar matriz de resumen
        resumenAsignaciones[totalAsignaciones][0] = asig.getId();
        resumenAsignaciones[totalAsignaciones][1] = municion;
        totalAsignaciones++;

        arma.setEstado("ASIGNADA");
        registrarMovimiento(Movimiento.SALIDA_ASIGNACION,
                "Arma " + arma.getNumSerie() + " → Agente "
                + agente.getNombreCompleto() + " | Mun: " + municion + " rds | Puesto: " + puesto,
                docSustento);
        return "OK";
    }

    // ── DEVOLUCIÓN (RETORNO) ────────────────────────────────
    public String registrarDevolucion(int idAsignacion,
            int municionRestante,
            String observaciones) {
        // Buscar la asignación activa con ese ID
        for (int i = 0; i < totalAsignaciones; i++) {
            if (asignaciones[i].getId() == idAsignacion && asignaciones[i].isActiva()) {
                Asignacion asig = asignaciones[i];
                asig.registrarDevolucion(fechaActual(), observaciones);
                asig.getArma().setEstado("DISPONIBLE");

                // Reincorporar munición sobrante al stock
                if (municionRestante > 0) {
                    String calibre = asig.getArma().getCalibre();
                    for (int j = 0; j < totalMuniciones; j++) {
                        if (municiones[j].getCalibre().equals(calibre)) {
                            municiones[j].agregarStock(municionRestante);
                            break;
                        }
                    }
                }
                registrarMovimiento(Movimiento.DEVOLUCION,
                        "Devolución arma " + asig.getArma().getNumSerie()
                        + " | Mun. sobrante: " + municionRestante + " | Obs: " + observaciones,
                        "DEV-" + idAsignacion);
                return "OK";
            }
        }
        return "ERROR: Asignación activa no encontrada";
    }

    // ── CONSULTAS ───────────────────────────────────────────
    public Asignacion[] getAsignacionesActivas() {
        Asignacion[] temp = new Asignacion[totalAsignaciones];
        int count = 0;
        for (int i = 0; i < totalAsignaciones; i++) {
            if (asignaciones[i].isActiva()) {
                temp[count++] = asignaciones[i];
            }
        }
        Asignacion[] r = new Asignacion[count];
        for (int i = 0; i < count; i++) {
            r[i] = temp[i];
        }
        return r;
    }

    public Movimiento[] getMovimientos() {
        Movimiento[] r = new Movimiento[totalMovimientos];
        for (int i = 0; i < totalMovimientos; i++) {
            r[i] = movimientos[i];
        }
        return r;
    }

    public int[][] getResumenAsignaciones() {
        return resumenAsignaciones;
    }

    // Agentes y registro
    public void registrarAgente(Agente a) {
        agentes[totalAgentes++] = a;
    }

    public Agente[] getAgentes() {
        Agente[] r = new Agente[totalAgentes];
        for (int i = 0; i < totalAgentes; i++) {
            r[i] = agentes[i];
        }
        return r;
    }

    // ── INTERNO ─────────────────────────────────────────────
    private void registrarMovimiento(String tipo, String desc, String doc) {
        movimientos[totalMovimientos++] = new Movimiento(
                contadorMovimientos++, tipo, desc, doc, fechaActual(), "SISTEMA");
    }

    private String fechaActual() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

}

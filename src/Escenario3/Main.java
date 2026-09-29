package Escenario3;

import static Escenario3.Escenario3.*;

public class Main {
public static void main(String[] args) {

    registrar(new SolicitudViaje("V1", "Ana", "Centro", "Aeropuerto"));
    registrar(new SolicitudViaje("V2", "Luis", "Norte", "Terminal"));
    registrar(new SolicitudViaje("V3", "Sara", "Sur", "Universidad"));
    registrar(new SolicitudViaje("V4", "Pedro", "Centro", "Estadio"));
    registrar(new SolicitudViaje("V2", "Luis", "Norte", "Terminal")); // repetida

    System.out.println("\n---- PENDIENTES ----");
    mostrarPendientes();

    System.out.println("\n---- CANCELAR ----");
    cancelar("V3");
    cancelar("V9");

    System.out.println("\n---- ATENDER ----");
    atender();

    System.out.println("\n---- PENDIENTES DESPUES ----");
    mostrarPendientes();

    // ---------------- FASE 4: MEDICION ----------------
    System.out.println("\n---- MEDICION ----");
    int[] tamanos = {100, 1000, 10000, 100000};

    for (int i = 0; i < tamanos.length; i++) {
        int n = tamanos[i];

        pendientes.clear();
        solicitudes.clear();

        Runtime rt = Runtime.getRuntime();
        rt.gc();
        long memoriaAntes = rt.totalMemory() - rt.freeMemory();

        // registrar
        long inicio = System.nanoTime();
        for (int j = 0; j < n; j++) {
            String id = "V" + j;
            if (!pendientes.contains(id)) {
                pendientes.add(id);
                solicitudes.put(id, new SolicitudViaje(id, "Cliente" + j, "A", "B"));
            }
        }
        long fin = System.nanoTime();
        double tRegistrar = (fin - inicio) / 1000000.0;

        rt.gc();
        long memoriaDespues = rt.totalMemory() - rt.freeMemory();
        long memoriaKB = (memoriaDespues - memoriaAntes) / 1024;

        // cancelar 1 de cada 10
        inicio = System.nanoTime();
        for (int j = 0; j < n; j = j + 10) {
            String id = "V" + j;
            if (pendientes.contains(id)) {
                pendientes.remove(id);
                solicitudes.remove(id);
            }
        }
        fin = System.nanoTime();
        double tCancelar = (fin - inicio) / 1000000.0;

        // mostrar (solo recorrer, sin imprimir)
        inicio = System.nanoTime();
        int contador = 0;
        for (String id : pendientes) {
            contador++;
        }
        fin = System.nanoTime();
        double tMostrar = (fin - inicio) / 1000000.0;

        // atender todas en orden
        inicio = System.nanoTime();
        while (!pendientes.isEmpty()) {
            String primero = "";
            for (String id : pendientes) {
                primero = id;
                break;
            }
            pendientes.remove(primero);
            solicitudes.remove(primero);
        }
        fin = System.nanoTime();
        double tAtender = (fin - inicio) / 1000000.0;

        System.out.println("n = " + n);
        System.out.println("   Registrar: " + tRegistrar + " ms");
        System.out.println("   Cancelar:  " + tCancelar + " ms");
        System.out.println("   Mostrar:   " + tMostrar + " ms");
        System.out.println("   Atender:   " + tAtender + " ms");
        System.out.println("   Memoria:   " + memoriaKB + " KB");
    }
}
}

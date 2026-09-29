package Escenario3;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class Escenario3 {

    // guarda los id en orden de llegada, el primero es el mas antiguo
    static LinkedHashSet<String> pendientes = new LinkedHashSet<>();
    // guarda la solicitud completa usando el id como clave
    static HashMap<String, SolicitudViaje> solicitudes = new HashMap<>();

    public static void registrar(SolicitudViaje s) {
        if (pendientes.contains(s.id)) {
            System.out.println("La solicitud " + s.id + " ya existe");
        } else {
            pendientes.add(s.id);
            solicitudes.put(s.id, s);
        }
    }

    public static void atender() {
        if (pendientes.isEmpty()) {
            System.out.println("No hay solicitudes");
            return;
        }
        // el primer elemento del for es el mas antiguo
        String primero = "";
        for (String id : pendientes) {
            primero = id;
            break;
        }
        System.out.print("Atendiendo: ");
        solicitudes.get(primero).mostrar();
        pendientes.remove(primero);
        solicitudes.remove(primero);
    }

    public static void cancelar(String id) {
        if (pendientes.contains(id)) {
            pendientes.remove(id);
            solicitudes.remove(id);
            System.out.println("Solicitud " + id + " cancelada");
        } else {
            System.out.println("La solicitud " + id + " no existe");
        }
    }

    public static void mostrarPendientes() {
        for (String id : pendientes) {
            solicitudes.get(id).mostrar();
        }
    }
}
package Escenario1;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;

public class Escenario1 {

    // guarda los documentos en el orden en que llegan y no deja repetidos revisando
    // todo
    static LinkedHashSet<String> ordenLlegada = new LinkedHashSet<>();// revisando
    // guarda el paciente usando el documento como clave para buscar rapido
    static HashMap<String, Paciente> pacientes = new HashMap<>();
    // cola de prioridad para atender primero a los graves
    static PriorityQueue<Paciente> triage = new PriorityQueue<>(new ComparadorGravedad());

    public static void registrar(Paciente p) {
        if (ordenLlegada.contains(p.documento)) {
            System.out.println("El paciente " + p.documento + " ya esta registrado");
            System.out.println("Funciona");
        } else {
            ordenLlegada.add(p.documento);
            pacientes.put(p.documento, p);
            triage.add(p);
        }
    }

    public static void buscar(String documento) {
        Paciente p = pacientes.get(documento);
        if (p == null) {
            System.out.println("No se encontro el paciente " + documento);
        } else {
            System.out.print("Encontrado: ");
            p.mostrar();
        }
    }

    public static void mostrarEnOrden() {
        for (String doc : ordenLlegada) {
            pacientes.get(doc).mostrar();
        }
    }

    public static void atenderGraves() {
        while (!triage.isEmpty()) {
            Paciente p = triage.poll();
            System.out.print("Atendiendo: ");
            p.mostrar();
        }
    }
}

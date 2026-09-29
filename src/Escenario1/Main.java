package Escenario1;

public class Main {

public static void main(String[] args) {

    System.out.println("---- REGISTRO DE PACIENTES ----");
    Escenario1.registrar(new Paciente("1001", "Ana", 3));
    Escenario1.registrar(new Paciente("1002", "Luis", 1));
    Escenario1.registrar(new Paciente("1003", "Sara", 5));
    Escenario1.registrar(new Paciente("1004", "Pedro", 2));
    Escenario1.registrar(new Paciente("1001", "Ana", 3)); // repetido

    Escenario1.mostrarEnOrden();

    System.out.println("\n---- BUSQUEDA ---");
    Escenario1.buscar("1003");
    Escenario1.buscar("5555");

    System.out.println("\n---- TRIAGE ----");
    Escenario1.atenderGraves();

    // ---------------- FASE 4: MEDICION ----------------
    System.out.println("\n---- MEDICION ----");
    int[] tamanos = { 100, 1000, 10000, 100000 };

    for (int i = 0; i < tamanos.length; i++) {
        int n = tamanos[i];

        // se limpian las estructuras para cada prueba
        Escenario1.ordenLlegada.clear();
        Escenario1.pacientes.clear();
        Escenario1.triage.clear();

        Runtime rt = Runtime.getRuntime();
        rt.gc();
        long memoriaAntes = rt.totalMemory() - rt.freeMemory();

        // tiempo de registrar
        long inicio = System.nanoTime();
        for (int j = 0; j < n; j++) {
            String doc = "" + j;
            if (!Escenario1.ordenLlegada.contains(doc)) {
                Escenario1.ordenLlegada.add(doc);
                Paciente p = new Paciente(doc, "Escenario1.Paciente" + j, (j % 5) + 1);
                Escenario1.pacientes.put(doc, p);
                Escenario1.triage.add(p);
            }
        }
        long fin = System.nanoTime();
        double tiempoRegistrar = (fin - inicio) / 1000000.0;

        // tiempo de buscar todos
        inicio = System.nanoTime();
        for (int j = 0; j < n; j++) {
            Paciente p = Escenario1.pacientes.get("" + j);
        }
        fin = System.nanoTime();
        double tiempoBuscar = (fin - inicio) / 1000000.0;

        rt.gc();
        long memoriaDespues = rt.totalMemory() - rt.freeMemory();
        long memoriaKB = (memoriaDespues - memoriaAntes) / 1024;

        System.out.println("n = " + n);
        System.out.println("   Registrar: " + tiempoRegistrar + " ms");
        System.out.println("   Buscar:    " + tiempoBuscar + " ms");
        System.out.println("   Memoria:   " + memoriaKB + " KB");
        System.out.println("   Pacientes guardados: " + Escenario1.pacientes.size());
    }
}}
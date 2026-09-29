public class Main {
public static void main(String[] args) {


    insertar(new Producto("A1", "Audifonos", 120000));
    insertar(new Producto("A2", "Cargador", 35000));
    insertar(new Producto("A3", "Cable USB", 15000));
    insertar(new Producto("A4", "Funda", 35000));
    insertar(new Producto("A2", "Cargador", 35000)); // repetido

    System.out.println("\n---- BUSCAR ----");
    buscar("A2");
    buscar("Z9");

    System.out.println("\n---- ORDENADOS POR PRECIO ----");
    mostrarPorPrecio();

    // ---------------- FASE 4: MEDICION ----------------
    System.out.println("\n---- MEDICION ----");
    int[] tamanos = {100, 1000, 10000, 100000};

    for (int i = 0; i < tamanos.length; i++) {
        int n = tamanos[i];

        catalogo.clear();
        ordenPrecio.clear();

        Runtime rt = Runtime.getRuntime();
        rt.gc();
        long memoriaAntes = rt.totalMemory() - rt.freeMemory();

        // insertar
        long inicio = System.nanoTime();
        for (int j = 0; j < n; j++) {
            double precio = (j * 53) % 80000;
            Producto p = new Producto("C" + j, "Producto" + j, precio);
            catalogo.put(p.codigo, p);
            ordenPrecio.add(p);
        }
        long fin = System.nanoTime();
        double tInsertar = (fin - inicio) / 1000000.0;

        // buscar
        inicio = System.nanoTime();
        for (int j = 0; j < n; j++) {
            Producto p = catalogo.get("C" + j);
        }
        fin = System.nanoTime();
        double tBuscar = (fin - inicio) / 1000000.0;

        // recorrer ordenados
        inicio = System.nanoTime();
        int contador = 0;
        for (Producto p : ordenPrecio) {
            contador++;
        }
        fin = System.nanoTime();
        double tMostrar = (fin - inicio) / 1000000.0;

        rt.gc();
        long memoriaDespues = rt.totalMemory() - rt.freeMemory();
        long memoriaKB = (memoriaDespues - memoriaAntes) / 1024;

        System.out.println("n = " + n);
        System.out.println("   Insertar:           " + tInsertar + " ms");
        System.out.println("   Buscar:             " + tBuscar + " ms");
        System.out.println("   Mostrar ordenados:  " + tMostrar + " ms");
        System.out.println("   Memoria:            " + memoriaKB + " KB");
        System.out.println("   Productos en el TreeSet: " + ordenPrecio.size());
    }
}
}

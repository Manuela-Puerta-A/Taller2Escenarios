package Escenario2;

import static Escenario2.Escenario2.*;

public class Main {
    public static void main(String[] args) {

        agregar(new Producto("P1", "Mouse", 45000, "Tecnologia", 10));
        agregar(new Producto("P2", "Camiseta", 30000, "Ropa", 5));
        agregar(new Producto("P3", "Teclado", 45000, "Tecnologia", 8));
        agregar(new Producto("P4", "Jean", 90000, "Ropa", 3));

        System.out.println("---- BUSCAR ----");
        buscar("P3");

        System.out.println("\n---- ORDENADOS POR PRECIO ----");
        mostrarPorPrecio();

        System.out.println("\n---- LISTA (el ultimo agregado de primero) ----");
        mostrarLista();

        System.out.println("\n---- FILTRAR ROPA ----");
        filtrar("Ropa");

        // ---------------- FASE 4: MEDICION ----------------
        System.out.println("\n---- MEDICION ----");
        int[] tamanos = { 100, 1000, 10000, 100000 };
        String[] cats = { "Tecnologia", "Ropa", "Hogar", "Deportes" };

        for (int i = 0; i < tamanos.length; i++) {
            int n = tamanos[i];

            productos.clear();
            ordenPrecio.clear();
            lista.clear();
            categorias.clear();

            Runtime rt = Runtime.getRuntime();
            rt.gc();
            long memoriaAntes = rt.totalMemory() - rt.freeMemory();

            // agregar
            long inicio = System.nanoTime();
            for (int j = 0; j < n; j++) {
                double precio = (j * 37) % 50000; // se repiten precios
                String categoria = cats[j % 4];
                Producto p = new Producto("C" + j, "Producto" + j, precio, categoria, 1);

                productos.put(p.codigo, p);
                ordenPrecio.add(p);
                lista.addFirst(p);
                if (!categorias.containsKey(categoria)) {
                    categorias.put(categoria, new ArrayList<>());
                }
                categorias.get(categoria).add(p);
            }
            long fin = System.nanoTime();
            double tAgregar = (fin - inicio) / 1000000.0;

            // buscar
            inicio = System.nanoTime();
            for (int j = 0; j < n; j++) {
                Producto p = productos.get("C" + j);
            }
            fin = System.nanoTime();
            double tBuscar = (fin - inicio) / 1000000.0;

            // recorrer ordenados por precio
            inicio = System.nanoTime();
            int contador = 0;
            for (Producto p : ordenPrecio) {
                contador++;
            }
            fin = System.nanoTime();
            double tOrdenar = (fin - inicio) / 1000000.0;

            // filtrar una categoria
            inicio = System.nanoTime();
            ArrayList<Producto> ropa = categorias.get("Ropa");
            fin = System.nanoTime();
            double tFiltrar = (fin - inicio) / 1000000.0;

            rt.gc();
            long memoriaDespues = rt.totalMemory() - rt.freeMemory();
            long memoriaKB = (memoriaDespues - memoriaAntes) / 1024;

            System.out.println("n = " + n);
            System.out.println("   Agregar:            " + tAgregar + " ms");
            System.out.println("   Buscar:             " + tBuscar + " ms");
            System.out.println("   Recorrer ordenados: " + tOrdenar + " ms");
            System.out.println("   Filtrar categoria:  " + tFiltrar + " ms");
            System.out.println("   Memoria:            " + memoriaKB + " KB");
            System.out.println("   Productos en el TreeSet: " + ordenPrecio.size());
        }
    }
}
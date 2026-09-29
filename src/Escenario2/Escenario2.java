package Escenario2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

public class Escenario2 {

    static HashMap<String, Producto> productos = new HashMap<>();
    static TreeSet<Producto> ordenPrecio = new TreeSet<>(new ComparadorPrecio());
    static LinkedList<Producto> lista = new LinkedList<>();
    static HashMap<String, ArrayList<Producto>> categorias = new HashMap<>();

    public static void agregar(Producto p) {
        if (productos.containsKey(p.codigo)) {
            System.out.println("El producto " + p.codigo + " ya existe");
            return;
        }
        productos.put(p.codigo, p);
        ordenPrecio.add(p);
        lista.addFirst(p); // se agrega al inicio

        if (!categorias.containsKey(p.categoria)) {
            categorias.put(p.categoria, new ArrayList<>());
        }
        categorias.get(p.categoria).add(p);
    }

    public static void buscar(String codigo) {
        Producto p = productos.get(codigo);
        if (p == null) {
            System.out.println("No existe el producto " + codigo);
        } else {
            System.out.print("Encontrado: ");
            p.mostrar();
        }
    }

    public static void mostrarPorPrecio() {
        for (Producto p : ordenPrecio) {
            p.mostrar();
        }
    }

    public static void mostrarLista() {
        for (Producto p : lista) {
            p.mostrar();
        }
    }

    public static void filtrar(String categoria) {
        if (!categorias.containsKey(categoria)) {
            System.out.println("No hay productos en " + categoria);
            return;
        }
        for (Producto p : categorias.get(categoria)) {
            p.mostrar();
        }
    }
}
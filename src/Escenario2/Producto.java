package Escenario2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

// Clase producto
public class Producto {
    String codigo;
    String nombre;
    double precio;
    String categoria;
    int cantidad;

    public Producto(String codigo, String nombre, double precio, String categoria, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    public void mostrar() {
        System.out.println(codigo + " - " + nombre + " - $" + precio + " - " + categoria);
    }
}

// Comparador para ordenar por precio
// si el precio es igual se compara el codigo, porque si no el TreeSet
// piensa que son el mismo producto y no lo guarda
class ComparadorPrecio implements Comparator<Producto> {
    public int compare(Producto a, Producto b) {
        if (a.precio < b.precio) {
            return -1;
        } else if (a.precio > b.precio) {
            return 1;
        } else {
            return a.codigo.compareTo(b.codigo);
        }
    }
}

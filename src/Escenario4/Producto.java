import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

// Clase producto del catalogo

public class Producto {
    String codigo;
    String nombre;
    double precio;

    public Producto(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public void mostrar() {
        System.out.println(codigo + " - " + nombre + " - $" + precio);
    }
}

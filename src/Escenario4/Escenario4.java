import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class Escenario4 {

    static HashMap<String, Producto> catalogo = new HashMap<>();
    static TreeSet<Producto> ordenPrecio = new TreeSet<>(new ComparadorPrecio());

    public static void insertar(Producto p) {
        if (catalogo.containsKey(p.codigo)) {
            System.out.println("El producto " + p.codigo + " ya existe");
        } else {
            catalogo.put(p.codigo, p);
            ordenPrecio.add(p);
        }
    }

    public static void buscar(String codigo) {
        Producto p = catalogo.get(codigo);
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
}
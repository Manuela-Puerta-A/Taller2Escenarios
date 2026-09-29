package Escenario3;

// Clase solicitud de viaje
public class SolicitudViaje {
    String id;
    String cliente;
    String origen;
    String destino;
    long hora;

    public SolicitudViaje(String id, String cliente, String origen, String destino) {
        this.id = id;
        this.cliente = cliente;
        this.origen = origen;
        this.destino = destino;
        this.hora = System.currentTimeMillis();
    }

    public void mostrar() {
        System.out.println(id + " - " + cliente + " - " + origen + " a " + destino);
    }
}

package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;
import ar.edu.unju.escmi.tp6.dominio.Prestamo;

public class CollectionPrestamo {
    public static ArrayList<Prestamo> prestamos = new ArrayList<>();

    public static void agregar(Prestamo p) {
        prestamos.add(p);
    }

    public static void listar() {
        for (Prestamo p : prestamos) {
            p.mostrarDatos();
        }
    }
}

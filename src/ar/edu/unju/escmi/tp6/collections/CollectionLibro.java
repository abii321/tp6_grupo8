package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;
import ar.edu.unju.escmi.tp6.dominio.Libro;

public class CollectionLibro {
    public static ArrayList<Libro> libros = new ArrayList<>();

    public static void agregar(Libro libro) {
        libros.add(libro);
    }

    public static Libro buscarPorISBN(String isbn) {
        for (Libro l : libros) {
            if (l.getIsbn().equalsIgnoreCase(isbn)) return l;
        }
        return null;
    }

    public static void listar() {
        for (Libro l : libros) {
            l.mostrarDatos();
        }
    }
}

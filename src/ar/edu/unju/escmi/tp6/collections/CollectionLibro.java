package ar.edu.unju.escmi.tp6.collections;

import java.util.HashMap;
import java.util.Map;

import ar.edu.unju.escmi.tp6.dominio.Libro;

public class CollectionLibro {
    public static Map<Integer, Libro> libros = new HashMap<>();
    private static int ultimoId = 0;

    public static int generarId() {
        ultimoId++;
        return ultimoId;
    }

    public static void agregar(Libro libro) {
        libros.put(libro.getId(), libro);
    }

    public static Libro buscarPorId(int id) {
        return libros.get(id);
    }

    public static void listar() {
        if (libros.isEmpty()) {
            System.out.println("📚 No hay libros registrados.");
        } else {
            System.out.println("\n📖 Lista de libros:");
            for (Libro libro : libros.values()) {
                libro.mostrarDatos();
            }
        }
    }

    public static int obtenerProximoId() {
        return ultimoId + 1;
    }
}

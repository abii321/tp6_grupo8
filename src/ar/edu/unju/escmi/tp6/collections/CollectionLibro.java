package ar.edu.unju.escmi.tp6.collections;

import java.util.HashMap;
import java.util.Map;
import ar.edu.unju.escmi.tp6.dominio.Libro;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoEncontradoException;

public class CollectionLibro {
    public static Map<Integer, Libro> libros = new HashMap<>();

    public static void agregar(Libro libro) {
        libros.put(libro.getId(), libro);
    }

    public static Libro buscarPorId(int id) throws LibroNoEncontradoException {
        if (!libros.containsKey(id)) {
            throw new LibroNoEncontradoException("El libro con ID " + id + " no fue encontrado.");
        }
        return libros.get(id);
    }

    public static void listar() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.values().forEach(Libro::mostrarDatos);
        }
    }
}

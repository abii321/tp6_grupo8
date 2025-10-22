package ar.edu.unju.escmi.tp6.collections;

<<<<<<< HEAD
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
=======
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
>>>>>>> origin/main
        }
    }
}

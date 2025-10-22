package ar.edu.unju.escmi.tp6.dominio;

import java.time.LocalDate;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoDisponibleException;
import ar.edu.unju.escmi.tp6.exceptions.UsuarioNoRegistradoException;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoEncontradoException;

public class Prestamo {
    // atributos, constructores, getters/setters

    public static Prestamo realizarPrestamo(Libro libro, Usuario usuario, LocalDate fecha) throws Exception {
        if (usuario == null) throw new UsuarioNoRegistradoException("Usuario no registrado.");
        if (libro == null) throw new LibroNoEncontradoException("Libro no encontrado.");
        if (!libro.getEstado()) throw new LibroNoDisponibleException("El libro no está disponible.");

        libro.setEstado(false); // marcar como prestado
        return new Prestamo(1, fecha, null, libro, usuario); // ejemplo
    }
}

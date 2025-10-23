package ar.edu.unju.escmi.tp6.dominio;

import ar.edu.unju.escmi.tp6.collections.CollectionLibro;
import ar.edu.unju.escmi.tp6.collections.CollectionPrestamo;

public class Bibliotecario extends Usuario {
    private int legajo;

    public Bibliotecario(int id, String nombre, String apellido, String email, int legajo) {
        super(id, nombre, apellido, email);
        this.legajo = legajo;
    }

    public int getLegajo() {
        return legajo;
    }

    // ✅ Implementación del método abstracto de Usuario
    @Override
    public void mostrarDatos() {
        System.out.println("=== Bibliotecario ===");
        System.out.println("ID: " + getId());
        System.out.println("Nombre: " + getNombre() + " " + getApellido());
        System.out.println("Email: " + getEmail());
        System.out.println("Legajo: " + legajo);
    }

    /**
     * Registra un préstamo en la colección de préstamos.
     */
    public void realizarPrestamo(Prestamo prestamo) {
        CollectionPrestamo.agregar(prestamo); // método existente
        System.out.println(" Préstamo registrado por el bibliotecario.");
    }

    /**
     * Registra un nuevo libro en la colección de libros.
     */
    public void registrarLibro(Libro libro) {
        CollectionLibro.agregar(libro); //  método existente
        System.out.println("Libro registrado correctamente por el bibliotecario.");
    }

    /**
     * Marca un libro como devuelto (estado disponible nuevamente).
     */
    public void recepcionarLibro(Libro libro) {
        if (libro != null) {
            libro.setEstado(true);
            System.out.println(" Libro recepcionado correctamente y marcado como disponible.");
        } else {
            System.out.println(" No se puede recepcionar un libro nulo.");
        }
    }
}

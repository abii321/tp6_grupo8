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

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Legajo: " + legajo);
    }


    // Métodos definidos en el UML
  

    /**
     * Registra un préstamo en la colección de préstamos.
     */
    public void realizarPrestamo(Prestamo prestamo) {
        CollectionPrestamo.altaPrestamo(prestamo);
        System.out.println("Préstamo realizado por el bibliotecario.");
    }

    /**
     * Registra un nuevo libro en la colección de libros.
     */
    public void registrarLibro(Libro libro) {
        CollectionLibro.altaLibro(libro);
        System.out.println(" Libro registrado correctamente por el bibliotecario.");
    }

    /**
     * Marca un libro como devuelto (estado disponible nuevamente).
     */
    public void recepcionarLibro(Libro libro) {
        libro.setEstado(true);
        System.out.println(" Libro recepcionado correctamente y marcado como disponible.");
    }
}

package ar.edu.unju.escmi.tp6.dominio;

import ar.edu.unju.escmi.tp6.collections.CollectionLibro;
import ar.edu.unju.escmi.tp6.collections.CollectionPrestamo;

public class Bibliotecario extends Usuario {
    private int legajo;

    public Bibliotecario(String nombre, String apellido, String email, int legajo) {
        super(nombre, apellido, email);
        this.legajo = legajo;
    }

    /*public int getLegajo() {
        return legajo;
    }*/

    public void realizarPrestamo(Prestamo prestamo) {
        CollectionPrestamo.altaPrestamo(prestamo);
        System.out.println("Préstamo realizado por el bibliotecario.");
    }

    public void registrarLibro(Libro libro) {
        CollectionLibro.altaLibro(libro);
        System.out.println(" Libro registrado correctamente por el bibliotecario.");
    }

    public void recepcionarLibro(Libro libro) {
        libro.setEstado(true);
        System.out.println(" Libro recepcionado correctamente y marcado como disponible.");
    }
    
    @Override
    public void mostrarDatos() {
        System.out.println("=======================================");
        System.out.println("ID: " + id);
        System.out.println("Nombre Completo: " + nombre + " " + apellido);
        System.out.println("Email: " + email);
        System.out.println("Legajo: " + legajo);
    }
}

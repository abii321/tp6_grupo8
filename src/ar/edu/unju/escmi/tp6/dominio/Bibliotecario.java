package ar.edu.unju.escmi.tp6.dominio;

import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.escmi.tp6.collections.CollectionLibro;

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
        if (libro != null) {
            libro.setEstado(true);
            System.out.println("📘 Libro recepcionado correctamente y marcado como disponible.");
        } else {
            System.out.println("⚠️ No se puede recepcionar un libro nulo.");
        }
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

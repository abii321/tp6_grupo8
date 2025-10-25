package ar.edu.unju.escmi.tp6.dominio;

import java.time.LocalDate;

import ar.edu.unju.escmi.tp6.collections.CollectionPrestamo;

public class Prestamo {
    private static int contador = 1; // ID automático
    private int id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private Libro libro;
    private Usuario usuario;

    public Prestamo(LocalDate fechaPrestamo, LocalDate fechaDevolucion, Libro libro, Usuario usuario) {
        this.id = contador++;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.libro = libro;
        this.usuario = usuario;
    }

    public int getId() { return id; }
    public Libro getLibro() { return libro; }

    public void registrarDevolucion(LocalDate fechaDev) {
        this.fechaDevolucion = fechaDev;
    }

    public void mostrarDatos() {
        System.out.println("=======================================");
        System.out.println("ID del préstamo: " + id);
        System.out.println("Fecha de préstamo: " + fechaPrestamo);
        System.out.println("Fecha de devolución: " +
            (fechaDevolucion != null ? fechaDevolucion : "Aún no devuelto"));
        System.out.println("Libro: " + libro);
        System.out.println("Usuario: " + usuario);
    }
}


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

    public void registrarDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
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

    public static void recepcionarLibro(Libro libro, LocalDate fecha) {
        Prestamo p = CollectionPrestamo.buscarLibro(libro);
        if(p==null){
            System.out.println("El libro nunca se presto");
            return;
        }
        p.registrarDevolucion(fecha);
        libro.setEstado(true);
        System.out.println("Recepcion realizada correctamente");
    }
}


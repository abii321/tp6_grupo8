package ar.edu.unju.escmi.tp6.dominio;

import java.time.LocalDate;

import ar.edu.unju.escmi.tp6.collections.CollectionPrestamo;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoDisponibleException;
import ar.edu.unju.escmi.tp6.exceptions.UsuarioNoRegistradoException;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoEncontradoException;

public class Prestamo {
    private static int contador = 1; // ID automático
    private int id;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private Libro libro;
    private Usuario usuario;

    public Prestamo(String fechaPrestamo, String fechaDevolucion, Libro libro, Usuario usuario) {
        this.id = contador++;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.libro = libro;
        this.usuario = usuario;
    }

    public Libro getLibro() { return libro; }
    /*public int getId() { return id; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public Usuario getUsuario() { return usuario; }*/

    public void registrarDevolucion(String fechaDevolucion) {
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

    public static void recepcionarLibro(Libro libro, String fecha) {
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


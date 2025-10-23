package ar.edu.unju.escmi.tp6.dominio; 

import java.time.LocalDate;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoDisponibleException;
import ar.edu.unju.escmi.tp6.exceptions.UsuarioNoRegistradoException;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoEncontradoException;

public class Prestamo {
    // ✅ Atributos mínimos necesarios
    private int id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private Libro libro;
    private Usuario usuario;

    // ✅ Constructor (para poder crear el préstamo como hace realizarPrestamo)
    public Prestamo(int id, LocalDate fechaPrestamo, LocalDate fechaDevolucion, Libro libro, Usuario usuario) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.libro = libro;
        this.usuario = usuario;
    }

    // ✅ Getters y setters mínimos
    public int getId() { return id; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public Libro getLibro() { return libro; }
    public Usuario getUsuario() { return usuario; }

    public void setFechaDevolucion(LocalDate fechaDevolucion) { 
        this.fechaDevolucion = fechaDevolucion; 
    }

    // ⚡ ESTE ES EL MÉTODO QUE TE FALTABA
    public void mostrarDatos() {
        System.out.println("=======================================");
        System.out.println("ID del préstamo: " + id);
        System.out.println("Fecha de préstamo: " + fechaPrestamo);
        System.out.println("Fecha de devolución: " + 
            (fechaDevolucion != null ? fechaDevolucion : "Aún no devuelto"));
        System.out.println("Libro: " + (libro != null ? libro.getTitulo() : "Sin libro"));
        System.out.println("Usuario: " + (usuario != null ? usuario.getNombre() + " " + usuario.getApellido() : "Sin usuario"));
    }

    
    // ⚡ Tu método original queda intacto:
    public static Prestamo realizarPrestamo(Libro libro, Usuario usuario, LocalDate fecha) throws Exception {
        if (usuario == null) throw new UsuarioNoRegistradoException("Usuario no registrado.");
        if (libro == null) throw new LibroNoEncontradoException("Libro no encontrado.");
        if (!libro.isEstado()) throw new LibroNoDisponibleException("El libro no está disponible.");

        libro.setEstado(false); // marcar como prestado
        return new Prestamo(1, fecha, null, libro, usuario); // ejemplo
    }
}

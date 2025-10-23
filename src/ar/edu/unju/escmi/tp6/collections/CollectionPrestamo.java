package ar.edu.unju.escmi.tp6.collections;

import java.time.LocalDate;
import java.util.ArrayList;

import ar.edu.unju.escmi.tp6.dominio.Prestamo;
import ar.edu.unju.escmi.tp6.dominio.Libro;
import ar.edu.unju.escmi.tp6.utils.FechaUtil;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoDisponibleException;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoEncontradoException;

public class CollectionPrestamo {
    public static ArrayList<Prestamo> prestamos = new ArrayList<>();

    public static void agregar(Prestamo p) {
        prestamos.add(p);
    }

    public static void listar() {
        for (Prestamo p : prestamos) {
            p.mostrarDatos();
        }
    }

    // ⚡ NUEVO MÉTODO: devolver libro
    public static void devolverLibro(int idLibro, String fechaDevolucionStr)
            throws LibroNoEncontradoException, LibroNoDisponibleException {
        Libro libro = CollectionLibro.buscarPorId(idLibro);

        if (libro == null)
            throw new LibroNoEncontradoException("❌ Libro con ID " + idLibro + " no encontrado en la colección.");

        if (libro.isEstado())
            throw new LibroNoDisponibleException("⚠️ El libro ya está disponible. No se puede devolver dos veces.");

        Prestamo prestamoPendiente = null;
        for (Prestamo p : prestamos) {
            if (p.getLibro().getId() == idLibro && p.getFechaDevolucion() == null) {
                prestamoPendiente = p;
                break;
            }
        }

        if (prestamoPendiente == null)
            throw new LibroNoEncontradoException("📕 No se encontró un préstamo activo para el libro ID " + idLibro);

        try {
            LocalDate fechaDevolucion = FechaUtil.convertirStringLocalDate(fechaDevolucionStr);
            prestamoPendiente.registrarDevolucion(fechaDevolucion);
            System.out.println("✅ Libro devuelto correctamente: " + libro.getTitulo());
        } catch (Exception e) {
            System.out.println("⚠️ Error al registrar devolución: " + e.getMessage());
        }
    }
}

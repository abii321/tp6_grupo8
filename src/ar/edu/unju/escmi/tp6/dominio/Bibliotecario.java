package ar.edu.unju.escmi.tp6.dominio;

import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.escmi.tp6.collections.CollectionLibro;

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
        System.out.println("=== Bibliotecario ===");
        System.out.println("ID: " + getId());
        System.out.println("Nombre: " + getNombre() + " " + getApellido());
        System.out.println("Email: " + getEmail());
        System.out.println("Legajo: " + legajo);
    }

  //Método para registrar un libro — el bibliotecario ingresa los datos

    public void registrarLibro(Scanner sc) {
        try {
            int nuevoId = CollectionLibro.obtenerProximoId();
            System.out.println("\n--- Registro de Libro ---");
            System.out.println("ID asignado automáticamente: " + nuevoId);

            System.out.print("Ingrese título: ");
            String titulo = sc.nextLine();
            if (titulo.isEmpty()) throw new Exception("El título no puede estar vacío.");

            System.out.print("Ingrese autor: ");
            String autor = sc.nextLine();
            if (autor.isEmpty()) throw new Exception("El autor no puede estar vacío.");

            System.out.print("Ingrese ISBN (número): ");
            long isbn = sc.nextLong();
            sc.nextLine();

            Libro libro = new Libro(titulo, autor, isbn, true);
            CollectionLibro.agregar(libro);

            System.out.println("✅ Libro registrado correctamente por el bibliotecario.");

        } catch (InputMismatchException e) {
            System.out.println("⚠️ Error: el ISBN debe ser un número válido.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("⚠️ Error al registrar el libro: " + e.getMessage());
        }
    }

    public void realizarPrestamo(Prestamo prestamo) {
        System.out.println("📚 Préstamo registrado por el bibliotecario.");
    }

    public void recepcionarLibro(Libro libro) {
        if (libro != null) {
            libro.setEstado(true);
            System.out.println("📘 Libro recepcionado correctamente y marcado como disponible.");
        } else {
            System.out.println("⚠️ No se puede recepcionar un libro nulo.");
        }
    }
}

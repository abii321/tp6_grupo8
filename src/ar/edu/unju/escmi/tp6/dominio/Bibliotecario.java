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
        super.mostrarDatos();
        System.out.println("Legajo: " + legajo);
    }
public void registrarLibro(Scanner sc) {
        try {
            int nuevoId = CollectionLibro.obtenerProximoId();
            System.out.println("\n--- Registro de Libro ---");
            System.out.println("ID: " + nuevoId);

            System.out.print("Ingrese título: ");
            String titulo = sc.nextLine();

            System.out.print("Ingrese autor: ");
            String autor = sc.nextLine();

            System.out.print("Ingrese ISBN (número): ");
            long isbn = sc.nextLong();
            sc.nextLine();

            Libro libro = new Libro(titulo, autor, isbn, true);
            CollectionLibro.agregar(libro);

            System.out.println(" Libro registrado correctamente con ID " + libro.getId());

        } catch (InputMismatchException e) {
            System.out.println(" Error: Debe ingresar un número válido para el ISBN.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println(" Error inesperado al registrar el libro: " + e.getMessage());
        }
    }
}

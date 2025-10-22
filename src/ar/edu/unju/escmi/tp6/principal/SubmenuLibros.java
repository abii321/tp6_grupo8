package ar.edu.unju.escmi.tp6.principal;

import java.util.Scanner;
import ar.edu.unju.escmi.tp6.collections.CollectionLibro;
import ar.edu.unju.escmi.tp6.dominio.Libro;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoEncontradoException;
//import ar.edu.unju.escmi.tp6.exceptions.LibroNoDisponibleException;

public class SubmenuLibros {

    public static void main(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n===== SUBMENU LIBROS =====");
            System.out.println("1 - Registrar nuevo libro");
            System.out.println("2 - Buscar libro por ID");
            System.out.println("3 - Listar todos los libros");
            System.out.println("4 - Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    registrarLibro(sc);
                    break;
                case 2:
                    buscarLibro(sc);
                    break;
                case 3:
                    CollectionLibro.listar();
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println(" Opción inválida, intente nuevamente.");
            }
        } while (opcion != 4);
    }

    private static void registrarLibro(Scanner sc) {
        try {
            System.out.println("\n--- Registro de Libro ---");
            System.out.print("Ingrese ID del libro: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese título: ");
            String titulo = sc.nextLine();
            System.out.print("Ingrese autor: ");
            String autor = sc.nextLine();
            System.out.print("Ingrese ISBN: ");
            String isbn = sc.nextLine();

            Libro nuevo = new Libro(id, titulo, autor, isbn, true);
            CollectionLibro.agregar(nuevo);
            System.out.println(" Libro registrado correctamente.");
        } catch (Exception e) {
            System.out.println(" Error al registrar el libro: " + e.getMessage());
        }
    }

    private static void buscarLibro(Scanner sc) {
        try {
            System.out.print("\nIngrese ID del libro a buscar: ");
            int id = sc.nextInt();
            sc.nextLine();

            Libro libro = CollectionLibro.buscarPorId(id);
            System.out.println("📖bro encontrado:");
            libro.mostrarDatos();

        } catch (LibroNoEncontradoException e) {
            System.out.println("⚠️ " + e.getMessage());
        } catch (Exception e) {
            System.out.println(" Error al buscar el libro: " + e.getMessage());
        }
    }
}

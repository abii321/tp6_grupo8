package ar.edu.unju.escmi.tp6.principal;

import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.escmi.tp6.collections.CollectionLibro;
import ar.edu.unju.escmi.tp6.collections.CollectionUsuario;
import ar.edu.unju.escmi.tp6.dominio.Libro;
import ar.edu.unju.escmi.tp6.dominio.Usuario;
import ar.edu.unju.escmi.tp6.dominio.Bibliotecario;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoEncontradoException;

public class SubmenuLibros {

    public static void mostrarMenu(Scanner sc) {
        int opcion = 0;

        do {
            try {
                System.out.println("\n--- Submenú de Libros ---");
                System.out.println("1 - Registrar libro (solo bibliotecarios)");
                System.out.println("2 - Listar libros");
                System.out.println("3 - Buscar libro por ID");
                System.out.println("4 - Volver al menú principal");
                System.out.print("Ingrese una opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1 -> registrarLibro(sc);
                    case 2 -> CollectionLibro.listar();
                    case 3 -> buscarLibro(sc);
                    case 4 -> System.out.println("Volviendo al menú principal...");
                    default -> System.out.println("❌ Opción inválida. Intente nuevamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println("⚠️ Error: Debe ingresar un número entero para la opción.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("⚠️ Error inesperado: " + e.getMessage());
            }

        } while (opcion != 4);
    }

    private static void registrarLibro(Scanner sc) {
        try {
            System.out.print("Ingrese su ID de usuario: ");
            int idUsuario = sc.nextInt();
            sc.nextLine();

            Usuario usuario = CollectionUsuario.buscarPorId(idUsuario);

            if (usuario instanceof Bibliotecario bibliotecario) {
                // solo el bibliotecario puede registrar
                int proximoId = CollectionLibro.obtenerProximoId();
                System.out.println("ID: " + proximoId);

                System.out.print("Ingrese título: ");
                String titulo = sc.nextLine();

                System.out.print("Ingrese autor: ");
                String autor = sc.nextLine();

                System.out.print("Ingrese ISBN (número): ");
                long isbn = sc.nextLong();
                sc.nextLine();

                Libro libro = new Libro(titulo, autor, isbn, true);
                CollectionLibro.agregar(libro);

                System.out.println("✅ Libro registrado correctamente por el bibliotecario " 
                                   + bibliotecario.getNombre() + ".");
            } else if (usuario != null) {
                System.out.println("❌ Solo los bibliotecarios pueden registrar libros.");
            } else {
                System.out.println("⚠️ Usuario no encontrado. Debe registrarse primero.");
            }

        } catch (InputMismatchException e) {
            System.out.println("⚠️ Error: Ingrese un número válido para el ID o ISBN.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("⚠️ Error inesperado al registrar el libro: " + e.getMessage());
        }
    }

    private static void buscarLibro(Scanner sc) {
        try {
            System.out.print("Ingrese el ID del libro que desea buscar: ");
            int id = sc.nextInt();
            sc.nextLine();

            Libro libro = CollectionLibro.buscarPorId(id);
            if (libro == null)
                throw new LibroNoEncontradoException("❌ El libro con ID " + id + " no existe en la biblioteca.");

            libro.mostrarDatos();

        } catch (InputMismatchException e) {
            System.out.println("⚠️ Error: Debe ingresar un número válido para el ID.");
            sc.nextLine();
        } catch (LibroNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}

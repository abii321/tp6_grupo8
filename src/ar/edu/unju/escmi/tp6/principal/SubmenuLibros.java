package ar.edu.unju.escmi.tp6.principal;

import java.time.LocalDate;
import java.util.Scanner;

import ar.edu.unju.escmi.tp6.collections.*;
import ar.edu.unju.escmi.tp6.dominio.*;
import ar.edu.unju.escmi.tp6.utils.FechaUtil;

public class SubmenuLibros {

    public static void mostrarMenu(Scanner sc) {
        int opcion;

        do {
            System.out.println("\n=====  GESTIÓN DE LIBROS  =====");
            System.out.println("1 - Registrar libro");
            System.out.println("2 - Listar libros");
            System.out.println("3 - Realizar préstamo");
            System.out.println("4 - Listar préstamos");
            System.out.println("5 - Devolver libro");  // ✅ Nueva opción
            System.out.println("6 - Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    registrarLibro(sc);
                    break;
                case 2:
                    CollectionLibro.listar();
                    break;
                case 3:
                    realizarPrestamo(sc);
                    break;
                case 4:
                    CollectionPrestamo.listar();
                    break;
                case 5:
                    devolverLibro(sc); // ✅ NUEVO
                    break;
                case 6:
                    System.out.println("🔙 Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida, intente nuevamente.");
                    break;
            }

        } while (opcion != 6);
    }

    // 🔹 Registrar libro
    private static void registrarLibro(Scanner sc) {
        System.out.println("\n--- Registro de Libro ---");
        System.out.print("Ingrese título: ");
        String titulo = sc.nextLine();
        System.out.print("Ingrese autor: ");
        String autor = sc.nextLine();
        System.out.print("Ingrese ISBN: ");
        long isbn = sc.nextLong();
        sc.nextLine();

        Libro libro = new Libro(titulo, autor, isbn, true);
        CollectionLibro.agregar(libro);
        System.out.println("✅ Libro registrado correctamente.");
    }

    // 🔹 Realizar préstamo
    private static void realizarPrestamo(Scanner sc) {
        try {
            System.out.println("\n--- Registrar Préstamo ---");
            System.out.print("Ingrese ID del libro: ");
            int idLibro = sc.nextInt();
            sc.nextLine();

            Libro libro = CollectionLibro.buscarPorId(idLibro);
            if (libro == null) {
                System.out.println("⚠️ No existe un libro con ese ID.");
                return;
            }

            System.out.print("Ingrese ID del usuario: ");
            int idUsuario = sc.nextInt();
            sc.nextLine();

            Usuario usuario = CollectionUsuario.buscarPorId(idUsuario);
            if (usuario == null) {
                System.out.println("⚠️ No existe un usuario con ese ID.");
                return;
            }

            System.out.print("Ingrese la fecha del préstamo (dd/MM/yyyy): ");
            String fechaStr = sc.nextLine();
            LocalDate fechaPrestamo = FechaUtil.convertirStringLocalDate(fechaStr);

            Prestamo prestamo = Prestamo.realizarPrestamo(libro, usuario, fechaPrestamo);
            CollectionPrestamo.agregar(prestamo);
            System.out.println("✅ Préstamo registrado correctamente.");

        } catch (Exception e) {
            System.out.println("⚠️ Error al registrar préstamo: " + e.getMessage());
        }
    }

    // ✅ NUEVO: Devolver libro
    private static void devolverLibro(Scanner sc) {
        System.out.println("\n--- Devolver Libro ---");
        System.out.print("Ingrese el ID del libro a devolver: ");
        int idLibro = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese la fecha de devolución (dd/MM/yyyy): ");
        String fechaStr = sc.nextLine();

        try {
            CollectionPrestamo.devolverLibro(idLibro, fechaStr);
        } catch (Exception e) {
            System.out.println("⚠️ " + e.getMessage());
        }
    }
}

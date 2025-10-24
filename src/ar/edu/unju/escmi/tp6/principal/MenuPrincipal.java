package ar.edu.unju.escmi.tp6.principal;

import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.escmi.tp6.collections.*;
import ar.edu.unju.escmi.tp6.dominio.*;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoEncontradoException;

public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            try {
                System.out.println("\n===== SISTEMA DE GESTIÓN DE BIBLIOTECA =====");
                System.out.println("1 - Registrar libro (solo bibliotecario)");
                System.out.println("2 - Listar libros");
                System.out.println("3 - Buscar libro por ID");
                System.out.println("4 - Registrar usuario");
                System.out.println("5 - Listar usuarios");
                System.out.println("6 - Realizar préstamo");
                System.out.println("7 - Listar préstamos");
                System.out.println("8 - Devolver libro");
                System.out.println("9 - Salir");
                System.out.print("Seleccione una opción: ");

                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1 -> registrarLibro(sc);
                    case 2 -> CollectionLibro.listar();
                    case 3 -> buscarLibro(sc);
                    case 4 -> registrarUsuario(sc);
                    case 5 -> CollectionUsuario.listarUsuarios();
                    case 6 -> realizarPrestamo(sc);
                    case 7 -> CollectionPrestamo.listar();
                    case 8 -> devolverLibro(sc);
                    case 9 -> System.out.println(" Saliendo del sistema...");
                    default -> System.out.println(" Opción inválida. Intente nuevamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println(" Error: debe ingresar un número entero.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println(" Error inesperado: " + e.getMessage());
            }

        } while (opcion != 9);

        sc.close();
    }

    // 🔹 REGISTRAR LIBRO (solo bibliotecario)
    private static void registrarLibro(Scanner sc) {
        try {
            System.out.print("Ingrese ID del bibliotecario: ");
            int idUsuario = sc.nextInt();
            sc.nextLine();

            Usuario usuario = CollectionUsuario.buscarPorId(idUsuario);

            if (usuario == null) {
                System.out.println(" Usuario no encontrado.");
                return;
            }

            if (!(usuario instanceof Bibliotecario)) {
                System.out.println(" Solo los bibliotecarios pueden registrar libros.");
                return;
            }

            Bibliotecario biblio = (Bibliotecario) usuario;
            biblio.registrarLibro(sc); 

        } catch (InputMismatchException e) {
            System.out.println(" Error: debe ingresar un número válido para ID o ISBN.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println(" Error inesperado al registrar libro: " + e.getMessage());
        }
    }

    // 🔹 BUSCAR LIBRO
    private static void buscarLibro(Scanner sc) {
        try {
            System.out.print("Ingrese el ID del libro: ");
            int id = sc.nextInt();
            sc.nextLine();

            Libro libro = CollectionLibro.buscarPorId(id);
            if (libro == null)
                throw new LibroNoEncontradoException("❌ El libro con ID " + id + " no existe.");

            libro.mostrarDatos();

        } catch (InputMismatchException e) {
            System.out.println(" Error: debe ingresar un número válido.");
            sc.nextLine();
        } catch (LibroNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(" Error inesperado: " + e.getMessage());
        }
    }

    // 🔹 REGISTRAR USUARIO
    private static void registrarUsuario(Scanner sc) {
        try {
            System.out.println("\n--- Registrar Usuario ---");
            System.out.print("Ingrese tipo de usuario (1 - Alumno, 2 - Bibliotecario): ");
            int tipo = sc.nextInt();
            sc.nextLine();

            System.out.print("Ingrese nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese apellido: ");
            String apellido = sc.nextLine();
            System.out.print("Ingrese email: ");
            String email = sc.nextLine();

            Usuario nuevo;

            if (tipo == 1) {
                System.out.print("Ingrese curso: ");
                String curso = sc.nextLine();
                System.out.print("Ingrese número de libreta: ");
                int libreta = sc.nextInt();
                sc.nextLine();
                nuevo = new Alumno(0, nombre, apellido, email, curso, libreta);
            } else if (tipo == 2) {
                System.out.print("Ingrese legajo: ");
                int legajo = sc.nextInt();
                sc.nextLine();
                nuevo = new Bibliotecario(0, nombre, apellido, email, legajo);
            } else {
                System.out.println(" Tipo de usuario inválido.");
                return;
            }

            CollectionUsuario.registrarUsuario(nuevo);
            System.out.println(" Usuario registrado correctamente.");

        } catch (InputMismatchException e) {
            System.out.println(" Error: formato de dato incorrecto. Intente nuevamente.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println(" Error inesperado al registrar usuario: " + e.getMessage());
        }
    }

    // 🔹 REALIZAR PRÉSTAMO 
    private static void realizarPrestamo(Scanner sc) {
        try {
            System.out.println("\n--- Registrar Préstamo ---");
            System.out.print("Ingrese ID del libro: ");
            int idLibro = sc.nextInt();
            sc.nextLine();

            Libro libro = CollectionLibro.buscarPorId(idLibro);
            if (libro == null) {
                System.out.println(" No existe un libro con ese ID.");
                return;
            }

            System.out.print("Ingrese ID del usuario: ");
            int idUsuario = sc.nextInt();
            sc.nextLine();

            Usuario usuario = CollectionUsuario.buscarPorId(idUsuario);
            if (usuario == null) {
                System.out.println(" No existe un usuario con ese ID.");
                return;
            }

            System.out.print("Ingrese la fecha del préstamo (dd/MM/yyyy): ");
            String fechaStr = sc.nextLine();
            java.time.LocalDate fechaPrestamo = ar.edu.unju.escmi.tp6.utils.FechaUtil.convertirStringLocalDate(fechaStr);

            Prestamo prestamo = Prestamo.realizarPrestamo(libro, usuario, fechaPrestamo);
            CollectionPrestamo.agregar(prestamo);
            System.out.println(" Préstamo registrado correctamente.");

        } catch (Exception e) {
            System.out.println(" Error al registrar préstamo: " + e.getMessage());
        }
    }

    // 🔹 DEVOLVER LIBRO 
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
            System.out.println(" " + e.getMessage());
        }
    }
}

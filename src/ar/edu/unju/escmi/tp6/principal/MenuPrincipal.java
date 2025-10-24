package ar.edu.unju.escmi.tp6.principal;

import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.escmi.tp6.collections.*;
import ar.edu.unju.escmi.tp6.dominio.*;
import ar.edu.unju.escmi.tp6.utils.FechaUtil;

public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            try {
                System.out.println("\n===== SISTEMA DE GESTIÓN DE BIBLIOTECA =====");
                System.out.println("1 - Registrar libro");
                System.out.println("2 - Registrar usuario");
                System.out.println("3 - Prestamo de libro");
                System.out.println("4 - Devolucion de libro");
                System.out.println("5 - Listar libros");
                System.out.println("6 - Listar usuarios");
                System.out.println("7 - Salir");
                System.out.print("Seleccione una opción: ");

                opcion = sc.nextInt(); sc.nextLine(); 

                switch (opcion) {
                    case 1 -> registrarLibro(sc);
                    case 2 -> registrarUsuario(sc);
                    //case 3 -> Biblotecario.realizarPrestamo(sc);
                    case 4 -> devolverLibro(sc);
                    case 5 -> CollectionLibro.listar();
                    case 6 -> CollectionUsuario.listarUsuarios();
                    case 7 -> System.out.println(" Saliendo del sistema...");
                    default -> System.out.println(" Opción inválida. Intente nuevamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println(" Error: debe ingresar un número entero.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println(" Error inesperado: " + e.getMessage());
            }

        } while (opcion != 7);

        sc.close();
    }

    // 🔹 REGISTRAR LIBRO (solo bibliotecario)
    private static void registrarLibro(Scanner sc) {
        try {
            System.out.println("\n--- Registro de Libro ---");
            System.out.print("Ingrese título: "); String titulo = sc.nextLine();
            if (titulo.isEmpty()) throw new Exception("El título no puede estar vacío.");

            System.out.print("Ingrese autor: "); String autor = sc.nextLine();
            if (autor.isEmpty()) throw new Exception("El autor no puede estar vacío.");

            System.out.print("Ingrese ISBN (número): "); long isbn = sc.nextLong();
            sc.nextLine();

            Libro libro = new Libro(titulo, autor, isbn);
            CollectionLibro.altaLibro(libro);

            System.out.println("✅ Libro registrado correctamente por el bibliotecario.");

        } catch (InputMismatchException e) {
            System.out.println("⚠️ Error: el ISBN debe ser un número válido.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("⚠️ Error al registrar el libro: " + e.getMessage());
        }
    }

    // 🔹 REGISTRAR USUARIO
    private static void registrarUsuario(Scanner sc) throws Exception {
        Usuario nuevo;

        System.out.println("\n--- Registro de Usuario ---");
        // VALIDACIONES DE ENTRADA -----------------------
        System.out.print("Ingrese nombre: "); String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) throw new Exception("El nombre no puede estar vacío.");

        System.out.print("Ingrese apellido: "); String apellido = sc.nextLine().trim();
        if (apellido.isEmpty()) throw new Exception("El apellido no puede estar vacío.");

        System.out.print("Ingrese email: "); String email = sc.nextLine().trim();
        if (email.isEmpty() || !email.contains("@")) throw new Exception("Debe ingresar un email válido (con '@').");

        System.out.println("Tipo de usuario:");
        System.out.println("1 - Alumno");
        System.out.println("2 - Bibliotecario");
        int tipo = sc.nextInt(); sc.nextLine();
        if (!sc.hasNextInt()) {
            sc.nextLine(); // limpiar entrada incorrecta
            throw new Exception("Debe ingresar un número (1 o 2).");
        }
    
        if (tipo == 1) {
            System.out.print("Ingrese curso: "); String curso = sc.nextLine().trim();
            if (curso.isEmpty()) throw new Exception("El curso no puede estar vacío.");

            System.out.print("Ingrese número de libreta: "); int nroLibreta = sc.nextInt(); sc.nextLine();
            if (!sc.hasNextInt()) {
                sc.nextLine(); // limpiar entrada incorrecta
                throw new Exception("El número de libreta debe ser un valor numérico.");
            }

            nuevo = new Alumno(nombre, apellido, email, curso, nroLibreta);

        } else if (tipo == 2) {
            System.out.print("Ingrese legajo: "); int legajo = sc.nextInt(); sc.nextLine();
            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new Exception("El legajo debe ser un número entero.");
            }
            nuevo = new Bibliotecario(nombre, apellido, email, legajo);

        } else {
            throw new Exception("Tipo de usuario inválido. Debe ser 1 (Alumno) o 2 (Bibliotecario).");
        }

        CollectionUsuario.registrarUsuario(nuevo);
        System.out.println(" Usuario registrado correctamente.");
    }

    // 🔹 REALIZAR PRÉSTAMO 
    /*private static void realizarPrestamo(Scanner sc) {
        try {
            System.out.println("\n--- Registrar Préstamo ---");
            System.out.print("Ingrese ID del libro: "); int idLibro = sc.nextInt(); sc.nextLine();
            Libro libro = CollectionLibro.buscarPorId(idLibro);
            if (libro == null) {
                System.out.println(" No existe un libro con ese ID.");
                return;
            }

            System.out.print("Ingrese ID del usuario: "); int idUsuario = sc.nextInt(); sc.nextLine();
            Usuario usuario = CollectionUsuario.buscarPorId(idUsuario);
            if (usuario == null) {
                System.out.println(" No existe un usuario con ese ID.");
                return;
            }

            System.out.print("Ingrese la fecha del préstamo (dd/MM/yyyy): "); String fechaStr = sc.nextLine();
            java.time.LocalDate fechaPrestamo = ar.edu.unju.escmi.tp6.utils.FechaUtil.convertirStringLocalDate(fechaStr);

            /*Prestamo prestamo = Prestamo.realizarPrestamo(libro, usuario, fechaPrestamo);
            CollectionPrestamo.agregar(prestamo);
            System.out.println(" Préstamo registrado correctamente.");

        } catch (Exception e) {
            System.out.println(" Error al registrar préstamo: " + e.getMessage());
        }
    }*/

    // 🔹 DEVOLVER LIBRO (versión optimizada y sin warnings)
    private static void devolverLibro(Scanner sc) {
        System.out.println("\n--- Devolver Libro ---");
        int idLibro; String fechaStr;
        try {
            System.out.print("Ingrese el ID del libro a devolver: "); idLibro = sc.nextInt(); sc.nextLine(); 
        } catch (java.util.InputMismatchException e) {
            System.out.println("⚠️ Error: el ID debe ser un número entero válido.");
            sc.nextLine(); // limpiar entrada incorrecta
            return;
        }
        System.out.print("Ingrese la fecha de devolución (dd/MM/yyyy): ");
        fechaStr = sc.nextLine();
        try {
            // Validamos el formato de la fecha (lanza excepción si es inválido)
            FechaUtil.convertirStringLocalDate(fechaStr);

            // Si la validación pasa, realizamos la devolución
            Libro libro = CollectionLibro.buscarPorId(idLibro);
            if(libro == null) {System.out.println("No existe el libro"); return; }
            Bibliotecario.recepcionarLibro(libro,fechaStr);
            //CollectionPrestamo.devolverLibro(idLibro, fechaStr);

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("⚠️ Formato de fecha incorrecto. Use el formato dd/MM/yyyy (por ejemplo 23/10/2025).");
        } catch (Exception e) {
            System.out.println("⚠️ " + e.getMessage());
        }
    }

}

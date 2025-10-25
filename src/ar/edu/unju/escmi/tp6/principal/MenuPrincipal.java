package ar.edu.unju.escmi.tp6.principal;

import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.escmi.tp6.collections.*;
import ar.edu.unju.escmi.tp6.dominio.*;
import ar.edu.unju.escmi.tp6.exceptions.*;
import ar.edu.unju.escmi.tp6.utils.FechaUtil;
import java.time.LocalDate;

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
                System.out.println("7 - Consultar libros disponibles");
                System.out.println("8 - Salir");
                System.out.print("Seleccione una opción: ");

                opcion = sc.nextInt(); sc.nextLine(); 

                switch (opcion) {
                    case 1 -> registrarLibro(sc);
                    case 2 -> registrarUsuario(sc);
                    case 3 -> realizarPrestamo(sc);
                    case 4 -> devolverLibro(sc);
                    case 5 -> CollectionLibro.listar();
                    case 6 -> CollectionUsuario.listarUsuarios();
                    case 7 -> Usuario.consultarLibrosDisponibles();
                    case 8 -> System.out.println(" Saliendo del sistema...");
                    default -> System.out.println(" Opción inválida. Intente nuevamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println(" Error: debe ingresar un número entero.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println(" Error inesperado: " + e.getMessage());
            }

        } while (opcion != 8);

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
        } catch (InputMismatchException e) {
            System.out.println("Error: el ISBN debe ser un número válido.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error al registrar el libro: " + e.getMessage());
        }
    }

    // 🔹 REGISTRAR USUARIO
   private static void registrarUsuario(Scanner sc) {
    try {
        Usuario nuevo;

        System.out.println("\n--- Registro de Usuario ---");

        System.out.print("Ingrese nombre: "); String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) throw new Exception("El nombre no puede estar vacío.");

        System.out.print("Ingrese apellido: "); String apellido = sc.nextLine().trim();
        if (apellido.isEmpty()) throw new Exception("El apellido no puede estar vacío.");

        System.out.print("Ingrese email: "); String email = sc.nextLine().trim();
        if (email.isEmpty() || !email.contains("@")) throw new Exception("Debe ingresar un email válido (con '@').");
        
        System.out.println("Tipo de usuario:");
        System.out.println("1 - Alumno");
        System.out.println("2 - Bibliotecario");
        int tipo;
        try {
            tipo = sc.nextInt(); sc.nextLine(); 
        } catch (InputMismatchException e) {
            sc.nextLine();
            throw new Exception("Debe ingresar un número (1 o 2).");
        }

        if (tipo == 1) {
            System.out.print("Ingrese curso: "); String curso = sc.nextLine().trim();
            if (curso.isEmpty()) throw new Exception("El curso no puede estar vacío.");

            int nroLibreta;
            try {
                System.out.print("Ingrese número de libreta: ");
                nroLibreta = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
                throw new Exception("El número de libreta debe ser un valor numérico.");
            }
            nuevo = new Alumno(nombre, apellido, email, curso, nroLibreta);

        } else if (tipo == 2) {
            int legajo;
            try {
                System.out.print("Ingrese legajo: ");
                legajo = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
                throw new Exception("El legajo debe ser un número entero.");
            }

            nuevo = new Bibliotecario(nombre, apellido, email, legajo);

        } else {
            throw new Exception("Tipo de usuario inválido. Debe ser 1 (Alumno) o 2 (Bibliotecario).");
        }

        CollectionUsuario.registrarUsuario(nuevo);

    } catch (InputMismatchException e) {
        System.out.println("Error: debe ingresar valores numéricos válidos.");
        sc.nextLine();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

    private static void realizarPrestamo(Scanner sc){
        try {
            System.out.println("\n--- Registrar Préstamo ---");

            System.out.print("Ingrese ID del usuario: "); int idUsuario = sc.nextInt(); sc.nextLine();
            Usuario usuario = CollectionUsuario.buscarPorId(idUsuario);
            if (usuario == null) throw new UsuarioNoRegistradoException("No existe un usuario con ese ID.");

            System.out.print("Ingrese ID del libro: "); int idLibro = sc.nextInt(); sc.nextLine();
            Libro libro = CollectionLibro.buscarPorId(idLibro);
            if (libro == null) throw new LibroNoEncontradoException("No existe un libro con ese ID.");
            if (!libro.getEstado()) throw new LibroNoDisponibleException("El libro no está disponible para préstamo.");

            System.out.print("Ingrese la fecha del préstamo (dd/MM/yyyy): "); String fechaStr = sc.nextLine();
            LocalDate fechaPrestamo = FechaUtil.convertirStringLocalDate(fechaStr);

            if( libro.getEstado()==false) {
                System.out.println("El libro no esta disponible");
                return;
            }
            Prestamo nuevo = new Prestamo(fechaPrestamo,null,libro,usuario);
            Bibliotecario.registrarPrestamo(nuevo);

        } catch (UsuarioNoRegistradoException | LibroNoEncontradoException | LibroNoDisponibleException e) {
            System.out.println( e.getMessage());
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Fecha inválida. Use el formato dd/MM/yyyy.");
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: debe ingresar un número entero válido.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    // 🔹 DEVOLVER LIBRO 
    private static void devolverLibro(Scanner sc) {
        System.out.println("\n--- Devolver Libro ---");
        int idUser, idLibro; String fechaStr;
        try {
            System.out.println("Ingrese el ID del usuario: "); idUser = sc.nextInt(); sc.nextLine();
            System.out.print("Ingrese el ID del libro a devolver: "); idLibro = sc.nextInt(); sc.nextLine(); 
            System.out.print("Ingrese la fecha de devolución (dd/MM/yyyy): "); fechaStr = sc.nextLine();
    
            Usuario usuario = CollectionUsuario.buscarPorId(idUser);
            if( usuario == null ) throw new UsuarioNoRegistradoException("No existe un usuario con ese ID");
            Libro libro = CollectionLibro.buscarPorId(idLibro);
            if(libro == null)throw new LibroNoEncontradoException("No existe un libro con ese ID.");
            
            LocalDate fecha=null;
            try{
                fecha = FechaUtil.convertirStringLocalDate(fechaStr);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Use el formato dd/MM/yyyy (por ejemplo 23/10/2025).");
            } 

            Bibliotecario.recepcionarLibro(usuario, libro, fecha);        
        } catch (InputMismatchException e) {
            System.out.println("Error: los IDs deben ser números enteros válidos.");
            sc.nextLine();
        } catch (UsuarioNoRegistradoException | LibroNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

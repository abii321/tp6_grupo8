package ar.edu.unju.escmi.tp6.principal;

import java.util.Scanner;
import ar.edu.unju.escmi.tp6.collections.CollectionUsuario;
import ar.edu.unju.escmi.tp6.dominio.*;


public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=====  SISTEMA DE GESTIÓN DE BIBLIOTECA =====");
            System.out.println("1 - Registrar libro");
            System.out.println("2 - Salir");
            System.out.println("3 - Registrar usuario");   // ➕ agregado
            System.out.println("4 - Listar usuarios");      // ➕ agregado
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                case 5:
                    SubmenuLibros.main(sc);
                    break;
                case 2:
                    System.out.println(" Saliendo del sistema...");
                    break;
                case 3: // ➕ registrar usuario
                    registrarUsuario(sc);
                    break;
                case 4: // ➕ listar usuarios
                    listarUsuarios();
                    break;
                default:
                    System.out.println(" Opción inválida, intente de nuevo.");
                    break;
            }
        } while (opcion != 2);

        sc.close();
    }

    // 🔹 Método para registrar usuario (nuevo)
    private static void registrarUsuario(Scanner sc) {
        try {
            System.out.println("\n--- Registro de Usuario ---");
            System.out.print("Ingrese ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese apellido: ");
            String apellido = sc.nextLine();
            System.out.print("Ingrese email: ");
            String email = sc.nextLine();

            System.out.println("Tipo de usuario:");
            System.out.println("1 - Alumno");
            System.out.println("2 - Bibliotecario");
            int tipo = sc.nextInt();
            sc.nextLine();

            Usuario usuario;
            if (tipo == 1) {
                System.out.print("Ingrese curso: ");
                int curso = sc.nextInt();
                System.out.print("Ingrese número de libreta: ");
                int nroLibreta = sc.nextInt();
                sc.nextLine();
                usuario = new Alumno(id, nombre, apellido, email, curso, nroLibreta);
            } else if (tipo == 2) {
                System.out.print("Ingrese legajo: ");
                int legajo = sc.nextInt();
                sc.nextLine();
                usuario = new Bibliotecario(id, nombre, apellido, email, legajo);
            } else {
                System.out.println("Tipo inválido. Registro cancelado.");
                return;
            }

            CollectionUsuario.registrar(usuario);
            System.out.println("✅ Usuario registrado correctamente.");

        } catch (Exception e) {
            System.out.println("⚠️ Error al registrar usuario: " + e.getMessage());
        }
    }

    // 🔹 Método para listar usuarios (nuevo)
    private static void listarUsuarios() {
        System.out.println("\n--- Listado de Usuarios ---");
        CollectionUsuario.listar();
    }
}

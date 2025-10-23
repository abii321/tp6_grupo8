package ar.edu.unju.escmi.tp6.principal;

import java.util.Scanner;
import ar.edu.unju.escmi.tp6.collections.*;
import ar.edu.unju.escmi.tp6.dominio.*;

public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=====  SISTEMA DE GESTIÓN DE BIBLIOTECA =====");
            System.out.println("1 - Registrar libro");
            System.out.println("2 - Salir");
            System.out.println("3 - Registrar usuario");
            System.out.println("4 - Listar usuarios");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                case 5:
                    SubmenuLibros.mostrarMenu(sc);
                    break;

                case 2:
                    System.out.println("Saliendo del sistema...");
                    break;

                case 3:
                 boolean registrado = false;
                  while (!registrado) {
                        try {
                        Usuario nuevo = CollectionUsuario.crearUsuarioDesdeConsola(sc);
                        CollectionUsuario.registrarUsuario(nuevo);
                        System.out.println("Usuario registrado correctamente.");
                        registrado = true;
                       } catch (Exception e) {
                     System.out.println("Error al registrar usuario: " + e.getMessage());
                     System.out.println("Intente nuevamente.\n");
                        sc.nextLine(); // limpia buffer si el usuario ingresó algo inválido
                       }
             }
                break;

                case 4:
                    System.out.println("\n--- Listado de Usuarios ---");
                    CollectionUsuario.listarUsuarios();
                    break;

                default:
                    System.out.println("Opción inválida, intente de nuevo.");
                    break;
            }

        } while (opcion != 2);

        sc.close();
    }
}

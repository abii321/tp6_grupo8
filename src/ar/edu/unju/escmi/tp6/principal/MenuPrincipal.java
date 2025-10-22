package ar.edu.unju.escmi.tp6.principal;

import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=====  SISTEMA DE GESTIÓN DE BIBLIOTECA =====");
            System.out.println("1 - Registrar libro");
            System.out.println("2 - Salir");
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
                default:
                    System.out.println(" Opción inválida, intente de nuevo.");
                    break;
            }
        } while (opcion != 2);

        sc.close();
    }
}

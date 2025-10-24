package ar.edu.unju.escmi.tp6.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import ar.edu.unju.escmi.tp6.dominio.*;

public class CollectionUsuario {
    public static Map<Integer, Usuario> usuarios = new HashMap<>();

    //  Registrar usuario (asigna ID automáticamente si no tiene)
    public static void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    //  Buscar usuario por ID
    public static Usuario buscarPorId(int id) {
        return usuarios.get(id);
    }

    //  Listar usuarios
    public static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("✖ No hay usuarios registrados.");
        } else {
            usuarios.values().forEach(Usuario::mostrarDatos);
        }
    }

    //  Crear usuario desde consola (sin pedir ID)
    /*public static Usuario crearUsuarioDesdeConsola(Scanner sc) throws Exception {
        System.out.println("\n--- Registro de Usuario ---");

        int id = generarId(); //  ID autogenerado
        System.out.println("(ID asignado automáticamente: " + id + ")");

        // VALIDACIONES DE ENTRADA -----------------------
        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) throw new Exception("El nombre no puede estar vacío.");

        System.out.print("Ingrese apellido: ");
        String apellido = sc.nextLine().trim();
        if (apellido.isEmpty()) throw new Exception("El apellido no puede estar vacío.");

        System.out.print("Ingrese email: ");
        String email = sc.nextLine().trim();
        if (email.isEmpty() || !email.contains("@")) throw new Exception("Debe ingresar un email válido (con '@').");

        System.out.println("Tipo de usuario:");
        System.out.println("1 - Alumno");
        System.out.println("2 - Bibliotecario");

        if (!sc.hasNextInt()) {
            sc.nextLine(); // limpiar entrada incorrecta
            throw new Exception("Debe ingresar un número (1 o 2).");
        }
        int tipo = sc.nextInt();
        sc.nextLine();

        if (tipo == 1) {
            System.out.print("Ingrese curso: ");
            String curso = sc.nextLine().trim();
            if (curso.isEmpty()) throw new Exception("El curso no puede estar vacío.");

            System.out.print("Ingrese número de libreta: ");
            if (!sc.hasNextInt()) {
                sc.nextLine(); // limpiar entrada incorrecta
                throw new Exception("El número de libreta debe ser un valor numérico.");
            }
            int nroLibreta = sc.nextInt();
            sc.nextLine();

            return new Alumno(id, nombre, apellido, email, curso, nroLibreta);

        } else if (tipo == 2) {
            System.out.print("Ingrese legajo: ");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new Exception("El legajo debe ser un número entero.");
            }
            int legajo = sc.nextInt();
            sc.nextLine();

            return new Bibliotecario(id, nombre, apellido, email, legajo);

        } else {
            throw new Exception("Tipo de usuario inválido. Debe ser 1 (Alumno) o 2 (Bibliotecario).");
        }
    }*/
}

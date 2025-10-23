package ar.edu.unju.escmi.tp6.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import ar.edu.unju.escmi.tp6.dominio.*;

public class CollectionUsuario {
    public static Map<Integer, Usuario> usuarios = new HashMap<>();
    private static int ultimoId = 0; // contador interno de IDs

    //  Generar ID automático
    public static int generarId() {
        ultimoId++;
        return ultimoId;
    }

    //  Registrar usuario (asigna ID automáticamente si no tiene)
    public static void registrarUsuario(Usuario usuario) {
        if (usuario.getId() == 0) {
            usuario.setId(generarId());
        }
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
    public static Usuario crearUsuarioDesdeConsola(Scanner sc) throws Exception {
        System.out.println("\n--- Registro de Usuario ---");

        int id = generarId(); //  ID autogenerado
        System.out.println("(ID asignado automáticamente: " + id + ")");

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

        if (tipo == 1) {
            System.out.print("Ingrese curso: ");
            String curso = sc.nextLine();
            System.out.print("Ingrese número de libreta: ");
            int nroLibreta = sc.nextInt();
            sc.nextLine();
            return new Alumno(id, nombre, apellido, email, curso, nroLibreta);
        } else if (tipo == 2) {
            System.out.print("Ingrese legajo: ");
            int legajo = sc.nextInt();
            sc.nextLine();
            return new Bibliotecario(id, nombre, apellido, email, legajo);
        } else {
            throw new Exception("Tipo de usuario inválido");
        }
    }
}

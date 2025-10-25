package ar.edu.unju.escmi.tp6.collections;

import java.util.HashMap;
import java.util.Map;
import ar.edu.unju.escmi.tp6.dominio.*;

public class CollectionUsuario {
    public static Map<Integer, Usuario> usuarios = new HashMap<>();

    //  Registrar usuario (asigna ID automáticamente si no tiene)
    public static void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
        System.out.println("ID asignado: "+usuario.getId());
        System.out.println("Usuario registrado correctamente.");
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
}

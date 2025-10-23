package ar.edu.unju.escmi.tp6.collections;

import java.util.HashMap;
import java.util.Map;
import ar.edu.unju.escmi.tp6.dominio.Usuario;

public class CollectionUsuario {
    public static Map<Integer, Usuario> usuarios = new HashMap<>();

    public static void registrar(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    public static Usuario buscarPorId(int id) {
        return usuarios.get(id);
    }

    public static void listar() {
        if (usuarios.isEmpty()) {
            System.out.println("✖ No hay usuarios registrados.");
        } else {
            usuarios.values().forEach(Usuario::mostrarDatos);
        }
    }
}


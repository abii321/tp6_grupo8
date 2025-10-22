package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;
import ar.edu.unju.escmi.tp6.dominio.Usuario;

public class CollectionUsuario {
    public static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void agregar(Usuario u) {
        usuarios.add(u);
    }

    public static Usuario buscarPorID(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) return u;
        }
        return null;
    }
}

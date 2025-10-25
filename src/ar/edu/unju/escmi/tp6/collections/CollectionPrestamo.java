package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;

import ar.edu.unju.escmi.tp6.dominio.Prestamo;
import ar.edu.unju.escmi.tp6.dominio.Libro;
import ar.edu.unju.escmi.tp6.dominio.Usuario;

public class CollectionPrestamo {
    public static ArrayList<Prestamo> prestamos = new ArrayList<>();

    public static void altaPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    public static Prestamo buscarLibro(Usuario usuario, Libro libro){
        for(int i=0; i<prestamos.size(); i++){
            Prestamo p = prestamos.get(i);
            if(p.getLibro().getId() == libro.getId() && p.getUsuario().getId()==usuario.getId()){
                return prestamos.get(i);
            }
        }
        return null;
    }
      
}

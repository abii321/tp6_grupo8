package ar.edu.unju.escmi.tp6.collections;

import java.time.LocalDate;
import java.util.ArrayList;

import ar.edu.unju.escmi.tp6.dominio.Prestamo;
import ar.edu.unju.escmi.tp6.dominio.Libro;
import ar.edu.unju.escmi.tp6.utils.FechaUtil;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoDisponibleException;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoEncontradoException;

public class CollectionPrestamo {
    public static ArrayList<Prestamo> prestamos = new ArrayList<>();

    public static void altaPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
        System.out.println(" Préstamo registrado correctamente en la colección.");
    }

    public static Prestamo buscarLibro(Libro libro){
        for(int i=0; i<prestamos.size(); i++){
            if(prestamos.get(i).getLibro().getId() == libro.getId()){
                return prestamos.get(i);
            }
        }
        return null;
    }
      
}

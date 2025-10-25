package ar.edu.unju.escmi.tp6.dominio;

import java.time.LocalDate;
import ar.edu.unju.escmi.tp6.collections.CollectionPrestamo;

public class Bibliotecario extends Usuario {
    private int legajo;

    public Bibliotecario(String nombre, String apellido, String email, int legajo) {
        super(nombre, apellido, email);
        this.legajo = legajo;
    }
    
    public static void recepcionarLibro(Libro libro, LocalDate fecha) {
        Prestamo p = CollectionPrestamo.buscarLibro(libro);
        if(p==null){
            System.out.println("El libro nunca se presto");
            return;
        }
        else if( libro.getEstado()==false ) {
            System.out.println("El libro ya se devolvio anteriormente");
            return;
        }
        p.registrarDevolucion(fecha);
        libro.setEstado(true);
        System.out.println("Recepcion realizada correctamente");
    }

    public static void registrarPrestamo(Prestamo p){
        CollectionPrestamo.altaPrestamo(p);
        System.out.println("ID del prestamo: "+p.getId());
        System.out.println("Prestamo realizado correctamente");
    }
    
    @Override
    public void mostrarDatos() {
        System.out.println("=======================================");
        System.out.println("ID: " + id);
        System.out.println("Nombre Completo: " + nombre + " " + apellido);
        System.out.println("Email: " + email);
        System.out.println("Legajo: " + legajo);
    }

    public static void realizarPrestamo(Prestamo prestamo) {
        CollectionPrestamo.altaPrestamo(prestamo);
        System.out.println("ID del prestamo:"+prestamo.getId());
        System.out.println("Prestamo realizado correctamente");
    }
}

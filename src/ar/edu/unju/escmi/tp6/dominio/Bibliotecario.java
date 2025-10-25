package ar.edu.unju.escmi.tp6.dominio;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.escmi.tp6.collections.CollectionLibro;
import ar.edu.unju.escmi.tp6.collections.CollectionUsuario;
import ar.edu.unju.escmi.tp6.collections.CollectionPrestamo;
import ar.edu.unju.escmi.tp6.utils.FechaUtil;
import java.time.LocalDate;

public class Bibliotecario extends Usuario {
    private int legajo;

    public Bibliotecario(String nombre, String apellido, String email, int legajo) {
        super(nombre, apellido, email);
        this.legajo = legajo;
    }

    public void registrarLibro(Scanner sc) {
        try {
            System.out.println("\n--- Registro de Libro ---");
            System.out.print("Ingrese título: "); String titulo = sc.nextLine();
            if (titulo.isEmpty()) throw new Exception("El título no puede estar vacío.");

            System.out.print("Ingrese autor: "); String autor = sc.nextLine();
            if (autor.isEmpty()) throw new Exception("El autor no puede estar vacío.");

            System.out.print("Ingrese ISBN (número): "); long isbn = sc.nextLong(); sc.nextLine();
        
            Libro libro = new Libro(titulo, autor, isbn);
            CollectionLibro.altaLibro(libro);
            System.out.println("ID asignado: "+libro.getId());

            System.out.println("✅ Libro registrado correctamente por el bibliotecario.");

        } catch (InputMismatchException e) {
            System.out.println("⚠️ Error: el ISBN debe ser un número válido.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("⚠️ Error al registrar el libro: " + e.getMessage());
        }
    }
    
    public static void recepcionarLibro(Libro libro, String fecha) {
        Prestamo p = CollectionPrestamo.buscarLibro(libro);
        if(p==null){
            System.out.println("El libro nunca se presto");
            return;
        }
        p.registrarDevolucion(fecha);
        libro.setEstado(true);
        System.out.println("Recepcion realizada correctamente");
    }
    
    @Override
    public void mostrarDatos() {
        System.out.println("=======================================");
        System.out.println("ID: " + id);
        System.out.println("Nombre Completo: " + nombre + " " + apellido);
        System.out.println("Email: " + email);
        System.out.println("Legajo: " + legajo);
    }

    private static void realizarPrestamo(Scanner sc) {
        try {
            System.out.println("\n--- Registrar Préstamo ---");
            System.out.print("Ingrese ID del libro: ");
            int idLibro = sc.nextInt(); sc.nextLine();
            Libro libro = CollectionLibro.buscarPorId(idLibro);
            if (libro == null) {
                System.out.println(" No existe un libro con ese ID.");
                return;
            }

            System.out.print("Ingrese ID del usuario: ");
            int idUsuario = sc.nextInt(); sc.nextLine();
            Usuario usuario = CollectionUsuario.buscarPorId(idUsuario);
            if (usuario == null) {
                System.out.println(" No existe un usuario con ese ID.");
                return;
            }

            System.out.print("Ingrese la fecha del préstamo (dd/MM/yyyy): ");
            String fechaStr = sc.nextLine();
            LocalDate fechaPrestamo = FechaUtil.convertirStringLocalDate(fechaStr);


        } catch (Exception e) {
            System.out.println(" Error al registrar préstamo: " + e.getMessage());
        }
    }
}

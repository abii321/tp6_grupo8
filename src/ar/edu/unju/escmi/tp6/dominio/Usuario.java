package ar.edu.unju.escmi.tp6.dominio;

import ar.edu.unju.escmi.tp6.collections.CollectionLibro;

public abstract class Usuario {
    protected static int contador = 1; // ID automático
    protected int id;
    protected String nombre;
    protected String apellido;
    protected String email;

    public Usuario(String nombre, String apellido, String email) {
        this.id = contador++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public int getId() { return id; }

    public abstract void mostrarDatos();

    public static void consultarLibrosDisponibles() {
        System.out.println("\n--- Libros disponibles ---");
        if (CollectionLibro.libros.isEmpty()) {
            System.out.println("No hay libros cargados.");
        } else {
            CollectionLibro.libros.values().stream()
                .filter(Libro::getEstado)
                .forEach(Libro::mostrarDetalles);
        }
    }
}

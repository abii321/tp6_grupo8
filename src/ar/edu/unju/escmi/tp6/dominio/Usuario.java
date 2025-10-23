package ar.edu.unju.escmi.tp6.dominio;

import ar.edu.unju.escmi.tp6.collections.CollectionLibro;

public class Usuario {
    protected int id;
    protected String nombre;
    protected String apellido;
    protected String email;

    public Usuario(int id, String nombre, String apellido, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEmail() { return email; }

    public void mostrarDatos() {
        System.out.println("=======================================");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre + " " + apellido);
        System.out.println("Email: " + email);
    }

    public void consultarLibrosDisponibles() {
        System.out.println("\n--- Libros disponibles ---");
        if (CollectionLibro.libros.isEmpty()) {
            System.out.println("No hay libros cargados.");
        } else {
            CollectionLibro.libros.values().stream()
                .filter(Libro::isEstado)
                .forEach(Libro::mostrarDatos);
        }
    }
}

package ar.edu.unju.escmi.tp6.dominio;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String email;

    public Usuario(int id, String nombre, String apellido, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    // ✅ Método usado por CollectionUsuario.listar()
    public void mostrarDatos() {
        System.out.println("=======================================");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre + " " + apellido);
        System.out.println("Email: " + email);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " " + apellido + " - " + email;
    }
}

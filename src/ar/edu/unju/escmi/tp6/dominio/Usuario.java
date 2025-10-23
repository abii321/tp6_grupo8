package ar.edu.unju.escmi.tp6.dominio;

public abstract class Usuario {
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

    // metodo abstracto, se implementa en Alumno y Bibliotecario
    public abstract void mostrarDatos();
}

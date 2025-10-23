package ar.edu.unju.escmi.tp6.dominio;

public class Alumno extends Usuario {
    private String curso;
    private int nroLibreta;

    public Alumno(int id, String nombre, String apellido, String email, String curso, int nroLibreta) {
        super(id, nombre, apellido, email);
        this.curso = curso;
        this.nroLibreta = nroLibreta;
    }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    public int getNroLibreta() { return nroLibreta; }

    @Override
    public void mostrarDatos() {
        System.out.println("=== Alumno ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre + " " + apellido);
        System.out.println("Email: " + email);
        System.out.println("Curso: " + curso);
        System.out.println("N° de libreta: " + nroLibreta);
    }
}

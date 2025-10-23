package ar.edu.unju.escmi.tp6.dominio;

public class Alumno extends Usuario {
    private int curso;
    private int nroLibreta;

    public Alumno(int id, String nombre, String apellido, String email, int curso, int nroLibreta) {
        super(id, nombre, apellido, email);
        this.curso = curso;
        this.nroLibreta = nroLibreta;
    }

    public int getCurso() {
        return curso;
    }

    public int getNroLibreta() {
        return nroLibreta;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Curso: " + curso);
        System.out.println("N° de libreta: " + nroLibreta);
    }
}

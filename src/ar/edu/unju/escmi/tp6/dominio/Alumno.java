package ar.edu.unju.escmi.tp6.dominio;

public class Alumno extends Usuario {
    private String curso;
    private int nroLibreta;

    
    public Alumno(int id, String nombre, String apellido, String email, String curso, int nroLibreta) {
        super(id, nombre, apellido, email);
        this.curso = curso;
        this.nroLibreta = nroLibreta;
    }

    public String getCurso() {
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

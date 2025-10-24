package ar.edu.unju.escmi.tp6.dominio;

public class Alumno extends Usuario {
    private String curso;
    private int nroLibreta;
    
    public Alumno(String nombre, String apellido, String email, String curso, int nroLibreta) {
        super(nombre, apellido, email);
        this.curso = curso;
        this.nroLibreta = nroLibreta;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("=======================================");
        System.out.println("ID: " + id);
        System.out.println("Nombre Completo: " + nombre + " " + apellido);
        System.out.println("Email: " + email);
        System.out.println("Curso: "+curso);
        System.out.println("Nro de Libreta: "+nroLibreta);
    }

    /*public String getCurso() {
        return curso;
    }

    public int getNroLibreta() {
        return nroLibreta;
    }*/
    
}

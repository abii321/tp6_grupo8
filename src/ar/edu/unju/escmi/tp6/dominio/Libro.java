package ar.edu.unju.escmi.tp6.dominio;

public class Libro {
    private static int contador = 1; // ID automático
    private int id;
    private String titulo;
    private String autor;
    private long isbn;
    private boolean estado;

    public Libro(String titulo, String autor, long isbn) {
        this.id = contador++;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.estado = true;
    }

    public int getId() { return id; }
    public void setEstado(boolean estado) { this.estado = estado; }
    public boolean getEstado() { return estado; }

    private void mostrarDatos(){
        System.out.println( (estado) ? "Disponible" : "No disponible");
    }

    public void mostrarDetalles() {
        System.out.println("=======================================");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        mostrarDatos();
    }
}

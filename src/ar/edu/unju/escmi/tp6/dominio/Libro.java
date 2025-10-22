package ar.edu.unju.escmi.tp6.dominio;

public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private boolean estado;

    public Libro(int id, String titulo, String autor, String isbn, boolean estado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.estado = estado;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public boolean isEstado() { return estado; }

    public void setEstado(boolean estado) { this.estado = estado; }

    public void mostrarDatos() {
        System.out.println("=======================================");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        System.out.println("Estado: " + (estado ? "Disponible" : "No disponible"));
    }

    @Override
    public String toString() {
        return "[" + id + "] " + titulo + " - " + autor + " (" + (estado ? "Disponible" : "No disponible") + ")";
    }
}

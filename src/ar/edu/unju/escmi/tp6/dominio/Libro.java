package ar.edu.unju.escmi.tp6.dominio;

public class Libro {
    private static int contador = 1; // ID automático
    private int id;
    private String titulo;
    private String autor;
    private long isbn;
    private boolean estado;

    public Libro(String titulo, String autor, long isbn, boolean estado) {
        this.id = contador++;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.estado = estado;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public long getIsbn() { return isbn; }
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

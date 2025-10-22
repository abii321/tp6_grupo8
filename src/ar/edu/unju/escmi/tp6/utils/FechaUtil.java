package ar.edu.unju.escmi.tp6.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FechaUtil {
<<<<<<< HEAD
    public static LocalDate convertirStringLocalDate(String fechaStr) throws Exception {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(fechaStr, formatter);
        } catch (DateTimeParseException e) {
            throw new Exception("Formato de fecha inválido. Use dd/MM/yyyy.");
        }
=======
    public static LocalDate convertirStringLocalDate(String fechaStr) throws DateTimeParseException {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fechaStr, formato);
>>>>>>> origin/main
    }
}

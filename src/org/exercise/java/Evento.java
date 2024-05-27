package org.exercise.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Evento {
    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;

    // Costruttore per inizializzare un evento
    public Evento(String titolo, String data, int postiTotali) throws IllegalArgumentException, DateTimeParseException {
        this.titolo = titolo;
        setData(data);
        if (postiTotali <= 0) {
            throw new IllegalArgumentException("Il numero di posti totali deve essere positivo.");
        }
        this.postiTotali = postiTotali;
        this.postiPrenotati = 0;
    }

    // Getter per il titolo
    public String getTitolo() {
        return titolo;
    }

    // Setter per il titolo
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    // Getter per la data
    public LocalDate getData() {
        return data;
    }

    // Setter per la data con verifica che non sia nel passato
    public void setData(String data) throws DateTimeParseException, IllegalArgumentException {
        LocalDate newData = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (newData.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La data dell'evento non può essere nel passato.");
        }
        this.data = newData;
    }

    // Getter per i posti totali
    public int getPostiTotali() {
        return postiTotali;
    }

    // Getter per i posti prenotati
    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    // Metodo per prenotare posti
    public void prenota(int numeroPosti) throws IllegalArgumentException {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("L'evento è già passato.");
        }
        if (postiPrenotati + numeroPosti > postiTotali) {
            throw new IllegalArgumentException("Non ci sono abbastanza posti disponibili.");
        }
        this.postiPrenotati += numeroPosti;
    }

    // Metodo per disdire posti prenotati
    public void disdici(int numeroPosti) throws IllegalArgumentException {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("L'evento è già passato.");
        }
        if (numeroPosti > postiPrenotati) {
            throw new IllegalArgumentException("Non ci sono abbastanza prenotazioni da disdire.");
        }
        this.postiPrenotati -= numeroPosti;
    }

    // Metodo per rappresentazione testuale dell'evento
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return data.format(formatter) + " - " + titolo;
    }
}
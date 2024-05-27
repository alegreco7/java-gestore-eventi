package org.exercise.java;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Concerto extends Evento {
    private LocalTime ora;
    private BigDecimal prezzo;

    // Costruttore per inizializzare un concerto
    public Concerto(String titolo, String data, int postiTotali, String ora, BigDecimal prezzo)
            throws IllegalArgumentException, DateTimeParseException {
        super(titolo, data, postiTotali);
        setOra(ora);
        setPrezzo(prezzo);
    }

    // Getter per l'ora
    public LocalTime getOra() {
        return ora;
    }

    // Setter per l'ora con parsing e formattazione
    public void setOra(String ora) throws DateTimeParseException {
        this.ora = LocalTime.parse(ora, DateTimeFormatter.ofPattern("HH:mm"));
    }

    // Getter per il prezzo
    public BigDecimal getPrezzo() {
        return prezzo;
    }

    // Setter per il prezzo con controllo che non sia negativo
    public void setPrezzo(BigDecimal prezzo) {
        if (prezzo.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Il prezzo non può essere negativo.");
        }
        this.prezzo = prezzo;
    }

    // Metodo per ottenere la data e l'ora formattate
    public String getDataOraFormattata() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return getData().format(dateFormatter) + " " + ora.format(timeFormatter);
    }

    // Metodo per ottenere il prezzo formattato
    public String getPrezzoFormattato() {
        return String.format("%.2f€", prezzo);
    }

    // Metodo per rappresentazione testuale del concerto
    @Override
    public String toString() {
        return getDataOraFormattata() + " - " + getTitolo() + " - " + getPrezzoFormattato();
    }
}

package org.exercise.java;

import java.math.BigDecimal;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creazione dell'evento
        Concerto concerto = null;
        while (concerto == null) {
            try {
                System.out.println("Inserisci il titolo del concerto:");
                String titolo = scanner.nextLine();

                System.out.println("Inserisci la data del concerto (formato yyyy-mm-dd):");
                String data = scanner.nextLine();

                System.out.println("Inserisci il numero di posti totali:");
                int postiTotali = Integer.parseInt(scanner.nextLine());

                System.out.println("Inserisci l'ora del concerto (formato HH:mm):");
                String ora = scanner.nextLine();

                System.out.println("Inserisci il prezzo del biglietto:");
                BigDecimal prezzo = new BigDecimal(scanner.nextLine());

                // Crea un nuovo oggetto Concerto
                concerto = new Concerto(titolo, data, postiTotali, ora, prezzo);
            } catch (IllegalArgumentException | DateTimeParseException e) {
                System.out.println("Errore: " + e.getMessage());
                System.out.println("Per favore riprova.");
            }
        }

        // Prenotazione dei posti
        System.out.println("Vuoi fare delle prenotazioni? (sì/no)");
        String rispostaPrenota = scanner.nextLine();

        if (rispostaPrenota.equalsIgnoreCase("sì")) {
            try {
                System.out.println("Quanti posti vuoi prenotare?");
                int postiDaPrenotare = Integer.parseInt(scanner.nextLine());
                concerto.prenota(postiDaPrenotare);
                System.out.println("Prenotazione effettuata.");
            } catch (IllegalArgumentException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

        // Visualizzazione posti prenotati e disponibili
        System.out.println("Posti prenotati: " + concerto.getPostiPrenotati());
        System.out.println("Posti disponibili: " + (concerto.getPostiTotali() - concerto.getPostiPrenotati()));

        // Disdetta dei posti
        System.out.println("Vuoi disdire delle prenotazioni? (sì/no)");
        String rispostaDisdici = scanner.nextLine();

        if (rispostaDisdici.equalsIgnoreCase("sì")) {
            try {
                System.out.println("Quanti posti vuoi disdire?");
                int postiDaDisdire = Integer.parseInt(scanner.nextLine());
                concerto.disdici(postiDaDisdire);
                System.out.println("Disdetta effettuata.");
            } catch (IllegalArgumentException e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

        // Visualizzazione finale posti prenotati e disponibili
        System.out.println("Posti prenotati: " + concerto.getPostiPrenotati());
        System.out.println("Posti disponibili: " + (concerto.getPostiTotali() - concerto.getPostiPrenotati()));

        scanner.close();
    }
}
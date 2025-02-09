package com.myself.esame.s5.repository;

import com.myself.esame.s5.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class DataStore {
    private final Map<String, Utente> utenti = new ConcurrentHashMap<>();
    private final Map<String, Postazione> postazioni = new ConcurrentHashMap<>();
    private final Map<Long, Edificio> edifici = new ConcurrentHashMap<>();
    private final Map<Long, Prenotazione> prenotazioni = new ConcurrentHashMap<>();
    private final AtomicLong prenotazioniIdGenerator = new AtomicLong();
    private final AtomicLong edificiIdGenerator = new AtomicLong();

    public Utente salvaUtente(Utente utente) {
        utenti.put(utente.getUsername(), utente);
        return utente;
    }

    public Optional<Utente> trovaUtente(String username) {
        return Optional.ofNullable(utenti.get(username));
    }

    public Postazione salvaPostazione(Postazione postazione) {
        postazioni.put(postazione.getCodice(), postazione);
        return postazione;
    }

    public Optional<Postazione> trovaPostazione(String codice) {
        return Optional.ofNullable(postazioni.get(codice));
    }

    public List<Postazione> cercaPostazioni(TipoPostazione tipo, String citta) {
        return postazioni.values().stream()
                .filter(p -> p.getTipo() == tipo && p.getEdificio().getCitta().equals(citta))
                .collect(Collectors.toList());
    }

    public Edificio salvaEdificio(Edificio edificio) {
        if (edificio.getId() == null) {
            edificio.setId(edificiIdGenerator.incrementAndGet());
        }
        edifici.put(edificio.getId(), edificio);
        return edificio;
    }

    public Prenotazione salvaPrenotazione(Prenotazione prenotazione) {
        if (prenotazione.getId() == null) {
            prenotazione.setId(prenotazioniIdGenerator.incrementAndGet());
        }
        prenotazioni.put(prenotazione.getId(), prenotazione);
        return prenotazione;
    }

    public boolean esistePrenotazionePostazione(String codicePostazione, LocalDate data) {
        return prenotazioni.values().stream()
                .anyMatch(p -> p.getPostazione().getCodice().equals(codicePostazione) &&
                        p.getData().equals(data));
    }

    public boolean esistePrenotazioneUtente(String username, LocalDate data) {
        return prenotazioni.values().stream()
                .anyMatch(p -> p.getUtente().getUsername().equals(username) &&
                        p.getData().equals(data));
    }

    public List<Prenotazione> trovaPrenotazioniUtente(String username) {
        return prenotazioni.values().stream()
                .filter(p -> p.getUtente().getUsername().equals(username))
                .collect(Collectors.toList());
    }
}

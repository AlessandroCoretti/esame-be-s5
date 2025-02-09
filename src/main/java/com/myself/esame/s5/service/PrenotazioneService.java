package com.myself.esame.s5.service;

import com.myself.esame.s5.model.*;
import com.myself.esame.s5.repository.DataStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {
    private final DataStore dataStore;

    public Prenotazione effettuaPrenotazione(String username, String codicePostazione, LocalDate data) {
        var utente = dataStore.trovaUtente(username)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        var postazione = dataStore.trovaPostazione(codicePostazione)
                .orElseThrow(() -> new IllegalArgumentException("Postazione non trovata"));

        if (dataStore.esistePrenotazioneUtente(username, data)) {
            throw new IllegalStateException("Utente ha già una prenotazione per questa data");
        }

        if (dataStore.esistePrenotazionePostazione(codicePostazione, data)) {
            throw new IllegalStateException("Postazione già prenotata per questa data");
        }

        var prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setPostazione(postazione);
        prenotazione.setData(data);

        return dataStore.salvaPrenotazione(prenotazione);
    }

    public List<Prenotazione> getPrenotazioniUtente(String username) {
        return dataStore.trovaPrenotazioniUtente(username);
    }
}

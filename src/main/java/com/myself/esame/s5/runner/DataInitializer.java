package com.myself.esame.s5.runner;

import com.myself.esame.s5.model.*;
import com.myself.esame.s5.repository.DataStore;
import com.myself.esame.s5.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final PostazioneService postazioneService;
    private final PrenotazioneService prenotazioneService;
    private final DataStore dataStore;

    @Override
    public void run(String... args) {
        // Crea e salva un edificio
        Edificio edificio = new Edificio();
        edificio.setNome("Sede Principale");
        edificio.setIndirizzo("Via Roma 1");
        edificio.setCitta("Milano");
        edificio = dataStore.salvaEdificio(edificio);

        // Crea e salva una postazione
        Postazione postazione = new Postazione();
        postazione.setCodice("P001");
        postazione.setDescrizione("Postazione Open Space Piano 1");
        postazione.setTipo(TipoPostazione.OPENSPACE);
        postazione.setNumeroMassimoOccupanti(4);
        postazione.setEdificio(edificio);
        postazioneService.salvaPostazione(postazione);

        // Crea e salva un utente
        Utente utente = new Utente();
        utente.setUsername("mario.rossi");
        utente.setNomeCompleto("Mario Rossi");
        utente.setEmail("mario.rossi@example.com");
        dataStore.salvaUtente(utente);

        System.out.println("Dati inizializzati con successo!");
    }
}

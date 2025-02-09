package com.myself.esame.s5.service;

import com.myself.esame.s5.model.*;
import com.myself.esame.s5.repository.DataStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostazioneService {
    private final DataStore dataStore;

    public List<Postazione> cercaPostazioni(TipoPostazione tipo, String citta) {
        return dataStore.cercaPostazioni(tipo, citta);
    }

    public Postazione salvaPostazione(Postazione postazione) {
        return dataStore.salvaPostazione(postazione);
    }
}

package com.myself.esame.s5.model;

import lombok.Data;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Prenotazione {
    private Long id;
    private Utente utente;
    private Postazione postazione;
    private LocalDate data;
}

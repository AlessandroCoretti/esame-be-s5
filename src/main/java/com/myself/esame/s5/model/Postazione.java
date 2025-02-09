package com.myself.esame.s5.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Postazione {
    private String codice;
    private String descrizione;
    private TipoPostazione tipo;
    private Integer numeroMassimoOccupanti;
    private Edificio edificio;
}

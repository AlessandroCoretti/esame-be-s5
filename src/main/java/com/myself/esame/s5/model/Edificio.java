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
public class Edificio {
    private Long id;
    private String nome;
    private String indirizzo;
    private String citta;
}

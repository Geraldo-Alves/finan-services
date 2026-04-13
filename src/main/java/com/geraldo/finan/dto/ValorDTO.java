package com.geraldo.finan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValorDTO {

    private String nome;
    private String descricao;
    private Double valor;
    private Integer categoria;
    private Integer mes;
    private Integer ano;

}

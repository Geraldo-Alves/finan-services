package com.geraldo.finan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrcamentoDTO {

    public Double entradas;
    public Double saidas;
    public Double balanco;
    public Integer mes;
    public Integer ano;

}

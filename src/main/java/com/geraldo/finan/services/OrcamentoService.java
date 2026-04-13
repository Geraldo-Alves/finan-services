package com.geraldo.finan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geraldo.finan.dto.OrcamentoDTO;
import com.geraldo.finan.entities.Valor;
import com.geraldo.finan.repositories.ValorRepository;

@Service
public class OrcamentoService {

    @Autowired
    ValorRepository valorRepository;

    public OrcamentoDTO obterOrcamento(Integer mes, Integer ano) {

        if (mes == null || ano == null) {
            mes = java.time.LocalDate.now().getMonthValue();
            ano = java.time.LocalDate.now().getYear();
        }

        OrcamentoDTO orcamento = valorRepository.findOrcamentoByMesAndAno(mes, ano);

        return orcamento;
    }

}

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
        // Lógica para calcular o orçamento com base nos valores do mês e ano
        // Você pode usar o ValorRepository para buscar os valores e calcular as entradas, saídas e balanço

        Double entradas = 0.0;
        Double saidas = 0.0;
        Double balanco = 0.0;

        List<Valor> valores = valorRepository.findByMesAndAno(mes, ano);

        for (Valor valor : valores) {
            String tipo = valor.getCategoria().getTipo();
            if (tipo.equals("E")) {
                entradas += valor.getValor();
            } else if (tipo.equals("S")) {
                saidas += valor.getValor();
            }
        }

        balanco = entradas - saidas;

        OrcamentoDTO orcamento = new OrcamentoDTO();
        orcamento.setMes(mes);
        orcamento.setAno(ano);
        orcamento.setEntradas(entradas);
        orcamento.setSaidas(saidas);
        orcamento.setBalanco(balanco);

        return orcamento;
    }

}

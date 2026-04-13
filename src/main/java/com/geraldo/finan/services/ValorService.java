package com.geraldo.finan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geraldo.finan.dto.ValorDTO;
import com.geraldo.finan.entities.Categoria;
import com.geraldo.finan.entities.Valor;
import com.geraldo.finan.repositories.CategoriaRepository;
import com.geraldo.finan.repositories.ValorRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class ValorService {

    @Autowired
    ValorRepository valorRepository;
    @Autowired
    CategoriaRepository categoriaRepository;

    @Transactional
    public Valor criarValor(ValorDTO dto) throws RuntimeException {
        
        // Lógica para criar um valor
        Valor valor = new Valor();
        valor.setNome(dto.getNome());
        valor.setDescricao(dto.getDescricao());
        valor.setValor(dto.getValor());
        valor.setMes(dto.getMes());
        valor.setAno(dto.getAno());

        Categoria categoria = categoriaRepository.findById(dto.getCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        valor.setCategoria(categoria);
        return valorRepository.save(valor);

    }

    @Transactional
    public Boolean duplicarOrcamento(Integer mes, Integer ano) throws RuntimeException {
        Integer mesAnterior = mes == 1 ? 12 : mes - 1;
        Integer anoAnterior = mes == 1 ? ano - 1 : ano;

        List<Valor> valoresAnteriores = valorRepository.findByMesAndAno(mesAnterior, anoAnterior);

        List<Valor> fixos = valoresAnteriores.stream()
                .filter(v -> v.getCategoria() != null && Boolean.TRUE.equals(v.getCategoria().getIsFixo()))
                .toList();

        if (fixos.isEmpty()) {
            return false;
        }

        List<Valor> duplicados = fixos.stream().map(v -> {
            Valor novo = new Valor();
            novo.setNome(v.getNome());
            novo.setDescricao(v.getDescricao());
            novo.setValor(v.getValor());
            novo.setParcela(v.getParcela());
            novo.setQtdParcelas(v.getQtdParcelas());
            novo.setCategoria(v.getCategoria());
            novo.setMes(mes);
            novo.setAno(ano);
            novo.setStatus(v.getStatus());
            return novo;
        }).toList();

        valorRepository.saveAll(duplicados);
        return true;
    }

    public List<Valor> listarEntradas(Integer mes, Integer ano) {
        if (mes == null || ano == null) {
            mes = java.time.LocalDate.now().getMonthValue();
            ano = java.time.LocalDate.now().getYear();
        }
        return valorRepository.findByMesAndAnoAndTipo(mes, ano, "E");
    }

    public List<Valor> listarSaidas(Integer mes, Integer ano) {
        if (mes == null || ano == null) {
            mes = java.time.LocalDate.now().getMonthValue();
            ano = java.time.LocalDate.now().getYear();
        }
        return valorRepository.findByMesAndAnoAndTipo(mes, ano, "S");
    }

}

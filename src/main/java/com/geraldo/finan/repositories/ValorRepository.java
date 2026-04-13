package com.geraldo.finan.repositories;

import com.geraldo.finan.dto.OrcamentoDTO;
import com.geraldo.finan.entities.Valor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ValorRepository extends JpaRepository<Valor, Integer> {

    List<Valor> findByMesAndAno(Integer mes, Integer ano);

    default List<Valor> findByMesAndAnoAndTipo(Integer mes, Integer ano, String tipo) {
        return findByMesAndAno(mes, ano).stream()
                .filter(v -> v.getCategoria() != null && v.getCategoria().getTipo().equals(tipo))
                .toList();
    };

    @Query(value = """
            SELECT
                COALESCE(SUM(CASE WHEN c.tipo = 'E' THEN v.valor ELSE 0 END), 0) AS entradas,
                COALESCE(SUM(CASE WHEN c.tipo = 'S' THEN v.valor ELSE 0 END), 0) AS saidas,
                COALESCE(SUM(CASE WHEN c.tipo = 'E' THEN v.valor ELSE 0 END), 0)
                    - COALESCE(SUM(CASE WHEN c.tipo = 'S' THEN v.valor ELSE 0 END), 0) AS balanco,
                :mes AS mes,
                :ano AS ano
            FROM valor v
            INNER JOIN categoria c ON c.id = v.ref_id_categoria
            WHERE v.mes = :mes AND v.ano = :ano
            """, nativeQuery = true)
    Object[] sumOrcamentoByMesAndAno(@Param("mes") Integer mes, @Param("ano") Integer ano);

    default OrcamentoDTO findOrcamentoByMesAndAno(Integer mes, Integer ano) {
        Object[] row = sumOrcamentoByMesAndAno(mes, ano);
        Object[] data = (row.length == 1 && row[0] instanceof Object[]) ? (Object[]) row[0] : row;

        Double entradas = ((Number) data[0]).doubleValue();
        Double saidas = ((Number) data[1]).doubleValue();
        Double balanco = ((Number) data[2]).doubleValue();

        return new OrcamentoDTO(entradas, saidas, balanco, mes, ano);
    };

}

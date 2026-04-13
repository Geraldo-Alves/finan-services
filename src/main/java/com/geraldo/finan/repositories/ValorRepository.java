package com.geraldo.finan.repositories;

import com.geraldo.finan.entities.Valor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValorRepository extends JpaRepository<Valor, Integer> {

    List<Valor> findByMesAndAno(Integer mes, Integer ano);
}

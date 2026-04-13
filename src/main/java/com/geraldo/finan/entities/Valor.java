package com.geraldo.finan.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "valor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Valor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String nome;

    @Column(length = 100)
    private String descricao;

    private Double valor;

    private Integer parcela;

    @Column(name = "qtd_parcelas")
    private Integer qtdParcelas;

    @ManyToOne
    @JoinColumn(name = "ref_id_categoria")
    private Categoria categoria;

    private Integer mes;

    private Integer ano;

    @Column(length = 1)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}

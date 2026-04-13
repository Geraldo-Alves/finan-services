package com.geraldo.finan.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String nome;

    @Column(name = "is_parcelavel")
    private Boolean isParcelavel;

    @Column(name = "is_fixo")
    private Boolean isFixo;

    @Column(length = 1)
    private String tipo;

}

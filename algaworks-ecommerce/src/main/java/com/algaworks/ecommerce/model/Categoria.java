package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "categoria")
public class Categoria {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "sequencia_chave_primaria", allocationSize = 50)
    private Integer id;
    private String nome;
    @Column(name = "categoria_pai_id")
    private Integer categoriaPaiId;

}

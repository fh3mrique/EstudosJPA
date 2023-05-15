package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


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
    private Integer id;
    private String nome;
    @Column(name = "categoria_pai_id")
    private Integer categoriaPaiId;

}

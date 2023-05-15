package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private Integer categoriaPaiId;

}

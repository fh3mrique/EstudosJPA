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
@Table(name = "estoque")
public class Estoque {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "produto_id")
    private Integer produtoId;
    private Integer quantidade;

}

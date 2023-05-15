package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "cliente")
public class Cliente {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private Integer nome;
    @Column(name = "sexo_cliente")
    private SexoCliente sexo;

}

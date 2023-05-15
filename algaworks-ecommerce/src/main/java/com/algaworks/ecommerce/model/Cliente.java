package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.*;
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
    private String nome;
    @Column(name = "sexo_cliente")
    @Enumerated(EnumType.STRING)
    /*A anotação @Enumerated(EnumType.STRING) é usada em classes JPA para mapear
    campos de enumeração (enum) para o banco de dados. Ela especifica se o enum
    deve ser mapeado como uma string ou um número inteiro. Quando usado com
    EnumType.STRING, a anotação mapeia o valor do enum como uma string, em vez de
    um número inteiro.*/
    private SexoCliente sexo;

}

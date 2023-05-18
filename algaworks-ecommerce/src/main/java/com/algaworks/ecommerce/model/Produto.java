package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    /*um Produto tem MUITAS Categorias e uma Categoria tem MUITOS Produtos */
    @ManyToMany
    @JoinTable(
            //nome da tabela intermédiaria
            name = "produto_categoria",
            // especifica a coluna na tabela intermediária que está associada à entidade
            // atual (no caso, Produto).
            joinColumns = @JoinColumn(name = "produto_id"),
            //explique essa linha
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

}
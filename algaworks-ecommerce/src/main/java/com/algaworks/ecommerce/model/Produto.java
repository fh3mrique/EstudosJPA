package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;
import com.algaworks.ecommerce.listener.ListenerGenerico;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
@EntityListeners({ListenerGenerico.class})
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    /*A propriedade updatable = false na anotação @Column indica que o campo
    correspondente no banco de dados não pode ser atualizado. Isso significa que,
    uma vez que o valor é definido, ele não pode ser modificado posteriormente*/
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

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
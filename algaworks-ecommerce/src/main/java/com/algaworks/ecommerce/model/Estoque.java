package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.*;


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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    /*Quando optional é definido como false, o JPA garante que a associação seja
     sempre estabelecida com um objeto Produto. Se uma tentativa de persistir
     ou atualizar uma instância dessa entidade ocorrer sem que um objeto Produto
     seja atribuído, uma exceção será lançada pelo JPA.*/
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

}

package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "produto_id")
    private Integer produtoId;
    @Column(name = "pedido_id")
    private Integer pedidoId;
    @Column(name = "preco_produto")
    private BigDecimal precoProduto;
    private Integer quantidade;

}

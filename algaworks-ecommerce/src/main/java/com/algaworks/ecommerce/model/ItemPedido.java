package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "item_pedido")
@IdClass(ItemPedidoId.class)
public class ItemPedido{

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "pedido_id")
    private Integer pedidoId;

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "produto_id")
    private Integer produtoId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", insertable = false, updatable = false)
    private Produto produto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
    private Pedido pedido;

    @Column(name = "preco_produto")
    private BigDecimal precoProduto;

    private Integer quantidade;

//    private Double preçoTotal;

//    public double calcularPreçoTotal(Produto produto){
//        double preçoTotal = this.quantidade *  produto.getPreco().doubleValue();
//        return preçoTotal;
//    }

}

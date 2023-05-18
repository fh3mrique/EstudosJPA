package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.algaworks.ecommerce.model.Cliente;



//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //MUITOS pedido PARA UM cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamentoCartao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @Column(name = "nota_fiscal_id")
    private Integer notaFiscalId;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private BigDecimal total;

    @Embedded
    /*A anotação @Embedded é usada em atributos de uma classe JPA para indicar que o
    atributo é um objeto embutido em outra entidade JPA. Isso significa que os campos
    do objeto embutido são mapeados como colunas da tabela da entidade que contém o
    atributo @Embedded.*/
    private EnderecoEntregaPedido endereco;

}

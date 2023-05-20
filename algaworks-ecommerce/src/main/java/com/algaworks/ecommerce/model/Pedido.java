package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;
import com.algaworks.ecommerce.listener.ListenerGenerico;
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
@EntityListeners({GerarNotaFiscalListener.class, ListenerGenerico.class})
public class Pedido {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //MUITOS pedido PARA UM cliente
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamentoCartao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao")
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private BigDecimal total;

    @Embedded
    /*A anotação @Embedded é usada em atributos de uma classe JPA para indicar que o
    atributo é um objeto embutido em outra entidade JPA. Isso significa que os campos
    do objeto embutido são mapeados como colunas da tabela da entidade que contém o
    atributo @Embedded.*/
    private EnderecoEntregaPedido enderecoEntrega;

    //MÉTODOS
    public boolean isPago(){
       return StatusPedido.PAGO.equals(status);
    }

    //METODOS DE CALBACKS

    //@PrePersist
    // @PreUpdate
    public void calcularTotal() {
        if (itens != null) {
            total = itens.stream().map(ItemPedido::getPrecoProduto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }
    @PrePersist
    /*O PrePersist é acionado apenas uma vez para cada entidade*/
    public void aoPesistir(){
        dataCriacao = LocalDateTime.now();
        calcularTotal();
    }
    @PreUpdate
    /*Não é acionado na hora de persistir pela primeira vez, mas é acionado sempre
    que fizemos uma operação de update*/
    public void aoAtualizar(){
        dataUltimaAtualizacao = LocalDateTime.now();
        calcularTotal();
    }

    @PostPersist
    public void aposPersistir() {
        System.out.println("Após persistir Pedido.");
    }

    @PostUpdate
    public void aposAtualizar() {
        System.out.println("Após atualizar Pedido.");
    }

    @PreRemove
    public void aoRemover() {
        System.out.println("Antes de remover Pedido.");
    }

    @PostRemove
    public void aposRemover() {
        System.out.println("Após remover Pedido.");
    }

    @PostLoad
    public void aoCarregar() {
        System.out.println("Após carregar o Pedido.");
    }

}

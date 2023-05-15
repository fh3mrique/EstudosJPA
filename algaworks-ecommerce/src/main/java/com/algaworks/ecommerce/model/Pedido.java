package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pedido")
public class Pedido {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;
    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;
    @Column(name = "nota_fiscal_id")
    private Integer notaFiscalId;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private BigDecimal total;

    /*private String cep;
    private String logradouro;
    private String numero;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;*/

    @Embedded
    /*A anotação @Embedded é usada em atributos de uma classe JPA para indicar que o
    atributo é um objeto embutido em outra entidade JPA. Isso significa que os campos
    do objeto embutido são mapeados como colunas da tabela da entidade que contém o
    atributo @Embedded.*/
    private EnderecoEntregaPedido endereco;

}

package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    /*UMA NotaFiscal TEM UM Pedido e UM Pedido TEM UMA NotaFiscal*/
    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    private String xml;

    @Column(name = "data_emissao")
    private Date dataEmissao;

}

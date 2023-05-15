package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private Integer id;
    private String nome;
    @Column(name = "pedido_id")
    private Integer pedidoId;
    private String xml;
    @Column(name = "data_emissao")
    private Date dataEmissao;

}

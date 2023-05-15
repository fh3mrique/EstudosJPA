package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NotaFiscal {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private Integer pedidoId;
    private String xml;
    private Date dataEmissao;

}

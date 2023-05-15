package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
@Getter
@Setter
@Embeddable
/*A anotação @Embeddable é usada para indicar que uma classe Java é um objeto
embutido que será incorporado em outras entidades JPA. Esses objetos embutidos
não possuem identidade própria e seus atributos são mapeados como colunas na
tabela da entidade que contém o objeto embutido.*/
public class EnderecoEntregaPedido {
    private String cep;
    private String logradouro;
    private String numero;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
}

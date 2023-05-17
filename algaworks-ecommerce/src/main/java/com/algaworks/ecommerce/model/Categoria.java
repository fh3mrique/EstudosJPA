package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "categoria")
public class Categoria {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    //AUTORELACIONAMENTOS
    //Uma Categoria pai pode ter varias outra categorias
    //EX: Categoria Eletrônicos -> VideoGames, Smathphones etc
    @ManyToOne
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPai;

    //Uma Categoria pai pode ter varias outra categorias
    //EX: Categoria VideoGames -> Só pode ter uma categoria pai
    @OneToMany(mappedBy = "categoriaPai")
    private List<Categoria> categorias;

}

package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AutoRelacionamentosTest extends EntityManagerFabrica {

    @Test
    public void testarRelacionamento(){

        Categoria categoriaPai = new Categoria();
        categoriaPai.setNome("Eletrônicos");

        Categoria categoriaFilha = new Categoria();
        categoriaFilha.setNome("VideoGames");
        categoriaFilha.setCategoriaPai(categoriaPai);

        entityManager.getTransaction().begin();
            entityManager.persist(categoriaPai);
            entityManager.persist(categoriaFilha);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoriaFilha.getId());
        Categoria categoriaPaiVerificacao = entityManager.find(Categoria.class, categoriaPai.getId());

        Assert.assertNotNull(categoriaVerificacao.getCategoriaPai());
        Assert.assertFalse(categoriaPaiVerificacao.getCategorias().isEmpty());
    }

}

package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DetalhesColumnTest extends EntityManagerFabrica {

    @Test
    public void impedirInsercaoDaColunaAtualizacao(){
        Produto produto = new Produto();

        produto.setNome("Teclado");
        produto.setDescricao("um teclado bom");
        produto.setPreco(BigDecimal.ONE);

        produto.setDataCriacao(LocalDateTime.now());
        //essa operação não vai funcionar pq o campo dataUltimaAtualizacao está
        //com o insertable = false
        produto.setDataUltimaAtualizacao(LocalDateTime.now());

        entityManager.getTransaction().begin();
            entityManager.persist(produto);
        entityManager.getTransaction().commit();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());

        Assert.assertNotNull(produtoVerificacao.getDataCriacao());
        //como não é possivel setar uma valor para DataUltimaAtualizacao o objeto
        //fica com o campo vázio
        Assert.assertNull(produtoVerificacao.getDataUltimaAtualizacao());
    }

    @Test
    public void impedirInsercaoDaColunaCriacao(){
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1);

        produto.setPreco(BigDecimal.TEN);
        produto.setDataUltimaAtualizacao(LocalDateTime.now());
        //a data de ciaçã o banco está um dia antes, estamos forçando para da hoje
        produto.setDataCriacao(LocalDateTime.now());

        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());

        Assert.assertNotEquals(produtoVerificacao.getDataCriacao().truncatedTo(ChronoUnit.SECONDS),
                produtoVerificacao.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));
        Assert.assertEquals(produtoVerificacao.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS),
                produtoVerificacao.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS));
    }
}

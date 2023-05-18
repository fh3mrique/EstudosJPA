package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class RelacionamentosManyToManyTest extends EntityManagerFabrica {

    @Test
    public void testarRelacionamento(){
      Categoria categoria = entityManager.find(Categoria.class, 1);//Eletrônicos
      Produto produto = entityManager.find(Produto.class, 1);//Kindle

      entityManager.getTransaction().begin();
      //Não vai pegar porque Produto é o owner da relação e não Categoria
        //categoria.setProdutos(Arrays.asList(produto));
        produto.setCategorias(Arrays.asList(categoria));
      entityManager.getTransaction().commit();

      entityManager.clear();

      Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());

      Assert.assertFalse(produtoVerificacao.getCategorias().isEmpty());
    }
}

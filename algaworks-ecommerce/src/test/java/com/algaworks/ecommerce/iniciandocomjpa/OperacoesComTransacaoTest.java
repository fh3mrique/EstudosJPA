package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.net.ProxySelector;

public class OperacoesComTransacaoTest extends EntityManagerFabrica {

    @Test
    public void inserirOPrimeiroObjeto(){
        Produto produto = new Produto();

        produto.setId(2);
        produto.setNome("Oyasumi punpun");
        produto.setDescricao("O melhor mánga");
        produto.setPreco(new BigDecimal(50));

        entityManager.getTransaction().begin();
            entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);

    }


    @Test
    public void abrirEFecharATransacao(){
        /*uma transação é uma unidade de trabalho lógica que consiste em uma ou mais operações de banco de
        dados que precisam ser executadas como uma única unidade atômica. As transações são usadas para
        garantir a consistência dos dados em um banco de dados e para evitar problemas como atualizações
        concorrentes ou dados corrompidos. Para iniciar uma transação em JPA, é necessário obter uma instância
        de EntityManager, iniciar a transação usando o método begin(), realizar as operações de banco de
        dados necessárias e, em seguida, finalizar a transação chamando o método commit() para confirmar as
        perações ou o método rollback() para cancelar todas as operações realizadas durante a transação.*/
//        Produto produto = new Produto();

        entityManager.getTransaction().begin();
//            entityManager.persist(produto);
//            entityManager.merge(produto);
//            entityManager.remove(produto);
        entityManager.getTransaction().commit();
    }

}

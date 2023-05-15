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
    public void inserirObjetoComMerge(){
        Produto produto = new Produto();

        produto.setId(4);
        produto.setNome("4° produto");
        produto.setDescricao("produto inserido com merge");
        produto.setPreco(new BigDecimal(50));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
    }

    @Test
    public void mostrarDifencaPersistMerge() {
        Produto produtoPersist = new Produto();

        produtoPersist.setId(5);
        produtoPersist.setNome("Smartphone One Plus");
        produtoPersist.setDescricao("O processador mais rápido.");
        produtoPersist.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        entityManager.persist(produtoPersist);
        produtoPersist.setNome("Smartphone Two Plus");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacaoPersist = entityManager.find(Produto.class, produtoPersist.getId());
        Assert.assertNotNull(produtoVerificacaoPersist);

        Produto produtoMerge = new Produto();

        produtoMerge.setId(6);
        produtoMerge.setNome("Notebook Dell");
        produtoMerge.setDescricao("O melhor da categoria.");
        produtoMerge.setPreco(new BigDecimal(2000));

        entityManager.getTransaction().begin();
        produtoMerge = entityManager.merge(produtoMerge);
        produtoMerge.setNome("Notebook Dell 2");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacaoMerge = entityManager.find(Produto.class, produtoMerge.getId());
        Assert.assertNotNull(produtoVerificacaoMerge);
    }

    @Test
    public void removerObjeto(){
        Produto produto = entityManager.find(Produto.class, 3);

        entityManager.getTransaction().begin();
            entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto produtoVerificacao = entityManager.find(Produto.class, 3);

        Assert.assertNull(produtoVerificacao);
    }

    @Test
    public void atualizarObjeto(){
        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("nome atualizado");
        produto.setDescricao("papinho furado");

        entityManager.getTransaction().begin();
            entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, 1);

        Assert.assertEquals("nome atualizado", produto.getNome());
        Assert.assertNotNull(produtoVerificacao);
    }

    @Test
    public void atualizarObjetoGerenciado(){
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
            produto.setNome("apenas o nome foi atualizado");
        entityManager.getTransaction().commit();

        Produto produtoVerificacao = entityManager.find(Produto.class, 1);

        Assert.assertEquals("apenas o nome foi atualizado", produto.getNome());
        Assert.assertNotNull(produtoVerificacao);
    }

    @Test
    public void impedirOperacaoComBD(){
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.detach(produto);

        entityManager.getTransaction().begin();
        produto.setNome("apenas o nome foi atualizado");
        entityManager.getTransaction().commit();

        Produto produtoVerificacao = entityManager.find(Produto.class, 1);

        Assert.assertEquals("Kindle", produtoVerificacao.getNome());
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

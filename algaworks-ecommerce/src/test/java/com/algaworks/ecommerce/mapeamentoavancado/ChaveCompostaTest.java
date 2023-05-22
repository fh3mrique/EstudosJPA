package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class ChaveCompostaTest extends EntityManagerFabrica {

    @Test
    public void salvarItem(){
        entityManager.getTransaction().begin();
        Produto produto = entityManager.find(Produto.class, 1);//Kindle
        Cliente cliente = entityManager.find(Cliente.class, 1);//Lionel Messi

        //Montando um pedido
        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(produto.getPreco());
        //Pedido depende de um Cliente
        pedido.setCliente(cliente);

        entityManager.persist(pedido);

        entityManager.flush();//jogando o pedido no banco antes de terminar a transação

        //Montando ItemPedido.
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setQuantidade(1);
        itemPedido.setPrecoProduto(produto.getPreco());
        // ItemPedido depende Pedido e Produto
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        //id composto de ItemPedido
        itemPedido.setPedidoId(pedido.getId());
        itemPedido.setProdutoId(produto.getId());

        entityManager.persist(itemPedido);

        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVerificacao);
        Assert.assertFalse(pedidoVerificacao.getItens().isEmpty());
    }

    @Test
    public void buscarItem(){
        ItemPedido itemPedido = entityManager.find(ItemPedido.class, new ItemPedidoId(1, 1));

        Assert.assertNotNull(itemPedido);
    }
}

package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelacionamentosManyToOneTest extends EntityManagerFabrica {

    @Test
    public void testarRelacionamento(){
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();

        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);

        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
            entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVerificacao.getCliente());

    }

    @Test
    public void verificarRelacionamentoItemPedido(){
        Cliente cliente = entityManager.find(Cliente.class, 1); //Lionel messi
        Produto produto = entityManager.find(Produto.class, 1);//nome: Kindle preço: 500

        //ASSOCIEI MESSI A UM PEDIDO
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);
        pedido.setCliente(cliente);

        //ASSOCIANDO UM PRODUTO E UM PEDIDO A UMA COMPRA
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setQuantidade(3);
        itemPedido.setProduto(produto);
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setPedido(pedido);

        entityManager.getTransaction().begin();
            entityManager.persist(pedido);
            entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        //ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());

        //Assert.assertNotNull(itemPedidoVerificacao.getPedido());
        //Assert.assertNotNull(itemPedidoVerificacao.getProduto());

        //TESTANDO O CALCULO DE VALOR, para funionar
        //descomente o atributo preçoTotal e o método calcularpreçoTotal
        //que estão na classe ItemPedido
        //double valorEsperado = 1500;
        //Assert.assertEquals(valorEsperado, itemPedidoVerificacao.calcularPreçoTotal(produto));
    }
}

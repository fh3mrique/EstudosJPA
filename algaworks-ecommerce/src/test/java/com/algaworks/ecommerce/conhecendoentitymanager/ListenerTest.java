package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

public class ListenerTest extends EntityManagerFabrica {

    @Test
    public void carregarEntidades(){
        //no log vão aparecer a messager colocada no ListenerGenerico
        //pq tanto a classe Produto quanto Pedido foram anotados com
        //@EntityListeners({ListenerGenerico.class})
        Produto produto = entityManager.find(Produto.class, 1);
        Pedido pedido = entityManager.find(Pedido.class, 1);
    }
    @Test
    public void acionarListener(){
        Cliente cliente = entityManager.find(Cliente.class, 1); //Lionel Messi

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();
            entityManager.persist(pedido);
            entityManager.flush();

            pedido.setStatus(StatusPedido.PAGO);
        entityManager.getTransaction().commit();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVerificacao.getDataCriacao());
        Assert.assertNotNull(pedidoVerificacao.getDataUltimaAtualizacao());
    }
}

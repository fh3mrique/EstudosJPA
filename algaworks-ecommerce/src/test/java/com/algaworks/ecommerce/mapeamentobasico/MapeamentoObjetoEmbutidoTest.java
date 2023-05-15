package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.EnderecoEntregaPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapeamentoObjetoEmbutidoTest extends EntityManagerFabrica {

    @Test
    public void analisarMapeamentoObjetoEmbutido(){
        EnderecoEntregaPedido entrega = new EnderecoEntregaPedido();
        entrega.setCep("0000-000");
        entrega.setLogradouro("logradouro");
        entrega.setNumero("69");
        entrega.setRua("Burra nua");
        entrega.setBairro("Sitio nov");
        entrega.setEstado("PE");

        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setNotaFiscalId(1);
        pedido.setTotal(new BigDecimal(100));
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setEndereco(entrega);

        entityManager.getTransaction().begin();
            entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificao = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVerificao);
        Assert.assertNotNull(pedidoVerificao.getEndereco());
        Assert.assertNotNull(pedidoVerificao.getEndereco().getCep());


    }
}

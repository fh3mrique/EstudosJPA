package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

public class RemovendoEntidadesReferenciadasTest extends EntityManagerFabrica {

    @Test
    public void removendoEntidadeRelacionadas(){

        Pedido pedido = entityManager.find(Pedido.class, 1);

        //Confimando que pedidos tem ItensPedidos associado a ele
        Assert.assertFalse(pedido.getItens().isEmpty());

        entityManager.getTransaction().begin();
        //essa linha remove todos os itensPedido relacionados ao Pedido
        //atualmente armazenado no entityManager
            pedido.getItens().forEach(itensPedidos -> entityManager.remove(itensPedidos));
            entityManager.remove(pedido);
        entityManager.getTransaction().commit();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNull(pedidoVerificacao);

    }

}

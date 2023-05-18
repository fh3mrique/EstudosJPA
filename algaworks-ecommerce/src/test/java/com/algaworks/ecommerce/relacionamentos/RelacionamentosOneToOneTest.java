package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

public class RelacionamentosOneToOneTest extends EntityManagerFabrica {

    @Test
    public void testarRelacionamento() {
        //buscando pedido de id 1
        Pedido pedido = entityManager.find(Pedido.class, 1);

        PagamentoCartao pagamento = new PagamentoCartao();
        pagamento.setNumero("543654");
        pagamento.setStatus(StatusPagamento.RECEBIDO);
        //setando pedido ao pagamento do cartão
        pagamento.setPedido(pedido);

        entityManager.getTransaction().begin();
            entityManager.persist(pagamento);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificao = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVerificao.getPagamentoCartao());
    }
    @Test
    public void testarRelacionamentoPedidoNotaFiscal() {
        //buscando pedido de id 1
        Pedido pedido = entityManager.find(Pedido.class, 1);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setXml("TESTE");
        notaFiscal.setDataEmissao(new Date());

        //setando pedido na na nota fiscal
        notaFiscal.setPedido(pedido);

        entityManager.getTransaction().begin();
            entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificao = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVerificao.getNotaFiscal());
    }
}

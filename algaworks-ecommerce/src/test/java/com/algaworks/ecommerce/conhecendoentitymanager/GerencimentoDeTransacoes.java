package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Test;

public class GerencimentoDeTransacoes extends EntityManagerFabrica {

    @Test
    public void abrirEFecharTransacao() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        //Transação: É um período de tempo delimitado pelo begin e commit, em que
        //podemos fazer mudanças no banco de dados com consistência.
        entityManager.getTransaction().begin();
        pedido.setStatus(StatusPedido.PAGO);
        if (pedido.getPagamentoCartao() != null) {
            entityManager.getTransaction().commit();
        }
        else {
            /*O rollback no JPA (Java Persistence API) é um mecanismo que
            permite desfazer todas as operações realizadas dentro de uma
            transação em caso de erros ou exceções.*/
            entityManager.getTransaction().rollback();
        }

    }
}

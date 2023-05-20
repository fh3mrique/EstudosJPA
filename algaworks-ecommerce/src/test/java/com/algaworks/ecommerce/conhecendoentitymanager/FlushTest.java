package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Test;

public class FlushTest extends EntityManagerFabrica {

    @Test
    public void chamarFlush(){
        try{
            entityManager.getTransaction().begin();
            Pedido pedido = entityManager.find(Pedido.class, 1);

            pedido.setStatus(StatusPedido.PAGO);

            /*flush() é um método do JPA que sincroniza as alterações com o banco
            de dados. Ele envia as alterações pendentes no contexto de persistência
            para o banco de dados, garantindo que sejam executadas imediatamente.
            O flush() também verifica violações de integridade e lança exceções se
            necessário. É útil para garantir a persistência imediata ou verificar
            violações antes do commit, mas pode impactar o desempenho se usado em
            excesso.*/
            entityManager.flush();

            if(pedido.getPagamentoCartao() == null){
                throw new RuntimeException("Pedido ainda não foi paga");
            }
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }

    }
}

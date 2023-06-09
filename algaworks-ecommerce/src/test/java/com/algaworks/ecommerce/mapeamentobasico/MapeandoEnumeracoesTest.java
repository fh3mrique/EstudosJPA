package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;
import org.junit.Assert;
import org.junit.Test;

public class MapeandoEnumeracoesTest extends EntityManagerFabrica {

    @Test
    public void testarEnum(){
        Cliente cliente = new Cliente();
        //COMENTADO POR ESTAMOS USANDO STRATEGY.INDENTITY
        //cliente.setId(4);
        cliente.setNome("Hazard");
        cliente.setSexo(SexoCliente.MASCULINO);

        entityManager.getTransaction().begin();
            entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());

        Assert.assertNotNull(clienteVerificacao);
    }
}

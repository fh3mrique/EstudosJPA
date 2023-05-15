package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class PrimeioCrudTest extends EntityManagerFabrica {

    @Test
    public void inserirCliente(){
        Cliente cliente = new Cliente();

        cliente.setId(3);
        cliente.setNome("Neymar");

        entityManager.getTransaction().begin();
            entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Assert.assertNotNull(cliente);
        Assert.assertEquals("Neymar", cliente.getNome());
    }

    @Test
    public void buscarClientePorId(){
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Assert.assertEquals("Lionel Messi", cliente.getNome());
        Assert.assertNotNull(cliente);
    }

    @Test
    public void atualizarCliente(){
        Cliente cliente = new Cliente();

        cliente.setId(2);
        cliente.setNome("De bruyne");

        entityManager.getTransaction().begin();
            entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Assert.assertNotNull(cliente);
        Assert.assertNotEquals("Cristiano Ronaldo", cliente.getNome());
    }

    @Test
    public void removerCliente(){
        Cliente cliente = entityManager.find(Cliente.class, 2);

        entityManager.getTransaction().begin();
            entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, 2);
        Assert.assertNull(clienteVerificacao);
    }
}

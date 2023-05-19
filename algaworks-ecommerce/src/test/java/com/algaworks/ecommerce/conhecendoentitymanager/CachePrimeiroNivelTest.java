package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

public class CachePrimeiroNivelTest  extends EntityManagerFabrica {

    @Test
    public void verificarCache(){
        Produto produto = entityManager.find(Produto.class, 1);

        System.out.println(produto.getNome());

        System.out.println("-------------------------------------");

        System.out.println("Depois do primeiro find o objeto já está em memoria," +
                " logo nesse segundo find quando olhar o log não terá consulta");

        Produto produtoResgatado = entityManager.find(Produto.class, produto.getId());

        System.out.println(produtoResgatado.getNome());
    }
}

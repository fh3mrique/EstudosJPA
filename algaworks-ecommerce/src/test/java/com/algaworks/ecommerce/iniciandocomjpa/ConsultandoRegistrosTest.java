package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.EntityManagerFabrica;
import org.junit.*;

public class ConsultandoRegistrosTest extends EntityManagerFabrica {

    //MÉTODOS PARA TESTER AS CONSULTAS AO BANCO

    @Test
    public void buscarPorIndentificador(){
        /*O método entityManager.find() é um dos métodos da API JPA (Java Persistence API) que permite buscar
        entidades no banco de dados pelo seu identificador (chave primária). Ele recebe como parâmetros a
        classe da entidade que se deseja buscar e o valor da chave primária que identifica a entidade.*/
        Produto produto = entityManager.find(Produto.class, 1);

        Assert.assertEquals("Kindle", produto.getNome());
        Assert.assertNotNull(produto);
    }

    @Test
    public void atualizarReferencia(){
        Produto produto = entityManager.find(Produto.class, 1);

        produto.setNome("OutroNome");

        /*O método refresh() da classe EntityManager é usado para atualizar o estado de um objeto gerenciado
        pela entidade a partir do banco de dados. Ele verifica o banco de dados e atualiza as propriedades do
        objeto gerenciado com os valores mais recentes da fonte de dados.*/
        entityManager.refresh(produto);

        Assert.assertNotNull(produto);
        Assert.assertEquals("Kindle", produto.getNome());
    }

}

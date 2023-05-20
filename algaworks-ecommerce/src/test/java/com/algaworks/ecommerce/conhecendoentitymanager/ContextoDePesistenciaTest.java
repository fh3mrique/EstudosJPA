package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;

public class ContextoDePesistenciaTest extends EntityManagerFabrica {

    @Test
    public void usarContextoDePesistencia(){
        /*O dirty checking é um recurso do JPA que rastreia automaticamente
        as alterações feitas em entidades gerenciadas. O JPA mantém uma cópia
        do estado original da entidade e compara com o estado atual durante uma
        transação. Se houver diferenças, as alterações são sincronizadas com o
        banco de dados apenas nos atributos modificados. Isso evita a necessidade
        de atualizar todos os atributos e simplifica o processo de persistência,
        mantendo a consistência entre a entidade e o banco de dados.*/

        entityManager.getTransaction().begin();
        //Quando usamos find o objeto fica managed
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setPreco(new BigDecimal(10.0));

        Produto produto2 = new Produto();
        produto2.setNome("Oyasumi punpun");
        produto2.setPreco(new BigDecimal(10.0));
        produto2.setDescricao("Melhor mangá já feito");
        ////Quando usamos persist o objeto fica managed
        entityManager.persist(produto2);


        Produto produto3 = new Produto();
        produto3.setNome("Kokou no hito");
        produto3.setPreco(new BigDecimal(10.0));
        produto3.setDescricao("Segundo mangá já feito");
        ////Quando usamos merge o objeto fica managed
        entityManager.merge(produto3);

        entityManager.getTransaction().commit();
    }
}

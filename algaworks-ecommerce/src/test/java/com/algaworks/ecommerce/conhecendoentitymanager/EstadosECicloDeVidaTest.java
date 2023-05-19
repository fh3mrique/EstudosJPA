package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.Test;

public class EstadosECicloDeVidaTest extends EntityManagerFabrica {

    @Test
    public void analisarEstados(){
        //Estado transient
        Categoria categoriaNovo = new Categoria();

        //Estado managed
        Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo);

        //Estado managed
        Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1);

        //Estado removed
        entityManager.remove(categoriaGerenciada);

        //Estado managed
        entityManager.persist(categoriaGerenciada);

        entityManager.detach(categoriaGerenciada);
    }


}

package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerFabrica;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Test;

public class EagerELazyTest extends EntityManagerFabrica {

    @Test
    public void testarLazy(){
        Pedido pedido = entityManager.find(Pedido.class, 1);
        /* estratégia LAZY indica que os objetos associados
        serão carregados apenas quando forem explicitamente acessados ou
        solicitados. Isso significa que as associações não são recuperadas
        automaticamente junto com o objeto principal, economizando recursos de
        processamento e minimizando o tráfego de dados do banco de dados para a
        aplicação.*/
        pedido.getItens();
    }

    @Test
    public void testarEager(){
        Pedido pedido = entityManager.find(Pedido.class, 1);
        Cliente cliente = entityManager.find(Cliente.class, 1);
        /*A estratégia EAGER indica que os objetos associados devem ser
        carregados imediatamente junto com o objeto principal. Isso significa
        que, ao carregar uma entidade, todas as suas associações definidas
        como EAGER também serão recuperadas do banco de dados. Dessa forma,
        todos os dados necessários estarão disponíveis em memória, mesmo que
        não sejam utilizados imediatamente.*/

        pedido.setCliente(cliente);
    }
}

package com.algaworks.ecommerce.listener;

import javax.persistence.PostLoad;

public class ListenerGenerico {

    @PostLoad
    public void logCarregamento(Object obj){
        System.out.println("Entidade " + obj.getClass().getSimpleName() + " foi carregada");

    }
}

package com.algaworks.ecommerce.service;

import com.algaworks.ecommerce.model.Pedido;

public class NotaFiscalService {

    public void gerar(Pedido pedido){
        System.out.println("GERANDO NOTA FISCAL PARA O PEDIDO " + pedido.getId());
    }
}

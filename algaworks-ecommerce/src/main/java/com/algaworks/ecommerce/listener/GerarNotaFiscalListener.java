package com.algaworks.ecommerce.listener;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.service.NotaFiscalService;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class GerarNotaFiscalListener {


    /*Um Listener JPA é uma classe que recebe eventos relacionados a entidades e
    ao ciclo de vida da persistência no JPA. Existem três tipos de listeners:
    Listeners de entidade, Callbacks de ciclo de vida da entidade e Callbacks
    globais.

Listeners de entidade (Entity Listeners): São classes anotadas com @EntityListeners
e associadas a uma ou mais entidades. Esses listeners são acionados quando eventos
relacionados às entidades ocorrem, como antes ou depois de persistir, atualizar,
carregar ou remover uma entidade. Eles podem ser usados para executar ações
adicionais, como validar dados, fazer auditoria, entre outras tarefas relacionadas
às entidades.

Callbacks de ciclo de vida da entidade (Entity Lifecycle Callbacks): São métodos
definidos dentro da própria entidade e anotados com anotações específicas, como
@PrePersist, @PostPersist, @PreUpdate, @PostUpdate, @PreRemove, @PostRemove,
@PostLoad. Esses métodos são acionados automaticamente pelo JPA em diferentes
estágios do ciclo de vida da entidade, permitindo que você execute lógica
personalizada antes ou depois de certos eventos ocorrerem.

Os Callbacks globais são métodos definidos em uma classe separada e registrados
globalmente no arquivo persistence.xml. Eles são acionados em eventos de ciclo
de vida da entidade, sem a necessidade de anotar individualmente as entidades.

Os listeners JPA oferecem flexibilidade para estender e personalizar o
comportamento padrão do JPA, permitindo adicionar lógica adicional em resposta
a eventos específicos. Isso ajuda a separar a lógica de negócios da lógica de
persistência, tornando o código mais modular e organizado.*/

    private NotaFiscalService notaFiscalService = new NotaFiscalService();

    @PrePersist
    @PreUpdate
    public void gerar(Pedido pedido){
        if (pedido.isPago() && pedido.getNotaFiscal() == null){
                notaFiscalService.gerar(pedido);
        }
    }
}

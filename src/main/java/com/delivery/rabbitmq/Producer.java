package com.delivery.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delivery.entities.Estoque;
import com.delivery.entities.Item;
import com.delivery.entities.Pedido;

@Component
public class Producer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send(Pedido pedido) throws Exception {
		for (Item item : pedido.getItens()) {
			System.out.println("Enviando Mensagem Item Estoque: ID: " + item.getId());
			Estoque estoque = new Estoque(item.getId(), 1L);
			rabbitTemplate.convertAndSend("delivery.estoque.queue", estoque);
		}
	}

}

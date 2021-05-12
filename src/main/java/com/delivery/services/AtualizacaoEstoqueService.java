package com.delivery.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.delivery.entities.Estoque;
import com.delivery.entities.Item;
import com.delivery.entities.Pedido;
import com.delivery.rabbitmq.Producer;

import reactor.core.publisher.Mono;

@Component
public class AtualizacaoEstoqueService {
	
	@Autowired
	private Producer producer;

	public void send(Pedido pedido){
		
//		RestTemplate rest = new RestTemplate();
//		String url = "http://localhost:9000/estoque/atualiza";
//		
//		for (Item item : pedido.getItens()) {
//			EstoqueDTO estoque = new EstoqueDTO(item.getId(), 1L);
//			HttpEntity<EstoqueDTO>	requestEstoque	=	new	HttpEntity<>(estoque);
//			String	responseEstoque	= rest.postForObject(url,	requestEstoque,	String.class);
//			System.out.println("Resposta:	"+responseEstoque);
//		}
		
		try {
			producer.send(pedido);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void sendReativo(Pedido pedido) {
		WebClient cliente = WebClient.create("http://localhost:9000");
		for (Item item : pedido.getItens()) {
			Estoque estoque = new Estoque(item.getId(), 1L);
			Mono<String> clienteReativo = cliente.post().uri("/estoque/atualiza-reativo")
			.body(Mono.just(estoque), Estoque.class)
			.retrieve().bodyToMono(String.class);
		
			System.out.println("Resposta:	"+ clienteReativo);
			clienteReativo.subscribe(System.out::println);
		}
	}
}

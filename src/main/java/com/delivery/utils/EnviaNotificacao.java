package com.delivery.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delivery.dto.Notificacao;
import com.delivery.entities.Cliente;
import com.delivery.entities.Pedido;

@Component
public class EnviaNotificacao{

	
	@Autowired
	Notificacao notificacao;
	
	public void enviaEmail(Cliente cliente, Pedido pedido) {
		if(notificacao.envioAtivo()) {
			System.out.println("Notificacao enviada");
		} else {
			System.out.println("Notificacao desligada");
		}
	}
}

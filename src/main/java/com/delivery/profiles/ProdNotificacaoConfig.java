package com.delivery.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.delivery.dto.Notificacao;

@Component
@Profile("prod")
public class ProdNotificacaoConfig implements Notificacao {

	@Override
	public boolean envioAtivo() {
		// TODO Auto-generated method stub
		return true;
	}

}

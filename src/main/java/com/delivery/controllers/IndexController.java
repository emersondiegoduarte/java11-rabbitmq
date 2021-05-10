package com.delivery.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.dto.MensagemDTO;

@RestController
@RequestMapping("/index")
public class IndexController {
	
	@Value("${mensagem:nenhuma}")
	private	String	mensagem;
	
	
	@GetMapping("/oferta")
	public MensagemDTO getMessage(HttpServletRequest	request) {
		return	new	MensagemDTO(this.mensagem,request.getServerName()
				+	":"	+	request.getServerPort(),"");
	}

}

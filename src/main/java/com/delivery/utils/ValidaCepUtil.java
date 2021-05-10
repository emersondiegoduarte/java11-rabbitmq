package com.delivery.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.delivery.dto.CepDTO;

public class ValidaCepUtil {
	
	public static Boolean validaCep(String cep) {
		RestTemplate	restTemplate	=	new	RestTemplate();
		String	cepURL = "https://viacep.com.br/ws/"+ cep + "/json";
		ResponseEntity<CepDTO>	response = 	restTemplate.getForEntity(cepURL, CepDTO.class);
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			CepDTO cepDTO = response.getBody();
			if(cepDTO.getUf().equals("CE")) {
				return Boolean.TRUE;
			}
		}
		
		return Boolean.FALSE;
	}

}

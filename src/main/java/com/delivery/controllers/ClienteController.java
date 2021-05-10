package com.delivery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.entities.Cliente;
import com.delivery.services.repositories.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@PostMapping("/store")
	public ResponseEntity<Cliente> store(@RequestBody Cliente cliente){
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> recuperarCliente(@PathVariable("id") Long id) {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}

}

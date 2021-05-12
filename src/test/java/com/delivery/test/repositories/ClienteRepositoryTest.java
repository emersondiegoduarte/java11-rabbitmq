package com.delivery.test.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.delivery.entities.Cliente;
import com.delivery.repositories.ClienteRepository;

@SpringBootTest
public class ClienteRepositoryTest {
	
	@Autowired
	private ClienteRepository clientRepository;
	
	@Test
	public void buscaClientesCadastrados() {
		Page<Cliente> clientes = clientRepository.findAll(PageRequest.of(0, 10));
		assertThat(clientes.getTotalElements()).isGreaterThan(0L);
	}
	
	
	@Test
	public void buscaClientesCadastradosPorNome() {
		Cliente clienteNaoEncontrado = clientRepository.findByNome("Jamille");
		assertThat(clienteNaoEncontrado).isNull();
		
		Cliente clienteEncontrado = clientRepository.findByNome("Diego");
		assertThat(clienteEncontrado).isNotNull();
	}

}

package com.delivery.controllers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.dto.RespostaDTO;
import com.delivery.entities.Cliente;
import com.delivery.entities.Item;
import com.delivery.entities.Pedido;
import com.delivery.services.repositories.ClienteRepository;
import com.delivery.services.repositories.ItemRepository;
import com.delivery.utils.ValidaCepUtil;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping("/novo/{clienteId}/{listaDeItens}")
	public ResponseEntity<RespostaDTO> createPedido(@PathVariable("clienteId") Long clienteId, @PathVariable("listaDeItens") String listaDeItens){
		RespostaDTO	dto	=	new	RespostaDTO();
		try	{
			Cliente	c =	clienteRepository.getOne(clienteId);
			Boolean enderecoValido = ValidaCepUtil.validaCep("60140100");
			if(enderecoValido) {
				String[] listaDeItensID	= listaDeItens.split(",");
				Pedido	pedido	=  new Pedido();
				BigDecimal	valorTotal	=	BigDecimal.ZERO;
				List<Item>	itensPedidos	=	new	ArrayList<Item>();
				for	(String	itemId	:	listaDeItensID)	{
					Item	item	=	itemRepository.getOne(Long.parseLong(itemId));
					itensPedidos.add(item);
					valorTotal = valorTotal.add(item.getPreco());
				}
				pedido.setItens(itensPedidos);
				pedido.setValorTotal(valorTotal);
				pedido.setData(LocalDateTime.now());
				pedido.setCliente(c);
				c.getPedidos().add(pedido);
				
				clienteRepository.saveAndFlush(c);
				Long ultimoPedido = c.getPedidos().stream().mapToLong(Pedido::getId).reduce(0,Long::max);
				dto	=	new	RespostaDTO(ultimoPedido,valorTotal,"Pedido efetuado com sucesso");
			} else {
				dto.setMensagem("Erro: Cep Inválido");
				return	ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
			}
			
		}	catch	(Exception	e)	{
			dto.setMensagem("Erro:	"	+	e.getMessage());
			return	ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
		}
		return	ResponseEntity.status(HttpStatus.CREATED).body(dto);
		
	}

}

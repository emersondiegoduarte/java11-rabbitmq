package com.delivery.dto;

import java.math.BigDecimal;

public class RespostaDTO {
	
	private BigDecimal valorTotal;
	private Long pedido;
	private String mensagem;
	
	public RespostaDTO(Long pedido, BigDecimal valorTotal, String mensagem) {
	// TODO Auto-generated constructor stub
		this.pedido = pedido;
		this.valorTotal = valorTotal;
		this.mensagem = mensagem;
	}

	public RespostaDTO() {
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Long getPedido() {
		return pedido;
	}

	public void setPedido(Long pedido) {
		this.pedido = pedido;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	

}

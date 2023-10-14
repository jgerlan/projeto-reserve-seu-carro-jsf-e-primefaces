package com.projetoes.ecommerce.model;

public enum StatusCarro {
	Livre("Carro livre para reserva"), 
	Reservado("Carro reservado");
	
	private String descricao;

	StatusCarro(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}

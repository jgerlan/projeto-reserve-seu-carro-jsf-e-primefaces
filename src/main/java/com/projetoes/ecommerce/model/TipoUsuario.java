package com.projetoes.ecommerce.model;

public enum TipoUsuario {
	Cliente("Cliente"), 
	Administrador("Administrador");
	
	private String descricao;

	TipoUsuario(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}

package com.projetoes.ecommerce.model;

public enum StatusUsuario {
	Ativo("Ativo"), 
	Inativo("Inativo");
	
	private String descricao;

	StatusUsuario(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}

package com.projetoes.ecommerce.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projetoes.ecommerce.model.Carro;
import com.projetoes.ecommerce.respository.CarroDAO;
import com.projetoes.ecommerce.util.Transacional;

public class CadastroCarroService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarroDAO carros;
	
	@Transacional
	public void salvar(Carro carro) {
		carros.guardar(carro);
	}
	
	@Transacional
	public void atualizar(Carro carro) {
		carros.guardar(carro);
	}
	
	@Transacional
	public void excluir(long id) {
		carros.remover(id);
	}
}

package com.projetoes.ecommerce.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projetoes.ecommerce.model.Carro;
import com.projetoes.ecommerce.model.HistoricoReservaCarro;
import com.projetoes.ecommerce.respository.CarroDAO;
import com.projetoes.ecommerce.respository.HistoricoReservaCarroDAO;
import com.projetoes.ecommerce.util.Transacional;

public class CadastroHistoricoReservaCarrosService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private HistoricoReservaCarroDAO historicoReservaCarros;
	
	@Inject
	private CarroDAO carros;
	
	@Transacional
	public void salvar(HistoricoReservaCarro historico, Carro carro) {
		try {
			carros.guardar(carro);
			historicoReservaCarros.guardar(historico);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Transacional
	public void atualizar(HistoricoReservaCarro historico, Carro carro) {
		try {
			carros.guardar(carro);
			historicoReservaCarros.guardar(historico);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Transacional
	public void excluir(long id) {
		historicoReservaCarros.remover(id);
	}
}

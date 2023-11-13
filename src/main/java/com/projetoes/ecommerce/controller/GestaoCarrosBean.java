package com.projetoes.ecommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoes.ecommerce.model.Carro;
import com.projetoes.ecommerce.model.FiltroListarCarros;
import com.projetoes.ecommerce.model.StatusCarro;
import com.projetoes.ecommerce.respository.CarroDAO;

@Named
@SessionScoped
public class GestaoCarrosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CarroDAO carros;

	private Carro carro;

	private List<Carro> listaCarros;

	private FiltroListarCarros filtros;
	
	public void index() {
		this.carro = new Carro();
		filtros = new FiltroListarCarros();
		filtros.setStatus(StatusCarro.Livre);
		listaCarros = carros.listarPorFiltros(filtros);
	}
	
	public String detalhes(Long id) {
		this.carro = carros.porId(id);
		return "detalheCarro?faces-redirect=true";
	}

	public void pesquisar() {
		listaCarros = carros.listarPorFiltros(filtros);
	}

	public void todosCarros() {
		this.carro = new Carro();
		filtros = new FiltroListarCarros();
		listaCarros = carros.listarPorFiltros(filtros);
	}

	public List<Carro> getListaCarros() {
		return listaCarros;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public FiltroListarCarros getFiltros() {
		return filtros;
	}

	public void setFiltros(FiltroListarCarros filtros) {
		this.filtros = filtros;
	}

	public void setListaCarros(List<Carro> listaCarros) {
		this.listaCarros = listaCarros;
	}
	
	public StatusCarro[] getStatus() {
        return StatusCarro.values();
    }
	
}

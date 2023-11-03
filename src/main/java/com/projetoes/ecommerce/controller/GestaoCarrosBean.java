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

	private String textoPesquisa;
	
	private FiltroListarCarros filtros;
	
	public void index() {
		this.textoPesquisa = "";
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
		filtros.setMarca(textoPesquisa);
		listaCarros = carros.listarPorFiltros(filtros);
	}

	public void todosCarros() {
		this.textoPesquisa = "";
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

	public String getTextoPesquisa() {
		return textoPesquisa;
	}

	public void setTextoPesquisa(String textoPesquisa) {
		this.textoPesquisa = textoPesquisa;
	}

}

package com.projetoes.ecommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import com.projetoes.ecommerce.model.Carro;
import com.projetoes.ecommerce.respository.Carros;

@Named
@SessionScoped
public class GestaoCarrosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Carros carros;
	
	private Carro carro;
	
	private List<Carro> listaCarros;
	
	private String textoPesquisa;
	
	public String detalhes(Long id) {
		this.carro = carros.porId(id);
		return "detalheCarro?faces-redirect=true";
	}
	
	public void pesquisar(){
		listaCarros = carros.pesquisar(textoPesquisa);
	}
	
	public void todosCarros() {
		listaCarros = carros.listarTodos();
	}
	
	public List<Carro> getListaCarros(){
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

package com.projetoes.ecommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoes.ecommerce.model.Carro;
import com.projetoes.ecommerce.respository.Carros;

@Named
@ViewScoped
public class GestaoCarrosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Carros carros;
	
	private List<Carro> listaCarros;
	
	public void todosCarros() {
		listaCarros = carros.listarTodos();
	}
	
	public List<Carro> getListaCarros(){
		return listaCarros;
	}
}

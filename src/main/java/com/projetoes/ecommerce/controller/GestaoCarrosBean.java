package com.projetoes.ecommerce.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.projetoes.ecommerce.model.Carro;

@Named
@ViewScoped
public class GestaoCarrosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Carro carro;
	
	public Carro getCarro() {
		return carro;
	}
}

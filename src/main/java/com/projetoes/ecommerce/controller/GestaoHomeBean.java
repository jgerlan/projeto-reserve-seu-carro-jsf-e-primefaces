package com.projetoes.ecommerce.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class GestaoHomeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public String index() {
		return "home?faces-redirect=true";
	}
	
	public String gestaoUsuarios() {
		return "gestaoUsuarios?faces-redirect=true";
	}
	
	public String gestaoCarros() {
		return "gestaoCarros?faces-redirect=true";
	}
}

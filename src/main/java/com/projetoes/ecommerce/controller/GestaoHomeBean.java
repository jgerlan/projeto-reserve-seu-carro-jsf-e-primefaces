package com.projetoes.ecommerce.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class GestaoHomeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String index() {return "home?faces-redirect=true";}
}

package com.projetoes.ecommerce.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoes.ecommerce.respository.UsuarioDAO;


@Named
@SessionScoped
public class RecuperarSenhaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	

    @Inject
    private UsuarioDAO usuarioDAO;

    private String login;
    private String dataNasc;

	public static String getSenha(String login, String dataNasc) {
	        String senha = null;
	        

	        return senha;
	    }
	
	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getDataNasc() {
		return dataNasc;
	}


	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

}

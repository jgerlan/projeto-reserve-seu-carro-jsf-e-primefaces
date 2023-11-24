package com.projetoes.ecommerce.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoes.ecommerce.service.LoginService;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private LoginService loginService;

    private String username;
    private String senha;

    public String login() {
        try {
        	return loginService.validateUsernamePassword(username, senha);
		} catch (Exception e) {
			return "login";
		}
    }

    public String logout() {
    	try {
        	
        	return loginService.logout();
		} catch (Exception e) {
			return "login";
		}
    }
    
    public boolean isLoggedIn() {
    	return loginService.isLoggedIn();
    }
    
    public boolean isNotLoggedIn() {
    	return !loginService.isLoggedIn();
    }
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}

package com.projetoes.ecommerce.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.projetoes.ecommerce.model.Usuario;
import com.projetoes.ecommerce.respository.UsuarioDAO;
import com.projetoes.ecommerce.util.FacesMessages;
import com.projetoes.ecommerce.util.SessionUtils;

public class LoginService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarios;

	@Inject
	private FacesMessages messages;
	
	public String validateUsernamePassword(String login, String senha) {
		Usuario usuario = usuarios.validarLoginESenha(login, senha);
		if (usuario != null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", usuario.getLogin());
			return "admin";
		} else {
			messages.aviso("Login e/ou senha incorretos!", "Por favor entre com os dados corretos.");
			return "login";
		}
	}
	
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "logout";
	}
	
	public boolean isLoggedIn() {
		HttpSession session = SessionUtils.getSession();
	    return session.getAttribute("username") != null;
	}
}

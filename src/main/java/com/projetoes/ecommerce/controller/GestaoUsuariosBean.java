package com.projetoes.ecommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoes.ecommerce.model.Usuario;
import com.projetoes.ecommerce.respository.UsuarioDAO;

@Named
@SessionScoped
public class GestaoUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarios;

	private Usuario usuario;

	private List<Usuario> listaUsuarios;
	
	private String textoPesquisa;

	public String detalhes(Long id) {
		this.usuario = usuarios.porId(id);
		return "detalheUsuario?faces-redirect=true";
	}

	public void todosUsuarios() {
		listaUsuarios = usuarios.listarTodos();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public String getTextoPesquisa() {
		return textoPesquisa;
	}

	public void setTextoPesquisa(String textoPesquisa) {
		this.textoPesquisa = textoPesquisa;
	}
	
	
}

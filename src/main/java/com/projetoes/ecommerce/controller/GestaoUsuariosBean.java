package com.projetoes.ecommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoes.ecommerce.model.FiltroListarUsuarios;
import com.projetoes.ecommerce.model.StatusUsuario;
import com.projetoes.ecommerce.model.TipoUsuario;
import com.projetoes.ecommerce.model.Usuario;
import com.projetoes.ecommerce.respository.UsuarioDAO;

@Named
@SessionScoped
public class GestaoUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarios;
	
	private Usuario usuario;

	private StatusUsuario[] statusOptions;
	
	private TipoUsuario[] tipoOptions;
	
	private TipoUsuario tipo;

	private List<Usuario> listaUsuarios;
	
	private FiltroListarUsuarios filtros;
	
	@PostConstruct
	public void init() {
	    statusOptions = StatusUsuario.values();
	    tipoOptions = TipoUsuario.values();
	}
	
	public void index() {
		this.usuario = new Usuario();
		filtros = new FiltroListarUsuarios();
		listaUsuarios = usuarios.listarPorFiltros(filtros);
	}

	public void pesquisar() {
		listaUsuarios = usuarios.listarPorFiltros(filtros);
	}

	public void todosUsuarios() {
		this.usuario = new Usuario();
		filtros = new FiltroListarUsuarios();
		listaUsuarios = usuarios.listarPorFiltros(filtros);
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

	public FiltroListarUsuarios getFiltros() {
		return filtros;
	}

	public void setFiltros(FiltroListarUsuarios filtros) {
		this.filtros = filtros;
	}
	
	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public StatusUsuario[] getStatusOptions() {
		return statusOptions;
	}

	public TipoUsuario[] getTipoOptions() {
		return tipoOptions;
	}
	
}

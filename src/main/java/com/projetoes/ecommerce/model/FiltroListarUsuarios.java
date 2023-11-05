package com.projetoes.ecommerce.model;

import java.io.Serializable;
import java.util.Date;

public class FiltroListarUsuarios implements Serializable {
	private static final long serialVersionUID = 1L;

	private String login;

	private String senha;

	private String nome;

	private StatusUsuario status;

	private Date deDataNasc;
	
	private Date ateDataNasc;

	private TipoUsuario tipo;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public StatusUsuario getStatus() {
		return status;
	}

	public void setStatus(StatusUsuario status) {
		this.status = status;
	}
	
	public Date getDeDataNasc() {
		return deDataNasc;
	}

	public void setDeDataNasc(Date deDataNasc) {
		this.deDataNasc = deDataNasc;
	}

	public Date getAteDataNasc() {
		return ateDataNasc;
	}

	public void setAteDataNasc(Date ateDataNasc) {
		this.ateDataNasc = ateDataNasc;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
	
}

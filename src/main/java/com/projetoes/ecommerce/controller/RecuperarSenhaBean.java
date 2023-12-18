package com.projetoes.ecommerce.controller;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.projetoes.ecommerce.model.Usuario;
import com.projetoes.ecommerce.respository.UsuarioDAO;
import com.projetoes.ecommerce.util.FacesMessages;

@Named
@ViewScoped
public class RecuperarSenhaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	

    @Inject
    private UsuarioDAO usuarioDAO;
    
    @Inject
	private FacesMessages messages;
    
    private Usuario usuario;

    private String login;
    private Date dataNasc;
    
    @PostConstruct
    public void init() {
    	usuario = new Usuario();
    	login = "";
    	dataNasc = null;
    }
    
	public void recuperarSenha() {

		try {

			if ((login == null || login.equals("")) || dataNasc == null) {
				PrimeFaces.current().ajax().addCallbackParam("usuarioEncontrado", false);
				messages.aviso("Dados incorretos!", "Verificar dados informados!");
				return;
			}

			usuario = usuarioDAO.buscarPorLoginEDataNascimento(login, dataNasc);

			if (usuario == null) {
				PrimeFaces.current().ajax().addCallbackParam("usuarioEncontrado", false);
				messages.erro("Usuário não encontrado!", "Verificar dados informados!");
				return;
			}

			PrimeFaces.current().ajax().addCallbackParam("usuarioEncontrado", true);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
}

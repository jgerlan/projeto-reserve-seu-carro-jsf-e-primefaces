package com.projetoes.ecommerce.controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import com.projetoes.ecommerce.model.Usuario;
import com.projetoes.ecommerce.respository.UsuarioDAO;

import java.io.Serializable;
import java.util.Date;

@Named
@RequestScoped
public class CadastroBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioDAO usuarioDAO;

    private Usuario novoUsuario = new Usuario();

    public String cadastrar() {
        try {
			/*
			 * if (!novoUsuario.getSenha().equals(novoUsuario.getConfirmacaoSenha())) {
			 * FacesContext.getCurrentInstance().addMessage(null, new
			 * FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas não coincidem.", null));
			 * return null; }
			 */

            if (usuarioDAO.existeNomeUsuario(novoUsuario.getNome())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome de usuário já em uso.", null));
                return null;
            }

            // idade maior de 18 anos
            Date dataNascimento = novoUsuario.getDataNasc();
            if (dataNascimento != null) {
                long idadeEmMilissegundos = new Date().getTime() - dataNascimento.getTime();
                long idadeEmAnos = idadeEmMilissegundos / (365 * 24 * 60 * 60 * 1000);
                if (idadeEmAnos < 18) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "O usuário deve ser maior de 18 anos.", null));
                    return null;
                }
            }

            usuarioDAO.salvar(novoUsuario);

            novoUsuario = new Usuario();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso!", null));

            // Envia para página de login após o cadastro bem-sucedido
            return "login.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar o usuário.", null));
            return null;
        }
    }

    public Usuario getNovoUsuario() {
        return novoUsuario;
    }

    public void setNovoUsuario(Usuario novoUsuario) {
        this.novoUsuario = novoUsuario;
    }
}

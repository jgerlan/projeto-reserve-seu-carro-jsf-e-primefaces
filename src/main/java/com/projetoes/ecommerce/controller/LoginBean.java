package com.projetoes.ecommerce.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;
import java.io.Serializable;

import com.projetoes.ecommerce.model.TipoUsuario;
import com.projetoes.ecommerce.model.Usuario;
import com.projetoes.ecommerce.respository.UsuarioDAO;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioDAO usuarioDAO;

    private String username;
    private String password;


    public String login() {
        try {
            Usuario usuario = usuarioDAO.autenticar(username, password);

            if (usuario != null) {
                // Autenticação bem-sucedida
                // Adicione o usuário autenticado
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuario);

                // Redireciona com base no tipo de usuário
                if (usuario.getTipo() == TipoUsuario.Administrador) {
                    return "admin/home?faces-redirect=true"; // Página inicial do administrador
                } else {
                    return "user/home?faces-redirect=true"; // Página inicial do usuário
                }
            } else {
                // Falha na autenticação
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha na autenticação.", null));
                return null; // Permanece na página de login
            }
        } catch (Exception e) {
            // exceções, se necessário
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na autenticação.", null));
            return null;
        }
    }

    public String logout() {
        // encerra a sessão
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

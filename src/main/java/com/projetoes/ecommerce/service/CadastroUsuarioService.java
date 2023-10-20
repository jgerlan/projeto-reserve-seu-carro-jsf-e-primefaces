package com.projetoes.ecommerce.service;

import java.io.Serializable;
import javax.inject.Inject;
import com.projetoes.ecommerce.model.Usuario;
import com.projetoes.ecommerce.respository.UsuarioDAO;
import com.projetoes.ecommerce.util.Transacional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarios;
	
	@Transacional
	public void salvar(Usuario usuario) {
		usuarios.guardar(usuario);
	}
	
	@Transacional
	public void excluir(long id) {
		usuarios.remover(id);
	}
}

package com.projetoes.ecommerce.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.projetoes.ecommerce.model.Usuario;
import com.projetoes.ecommerce.respository.HistoricoReservaCarroDAO;
import com.projetoes.ecommerce.respository.UsuarioDAO;
import com.projetoes.ecommerce.util.Transacional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarios;
	
	@Inject
	private HistoricoReservaCarroDAO historicoReservaCarros;

	@Transacional
	public void salvar(Usuario usuario) {
		usuarios.guardar(usuario);
	}
	
	@Transacional
	public void atualizar(Usuario usuario) {
		usuarios.guardar(usuario);
	}

	@Transacional
	public void excluir(long id) {
		try {
			historicoReservaCarros.excluirPorUsuarioId(id);
			usuarios.remover(id);
		} catch (Exception e) {
			throw e;
		}
	}
}

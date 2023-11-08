package com.projetoes.ecommerce.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.projetoes.ecommerce.model.DadosCadastroVo;
import com.projetoes.ecommerce.model.FiltroListarUsuarios;
import com.projetoes.ecommerce.model.StatusUsuario;
import com.projetoes.ecommerce.model.TipoUsuario;
import com.projetoes.ecommerce.model.Usuario;
import com.projetoes.ecommerce.respository.UsuarioDAO;
import com.projetoes.ecommerce.service.CadastroUsuarioService;
import com.projetoes.ecommerce.util.FacesMessages;

@Named
@SessionScoped
public class GestaoUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarios;

	@Inject
	private FacesMessages messages;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

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

	public void prepararNovoUsuario() {
		usuario = new Usuario();
	}

	public void salvar() {
		boolean usuarioSalvo = false;

		try {
			DadosCadastroVo dadosCadastroVo = new DadosCadastroVo();
			dadosCadastroVo.setUsuarioIdCriacao(1);
			dadosCadastroVo.setUsuarioIdAtualizacao(1);
			dadosCadastroVo.setDataCriacao(new Date());
			dadosCadastroVo.setDataAtualizacao(new Date());
			usuario.setDadosCadastro(dadosCadastroVo);

			cadastroUsuarioService.salvar(usuario);

			atualizarRegistros();

			usuarioSalvo = true;
			PrimeFaces.current().ajax().addCallbackParam("usuarioSalvo", usuarioSalvo);
			messages.info("Usuario salvo com sucesso!");

			PrimeFaces.current().ajax()
					.update(Arrays.asList("frm-usuarios:usuariosDataTable", "frm-usuarios:messages"));

		} catch (Exception e) {
			PrimeFaces.current().ajax().addCallbackParam("usuarioSalvo", usuarioSalvo);
			messages.erro("Erro ao salvar usuário!");
		}
	}

	public void atualizar() {
		boolean usuarioAtualizado = false;

		try {
			DadosCadastroVo dadosCadastroVo = usuario.getDadosCadastro();
			dadosCadastroVo.setUsuarioIdAtualizacao(1);
			dadosCadastroVo.setDataAtualizacao(new Date());
			usuario.setDadosCadastro(dadosCadastroVo);

			cadastroUsuarioService.atualizar(usuario);

			atualizarRegistros();

			usuarioAtualizado = true;
			PrimeFaces.current().ajax().addCallbackParam("usuarioAtualizado", usuarioAtualizado);
			messages.info("Usuario atualizado com sucesso!");

			PrimeFaces.current().ajax()
					.update(Arrays.asList("frm-usuarios:usuariosDataTable", "frm-usuarios:messages"));

		} catch (Exception e) {
			PrimeFaces.current().ajax().addCallbackParam("usuarioAtualizado", usuarioAtualizado);
			messages.erro("Erro ao atualizar usuário!");
		}
	}
	
	public void excluir(Long id) {
		cadastroUsuarioService.excluir(id);
        
        usuario = null;
        
        atualizarRegistros();
        
        messages.info("Usuário excluído com sucesso!");
    }

	public void prepararEdicao(Long id) {
		usuario = new Usuario();
		this.usuario = usuarios.porId(id);
	}
	
	private void atualizarRegistros() {
		if (filtroVazio()) {
			pesquisar();
		} else {
			todosUsuarios();
		}
	}

	private boolean filtroVazio() {
		return this.filtros.getLogin() != null && !"".equals(this.filtros.getLogin()) && this.filtros.getNome() != null
				&& !"".equals(this.filtros.getNome()) && this.filtros.getStatus() != null
				&& this.filtros.getTipo() != null && this.filtros.getDeDataNasc() != null
				&& this.filtros.getAteDataNasc() != null;
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

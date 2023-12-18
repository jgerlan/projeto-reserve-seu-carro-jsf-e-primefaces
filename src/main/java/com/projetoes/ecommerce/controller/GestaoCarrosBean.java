package com.projetoes.ecommerce.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.projetoes.ecommerce.model.Carro;
import com.projetoes.ecommerce.model.DadosCadastroVo;
import com.projetoes.ecommerce.model.FiltroListarCarros;
import com.projetoes.ecommerce.model.HistoricoReservaCarro;
import com.projetoes.ecommerce.model.StatusCarro;
import com.projetoes.ecommerce.model.Usuario;
import com.projetoes.ecommerce.respository.CarroDAO;
import com.projetoes.ecommerce.respository.HistoricoReservaCarroDAO;
import com.projetoes.ecommerce.respository.UsuarioDAO;
import com.projetoes.ecommerce.service.CadastroCarroService;
import com.projetoes.ecommerce.service.HistoricoReservaCarrosService;
import com.projetoes.ecommerce.util.FacesMessages;

@Named
@SessionScoped
public class GestaoCarrosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CarroDAO carros;

	@Inject
	private UsuarioDAO usuarios;

	@Inject
	private HistoricoReservaCarroDAO historicoReservaCarros;

	@Inject
	private HistoricoReservaCarrosService historicosReservaCarrosService;

	@Inject
	private CadastroCarroService cadastroCarroService;
	
	@Inject
	private FacesMessages messages;

	private Carro carro;

	private StatusCarro verificaStatusCarro;

	private Usuario usuario;

	private List<Carro> listaCarros;

	private FiltroListarCarros filtros;

	public void index() {
		this.carro = new Carro();
		filtros = new FiltroListarCarros();
		filtros.setStatus(StatusCarro.Livre);
		listaCarros = carros.listarPorFiltros(filtros);

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Flash flash = facesContext.getExternalContext().getFlash();

		// Retrieve the message from the Flash scope
		String successMessage = (String) flash.get("successMessage");

		if (successMessage != null) {
			messages.info(successMessage);
		}
	}

	public String detalhes(Long id) {
		this.carro = carros.porId(id);
		this.usuario = new Usuario();
		return "detalheCarro?faces-redirect=true";
	}

	public String home() {
		return "home?faces-redirect=true";
	}

	public void pesquisar() {
		listaCarros = carros.listarPorFiltros(filtros);
	}

	public void todosCarros() {
		this.carro = new Carro();
		filtros = new FiltroListarCarros();
		listaCarros = carros.listarPorFiltros(filtros);
	}

	public void prepararNovoCarro() {
		carro = new Carro();
	}

	public void salvar() {
		boolean carroSalvo = false;

		try {
			DadosCadastroVo dadosCadastroVo = new DadosCadastroVo();
			dadosCadastroVo.setUsuarioIdCriacao(1);
			dadosCadastroVo.setUsuarioIdAtualizacao(1);
			dadosCadastroVo.setDataCriacao(new Date());
			dadosCadastroVo.setDataAtualizacao(new Date());

			carro.setStatus(StatusCarro.Livre);
			carro.setDadosCadastro(dadosCadastroVo);

			cadastroCarroService.salvar(carro);

			atualizarRegistros();

			carroSalvo = true;
			PrimeFaces.current().ajax().addCallbackParam("carroSalvo", carroSalvo);
			messages.info("Carro salvo com sucesso!");

			PrimeFaces.current().ajax().update(Arrays.asList("frm-carros:carrosDataTable", "frm-carros:messages"));

		} catch (Exception e) {
			PrimeFaces.current().ajax().addCallbackParam("carroSalvo", carroSalvo);
			messages.erro("Erro ao salvar carro!");
			e.printStackTrace();
		}
	}

	public void atualizar() {
		boolean carroAtualizado = false;

		try {
			DadosCadastroVo dadosCadastroVo = carro.getDadosCadastro();
			dadosCadastroVo.setUsuarioIdAtualizacao(1);
			dadosCadastroVo.setDataAtualizacao(new Date());
			carro.setDadosCadastro(dadosCadastroVo);

			cadastroCarroService.atualizar(carro);

			atualizarRegistros();

			carroAtualizado = true;
			PrimeFaces.current().ajax().addCallbackParam("carroAtualizado", carroAtualizado);
			messages.info("Carro atualizado com sucesso!");

			PrimeFaces.current().ajax().update(Arrays.asList("frm-carros:carrosDataTable", "frm-carros:messages"));

		} catch (Exception e) {
			PrimeFaces.current().ajax().addCallbackParam("carroAtualizado", carroAtualizado);
			messages.erro("Erro ao atualizar carro!");
			e.printStackTrace();
		}
	}

	public void excluir(Long id) {
		cadastroCarroService.excluir(id);

		carro = null;

		atualizarRegistros();

		messages.info("Carro excluído com sucesso!");
	}

	public void prepararEdicao(Long id) {
		carro = new Carro();
		this.carro = carros.porId(id);
	}

	private void atualizarRegistros() {
		if (filtroVazio()) {
			pesquisar();
		} else {
			todosCarros();
		}
	}

	private boolean filtroVazio() {
		return this.filtros.getMarca() != null || !"".equals(this.filtros.getMarca())
				|| this.filtros.getModelo() != null || !"".equals(this.filtros.getModelo())
				|| this.filtros.getStatus() != null || this.filtros.getValorFim() != 0
				|| this.filtros.getValorInicio() != 0 || this.filtros.getAnoFabricacaoInicio() != null
				|| this.filtros.getAnoFabricacaoFim() != null || this.filtros.getAnoModeloInicio() != null
				|| this.filtros.getAnoModeloInicio() != null;
	}

	public void reservarCarro() {
		usuario.setTelefone(usuario.getTelefone().replaceAll("\\D", ""));
		Usuario usuarioReserva = usuarios.buscarPorNomeETelefone(usuario.getNome(), usuario.getTelefone());

		if (usuarioReserva == null) {
			messages.erro("Usuario não encontrado!");
			this.usuario = new Usuario();
			return;
		}

		try {
			carro.setStatus(StatusCarro.Reservado);
			DadosCadastroVo dadosCadastroVo = carro.getDadosCadastro();
			dadosCadastroVo.setUsuarioIdAtualizacao(1);
			dadosCadastroVo.setDataAtualizacao(new Date());
			carro.setDadosCadastro(dadosCadastroVo);

			HistoricoReservaCarro historicoReservaCarro = new HistoricoReservaCarro();
			historicoReservaCarro.setUsuario(usuarioReserva);
			historicoReservaCarro.setCarro(this.carro);
			historicoReservaCarro.setDataReserva(new Date());

			historicosReservaCarrosService.salvar(historicoReservaCarro, this.carro);

			FacesContext facesContext = FacesContext.getCurrentInstance();
			Flash flash = facesContext.getExternalContext().getFlash();

			flash.put("successMessage", "Carro reservado com sucesso!");

			String redirectURL = facesContext.getExternalContext().getRequestContextPath() + "/home.xhtml";

			facesContext.getExternalContext().redirect(redirectURL);
		} catch (Exception e) {
			messages.erro("Erro ao reservar carro!");
			this.usuario = new Usuario();
			this.carro = new Carro();
			e.printStackTrace();
		}
	}

	public void liberarCarro(Long id) {
		try {

			Carro carroLiberar = carros.porId(id);

			if (carroLiberar == null)
				throw new Exception();

			HistoricoReservaCarro historico = historicoReservaCarros
					.buscarPorIdCarroEDataLiberacao(carroLiberar.getId(), null);

			if (historico == null)
				throw new Exception();

			DadosCadastroVo dadosCadastroVo = carroLiberar.getDadosCadastro();
			dadosCadastroVo.setUsuarioIdAtualizacao(1);
			dadosCadastroVo.setDataAtualizacao(new Date());

			carroLiberar.setStatus(StatusCarro.Livre);
			carroLiberar.setDadosCadastro(dadosCadastroVo);
			historico.setDataLiberacao(new Date());

			historicosReservaCarrosService.atualizar(historico, carroLiberar);

			atualizarRegistros();

			messages.info("Carro liberado com sucesso!");

			PrimeFaces.current().ajax().update(Arrays.asList("frm-carros:carrosDataTable", "frm-carros:messages"));

		} catch (Exception e) {
			messages.erro("Erro ao liberar carro!");
			this.carro = new Carro();
			e.printStackTrace();
		}
	}

	public List<Carro> getListaCarros() {
		return listaCarros;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public FiltroListarCarros getFiltros() {
		return filtros;
	}

	public void setFiltros(FiltroListarCarros filtros) {
		this.filtros = filtros;
	}

	public void setListaCarros(List<Carro> listaCarros) {
		this.listaCarros = listaCarros;
	}

	public StatusCarro[] getStatus() {
		return StatusCarro.values();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StatusCarro getVerificaStatusCarro() {
		return verificaStatusCarro;
	}
}

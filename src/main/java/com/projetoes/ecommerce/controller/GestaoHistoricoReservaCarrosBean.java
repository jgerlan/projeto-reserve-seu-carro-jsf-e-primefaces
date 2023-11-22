package com.projetoes.ecommerce.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.projetoes.ecommerce.model.DadosCadastroVo;
import com.projetoes.ecommerce.model.FiltroListarHistoricoReservaCarros;
import com.projetoes.ecommerce.model.HistoricoReservaCarro;
import com.projetoes.ecommerce.model.Usuario;
import com.projetoes.ecommerce.respository.HistoricoReservaCarroDAO;
import com.projetoes.ecommerce.service.HistoricoReservaCarrosService;
import com.projetoes.ecommerce.util.FacesMessages;
import com.projetoes.ecommerce.util.StringExtensions;

@Named
@SessionScoped
public class GestaoHistoricoReservaCarrosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private HistoricoReservaCarroDAO historicosReservaCarros;
	
	@Inject
	private HistoricoReservaCarrosService historicoReservaCarrosService;
	
	@Inject
	private StringExtensions stringExtensions;

	@Inject
	private FacesMessages messages;
	
	private HistoricoReservaCarro historicoReservaCarro;

	private List<HistoricoReservaCarro> listaHistoricoReserva;
	
	private Usuario usuario;

	private FiltroListarHistoricoReservaCarros filtros;
	
	public void index() {
		this.historicoReservaCarro = new HistoricoReservaCarro();
		filtros = new FiltroListarHistoricoReservaCarros();
		listaHistoricoReserva = historicosReservaCarros.listarPorFiltros(filtros);
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();

        // Retrieve the message from the Flash scope
        String successMessage = (String) flash.get("successMessage");

        if (successMessage != null) {
        	messages.info(successMessage);
        }
	}
	
	public String home() {
		return "home?faces-redirect=true";
	}

	public void pesquisar() {
		filtros.setTelefone(filtros.getTelefone().replaceAll("\\D", ""));
		this.listaHistoricoReserva = historicosReservaCarros.listarPorFiltros(filtros);
	}

	public void todosHistoricos() {
		this.historicoReservaCarro = new HistoricoReservaCarro();
		filtros = new FiltroListarHistoricoReservaCarros();
		listaHistoricoReserva = historicosReservaCarros.listarPorFiltros(filtros);
	}
	
	public void prepararExportacao() {
		this.historicoReservaCarro = new HistoricoReservaCarro();
		this.usuario = new Usuario();
		this.usuario.setDadosCadastro(new DadosCadastroVo());
	}
	
	public StreamedContent exportarPorUsuario() throws IOException {
		try {
			
			List<HistoricoReservaCarro> historico = 
					historicosReservaCarros.buscarPorNomeTelefoneDataCadastro(
							this.usuario.getNome(), this.usuario.getTelefone(), this.usuario.getDadosCadastro().getDataCriacao());
			
			if(historico == null || historico.isEmpty()) {
				messages.erro("Não foram encontrados históricos para esse usuário!");
				PrimeFaces.current().ajax()
				.update(Arrays.asList("frm-historico-reserva:messages"));
				return null;
			}
			
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        historicoReservaCarrosService.criarPDFPorListaHistoricoReservaCarro(historico, byteArrayOutputStream);
			
	        //Enviar pro front
	        StreamedContent file = DefaultStreamedContent.builder()
	                .name("historico.pdf")
	                .contentType("application/pdf")
	                .stream(() -> new ByteArrayInputStream(byteArrayOutputStream.toByteArray()))
	                .build();
	        return file;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public HistoricoReservaCarroDAO getHistoricosReservaCarros() {
		return historicosReservaCarros;
	}

	public void setHistoricosReservaCarros(HistoricoReservaCarroDAO historicosReservaCarros) {
		this.historicosReservaCarros = historicosReservaCarros;
	}

	public HistoricoReservaCarro getHistoricoReservaCarro() {
		return historicoReservaCarro;
	}

	public void setHistoricoReservaCarro(HistoricoReservaCarro historicoReservaCarro) {
		this.historicoReservaCarro = historicoReservaCarro;
	}

	public List<HistoricoReservaCarro> getListaHistoricoReserva() {
		return listaHistoricoReserva;
	}

	public void setListaHistoricoReserva(List<HistoricoReservaCarro> listaHistoricoReserva) {
		this.listaHistoricoReserva = listaHistoricoReserva;
	}

	public FiltroListarHistoricoReservaCarros getFiltros() {
		return filtros;
	}

	public void setFiltros(FiltroListarHistoricoReservaCarros filtros) {
		this.filtros = filtros;
	}

	public StringExtensions getStringExtensions() {
		return stringExtensions;
	}

	public void setStringExtensions(StringExtensions stringExtensions) {
		this.stringExtensions = stringExtensions;
	}
	
}

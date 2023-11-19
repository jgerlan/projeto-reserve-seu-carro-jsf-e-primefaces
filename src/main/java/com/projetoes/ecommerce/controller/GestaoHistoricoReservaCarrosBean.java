package com.projetoes.ecommerce.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoes.ecommerce.model.DadosCadastroVo;
import com.projetoes.ecommerce.model.FiltroListarHistoricoReservaCarros;
import com.projetoes.ecommerce.model.HistoricoReservaCarro;
import com.projetoes.ecommerce.model.Usuario;
import com.projetoes.ecommerce.respository.HistoricoReservaCarroDAO;
import com.projetoes.ecommerce.util.FacesMessages;

@Named
@SessionScoped
public class GestaoHistoricoReservaCarrosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private HistoricoReservaCarroDAO historicosReservaCarros;

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
	
	public String getFormattedTelefone(String telefone) {
        if (!telefone.isEmpty()) {
            // Remove non-digit characters
            String cleaned = telefone.replaceAll("\\D", "");

            // Apply the custom phone number format (99) 9 9999-9999
            return MessageFormat.format("({0}) {1} {2}-{3}",
                    cleaned.substring(0, 2),
                    cleaned.substring(2, 3),
                    cleaned.substring(3, 7),
                    cleaned.substring(7));
        }
        return "";
    }
	
	public void exportarPorUsuario() {
	    try {
	    	this.download();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void download() throws IOException {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
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
	
}

package com.projetoes.ecommerce.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import com.projetoes.ecommerce.model.FiltroListarHistoricoReservaCarros;
import com.projetoes.ecommerce.model.HistoricoReservaCarro;
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
		this.listaHistoricoReserva = historicosReservaCarros.listarPorFiltros(filtros);
	}

	public void todosHistoricos() {
		this.historicoReservaCarro = new HistoricoReservaCarro();
		filtros = new FiltroListarHistoricoReservaCarros();
		listaHistoricoReserva = historicosReservaCarros.listarPorFiltros(filtros);
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

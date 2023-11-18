package com.projetoes.ecommerce.model;

import java.io.Serializable;
import java.util.Date;

public class FiltroListarHistoricoReservaCarros implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Login;

	private double valorInicio;
	
	private double valorFim;

	private Date dataReservaInicio;
	
	private Date dataReservaFim;

	private Date dataLiberacaoInicio;
	
	private Date dataLiberacaoFim;

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}
	
	public double getValorInicio() {
		return valorInicio;
	}

	public void setValorInicio(double valorInicio) {
		this.valorInicio = valorInicio;
	}

	public double getValorFim() {
		return valorFim;
	}

	public void setValorFim(double valorFim) {
		this.valorFim = valorFim;
	}

	public Date getDataReservaInicio() {
		return dataReservaInicio;
	}

	public void setDataReservaInicio(Date dataReservaInicio) {
		this.dataReservaInicio = dataReservaInicio;
	}

	public Date getDataReservaFim() {
		return dataReservaFim;
	}

	public void setDataReservaFim(Date dataReservaFim) {
		this.dataReservaFim = dataReservaFim;
	}

	public Date getDataLiberacaoInicio() {
		return dataLiberacaoInicio;
	}

	public void setDataLiberacaoInicio(Date dataLiberacaoInicio) {
		this.dataLiberacaoInicio = dataLiberacaoInicio;
	}

	public Date getDataLiberacaoFim() {
		return dataLiberacaoFim;
	}

	public void setDataLiberacaoFim(Date dataLiberacaoFim) {
		this.dataLiberacaoFim = dataLiberacaoFim;
	}
	
}

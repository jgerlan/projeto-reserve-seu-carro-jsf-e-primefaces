package com.projetoes.ecommerce.model;

import java.io.Serializable;
import java.util.Date;

public class FiltroListarCarros implements Serializable {

	private static final long serialVersionUID = 1L;

	private String marca;
	
	private String modelo;
	
	private Date anoFabricacaoInicio;
	
	private Date anoFabricacaoFim;
	
	private String anoModeloInicio;
	
	private String anoModeloFim;
	
	private double valorInicio;
	
	private double valorFim;
	
	private StatusCarro status;
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Date getAnoFabricacaoInicio() {
		return anoFabricacaoInicio;
	}
	public void setAnoFabricacaoInicio(Date anoFabricacaoInicio) {
		this.anoFabricacaoInicio = anoFabricacaoInicio;
	}
	public Date getAnoFabricacaoFim() {
		return anoFabricacaoFim;
	}
	public void setAnoFabricacaoFim(Date anoFabricacaoFim) {
		this.anoFabricacaoFim = anoFabricacaoFim;
	}
	public String getAnoModeloInicio() {
		return anoModeloInicio;
	}
	public void setAnoModeloInicio(String anoModeloInicio) {
		this.anoModeloInicio = anoModeloInicio;
	}
	public String getAnoModeloFim() {
		return anoModeloFim;
	}
	public void setAnoModeloFim(String anoModeloFim) {
		this.anoModeloFim = anoModeloFim;
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
	public StatusCarro getStatus() {
		return status;
	}
	public void setStatus(StatusCarro status) {
		this.status = status;
	}

}

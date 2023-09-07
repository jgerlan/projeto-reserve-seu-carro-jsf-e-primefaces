package com.projetoes.ecommerce.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "carro")
public class Carro implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 50)
	private String marca;
	
	@Column(nullable = false, length = 50)
	private String modelo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ano_fabricacao")
	private Date anoFabricacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ano_modelo")
	private Date anoModelo;
	
	private double valor;
	
	@Column(nullable = true, length = 300)
	private String descricao;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
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
	
	public Date getAnoFabricacao() {
		return anoFabricacao;
	}
	
	public void setAnoFabricacao(Date anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	
	public Date getAnoModelo() {
		return anoModelo;
	}
	
	public void setAnoModelo(Date anoModelo) {
		this.anoModelo = anoModelo;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Carro [id=" + id + "]";
	}
	
}

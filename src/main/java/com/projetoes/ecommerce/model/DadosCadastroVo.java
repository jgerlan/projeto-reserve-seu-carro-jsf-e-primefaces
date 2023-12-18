package com.projetoes.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class DadosCadastroVo {
	public DadosCadastroVo()
	{ }
	
	@Column(nullable = false, name = "usuarioid_criacao")
	private long usuarioIdCriacao;
	
	@Column(nullable = false, name = "usuarioid_atualizacao")
	private long usuarioIdAtualizacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "data_criacao")
	private Date dataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "data_atualizacao")
	private Date dataAtualizacao;

	public long getUsuarioIdCriacao() {
		return usuarioIdCriacao;
	}

	public void setUsuarioIdCriacao(long usuarioIdCriacao) {
		this.usuarioIdCriacao = usuarioIdCriacao;
	}

	public long getUsuarioIdAtualizacao() {
		return usuarioIdAtualizacao;
	}

	public void setUsuarioIdAtualizacao(long usuarioIdAtualizacao) {
		this.usuarioIdAtualizacao = usuarioIdAtualizacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	
}

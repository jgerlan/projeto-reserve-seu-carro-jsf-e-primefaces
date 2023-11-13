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
	private long UsuarioIdCriacao;
	
	@Column(nullable = false, name = "usuarioid_atualizacao")
	private long UsuarioIdAtualizacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "data_criacao")
	private Date DataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "data_atualizacao")
	private Date DataAtualizacao;

	public long getUsuarioIdCriacao() {
		return UsuarioIdCriacao;
	}

	public void setUsuarioIdCriacao(long usuarioIdCriacao) {
		UsuarioIdCriacao = usuarioIdCriacao;
	}

	public long getUsuarioIdAtualizacao() {
		return UsuarioIdAtualizacao;
	}

	public void setUsuarioIdAtualizacao(long usuarioIdAtualizacao) {
		UsuarioIdAtualizacao = usuarioIdAtualizacao;
	}

	public Date getDataCriacao() {
		return DataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		DataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return DataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		DataAtualizacao = dataAtualizacao;
	}
	
	
}

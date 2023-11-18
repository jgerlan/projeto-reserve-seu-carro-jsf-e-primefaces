package com.projetoes.ecommerce.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "historico_reserva_carros")
public class HistoricoReservaCarro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "carro_id", nullable = false)
	private Carro carro;

	@Column(nullable = false)
	private String login;
	
	@Column(nullable = false, length = 30)
	private String telefone;

	@Column(nullable = false)
	private Double valor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_reserva", nullable = false)
	private Date dataReserva;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_liberacao")
	private Date dataLiberacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

	public Date getDataLiberacao() {
		return dataLiberacao;
	}

	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(carro, dataLiberacao, dataReserva, id, usuario, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoricoReservaCarro other = (HistoricoReservaCarro) obj;
		return Objects.equals(carro, other.carro) && Objects.equals(dataLiberacao, other.dataLiberacao)
				&& Objects.equals(dataReserva, other.dataReserva) && Objects.equals(id, other.id)
				&& Objects.equals(usuario, other.usuario) && Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "HistoricoReservaCarros [id=" + id + ", usuario=" + usuario + ", carro=" + carro + ", valor=" + valor
				+ ", dataReserva=" + dataReserva + ", dataLiberacao=" + dataLiberacao + "]";
	}

}

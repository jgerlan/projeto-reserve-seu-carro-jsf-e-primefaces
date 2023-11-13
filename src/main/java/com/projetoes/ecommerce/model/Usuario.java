package com.projetoes.ecommerce.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
		@Column(unique = true, nullable = false, length = 20)
		private String login;
		
		@Column(nullable = false, length = 15)
		private String senha;
		
		@Column(nullable = false, length = 30)
		private String nome;
		
		@NotNull
		@Enumerated(EnumType.STRING)
		@Column(nullable = false, length = 30)
		private StatusUsuario status;
		
		@Past
		@Temporal(TemporalType.DATE)
		@Column(name = "data_nascimento")
		private Date dataNasc;
		
		@NotNull
		@Enumerated(EnumType.STRING)
		@Column(nullable = false, length = 30)
		private TipoUsuario tipo;
		
		@Embedded
		DadosCadastroVo dadosCadastro;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public StatusUsuario getStatus() {
			return status;
		}

		public void setStatus(StatusUsuario status) {
			this.status = status;
		}

		public Date getDataNasc() {
			return dataNasc;
		}

		public void setDataNasc(Date dataNasc) {
			this.dataNasc = dataNasc;
		}
		
		public TipoUsuario getTipo() {
			return tipo;
		}

		public void setTipo(TipoUsuario tipo) {
			this.tipo = tipo;
		}

		public DadosCadastroVo getDadosCadastro() {
			return dadosCadastro;
		}

		public void setDadosCadastro(DadosCadastroVo dadosCadastro) {
			this.dadosCadastro = dadosCadastro;
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
			Usuario other = (Usuario) obj;
			return id == other.id;
		}

		@Override
		public String toString() {
			return "Usuario i d= " + id;
		}

}
		



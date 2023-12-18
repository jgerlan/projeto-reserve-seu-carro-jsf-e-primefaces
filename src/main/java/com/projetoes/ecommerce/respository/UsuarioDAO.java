package com.projetoes.ecommerce.respository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projetoes.ecommerce.model.FiltroListarUsuarios;
import com.projetoes.ecommerce.model.Usuario;
import com.projetoes.ecommerce.util.DateTimeExtensions;

public class UsuarioDAO extends RepositorioCRUD<Usuario, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DateTimeExtensions dtExtensions;

	@Inject
	public UsuarioDAO(EntityManager entityManager) {
		super(Usuario.class, entityManager);
	}

	public List<Usuario> pesquisar(String nome) {
		TypedQuery<Usuario> query = getEntityManager().createQuery("from Usuario where nome like ?1 ", Usuario.class);
		query.setParameter(1, "%" + nome + "%");

		return query.getResultList();
	}

	public Usuario buscarPorNomeETelefone(String nome, String telefone) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> usuarioRoot = criteriaQuery.from(Usuario.class);

		Predicate predicate = criteriaBuilder.conjunction();

		predicate = criteriaBuilder.and(predicate,
				criteriaBuilder.like(criteriaBuilder.lower(usuarioRoot.get("nome")), "%" + nome.toLowerCase() + "%"));

		predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(usuarioRoot.get("telefone"), telefone));

		criteriaQuery.where(predicate);

		try {
			return getEntityManager().createQuery(criteriaQuery).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Usuario buscarPorNomeTelefoneDataCadastro(String nome, String telefone, Date dataCadastro) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> entityRoot = criteriaQuery.from(Usuario.class);

		Predicate predicate = criteriaBuilder.conjunction();

		predicate = criteriaBuilder.and(predicate,
				criteriaBuilder.like(criteriaBuilder.lower(entityRoot.get("nome")), "%" + nome.toLowerCase() + "%"));

		String telefoneAux = telefone.replaceAll("\\D", "");
		predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(entityRoot.get("telefone"), telefoneAux));
		
		Date truncatedDataCadastro = dtExtensions.truncateToDay(dataCadastro);
		
		predicate = criteriaBuilder.and(predicate,
	            criteriaBuilder.greaterThanOrEqualTo(entityRoot.get("dadosCadastro").get("dataCriacao"), truncatedDataCadastro),
	            criteriaBuilder.lessThan(entityRoot.get("dadosCadastro").get("dataCriacao"),
	            		dtExtensions.addDays(truncatedDataCadastro, 1)));

		criteriaQuery.where(predicate);

		try {
			return getEntityManager().createQuery(criteriaQuery).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Usuario buscarPorLoginEDataNascimento(String login, Date dataNascimento) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> entityRoot = criteriaQuery.from(Usuario.class);

		Predicate predicate = criteriaBuilder.conjunction();

		predicate = criteriaBuilder.and(predicate,
				criteriaBuilder.like(criteriaBuilder.lower(entityRoot.get("login")), "%" + login.toLowerCase() + "%"));
		
		Date truncatedDataCadastro = dtExtensions.truncateToDay(dataNascimento);
		
		predicate = criteriaBuilder.and(predicate,
	            criteriaBuilder.greaterThanOrEqualTo(entityRoot.get("dataNasc"), truncatedDataCadastro),
	            criteriaBuilder.lessThan(entityRoot.get("dataNasc"),
	            		dtExtensions.addDays(truncatedDataCadastro, 1)));

		criteriaQuery.where(predicate);

		try {
			return getEntityManager().createQuery(criteriaQuery).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Usuario validarLoginESenha(String login, String senha) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> usuarioRoot = criteriaQuery.from(Usuario.class);

		Predicate predicate = criteriaBuilder.conjunction();

		predicate = criteriaBuilder.and(predicate,
				criteriaBuilder.like(criteriaBuilder.lower(usuarioRoot.get("login")), login.toLowerCase()));

		predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(usuarioRoot.get("senha"), senha));

		criteriaQuery.where(predicate);

		try {
			return getEntityManager().createQuery(criteriaQuery).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Usuario> listarPorFiltros(FiltroListarUsuarios filtro) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> usuarioRoot = criteriaQuery.from(Usuario.class);

		Predicate predicate = criteriaBuilder.conjunction();

		if (filtro.getLogin() != null && !filtro.getLogin().isEmpty()) {
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
					criteriaBuilder.lower(usuarioRoot.get("login")), "%" + filtro.getLogin().toLowerCase() + "%"));
		}

		if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
			predicate = criteriaBuilder.and(predicate, criteriaBuilder
					.like(criteriaBuilder.lower(usuarioRoot.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
		}

		if (filtro.getTelefone() != null && !filtro.getTelefone().isEmpty()) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.equal(usuarioRoot.get("telefone"), filtro.getTelefone()));
		}

		if (filtro.getStatus() != null) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.equal(usuarioRoot.get("status"), filtro.getStatus()));
		}

		if (filtro.getTipo() != null) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.equal(usuarioRoot.get("tipo"), filtro.getTipo()));
		}

		if (filtro.getDeDataNasc() != null) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.greaterThanOrEqualTo(usuarioRoot.get("dataNasc"), filtro.getDeDataNasc()));
		}

		if (filtro.getAteDataNasc() != null) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.lessThanOrEqualTo(usuarioRoot.get("dataNasc"), filtro.getAteDataNasc()));
		}

		criteriaQuery.where(predicate);

		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}
	
	// métodos adicionados:

	public boolean existeNomeUsuario(String nomeUsuario) {
		TypedQuery<Long> query = getEntityManager()
				.createQuery("select count(u) from Usuario u where u.nome = :nomeUsuario", Long.class);
		query.setParameter("nomeUsuario", nomeUsuario);
		return query.getSingleResult() > 0;
	}
	
	public Usuario autenticar(String username, String password) {
		TypedQuery<Usuario> query = getEntityManager().createQuery(
				"SELECT u FROM Usuario u WHERE u.login = :username AND u.senha = :password", Usuario.class);
		query.setParameter("username", username);
		query.setParameter("password", password);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			// Usuário não encontrado ou senha incorreta
			return null;
		}
	}
}

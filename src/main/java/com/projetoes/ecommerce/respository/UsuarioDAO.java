package com.projetoes.ecommerce.respository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projetoes.ecommerce.model.FiltroListarUsuarios;
import com.projetoes.ecommerce.model.Usuario;

public class UsuarioDAO extends RepositorioCRUD<Usuario, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	public UsuarioDAO(EntityManager entityManager) {
		super(Usuario.class, entityManager);
	}

	public List<Usuario> pesquisar(String nome) {
		TypedQuery<Usuario> query = getEntityManager().createQuery("from Usuario where nome like ?1 ", Usuario.class);
		query.setParameter(1, "%" + nome + "%");

		return query.getResultList();
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

		if (filtro.getStatus() != null) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.equal(usuarioRoot.get("status"), filtro.getStatus()));
		}

		if (filtro.getTipo() != null) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.equal(usuarioRoot.get("tipo"), filtro.getTipo()));
		}

		if (filtro.getDataNasc() != null) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.equal(usuarioRoot.get("data_nascimento"), filtro.getDataNasc()));
		}

		criteriaQuery.where(predicate);

		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}
}

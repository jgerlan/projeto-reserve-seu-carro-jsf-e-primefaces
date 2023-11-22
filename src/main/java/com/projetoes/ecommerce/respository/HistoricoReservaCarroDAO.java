package com.projetoes.ecommerce.respository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projetoes.ecommerce.model.FiltroListarHistoricoReservaCarros;
import com.projetoes.ecommerce.model.HistoricoReservaCarro;
import com.projetoes.ecommerce.util.DateTimeExtensions;

public class HistoricoReservaCarroDAO extends RepositorioCRUD<HistoricoReservaCarro, Long> {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DateTimeExtensions dtExtensions;

	@Inject
	public HistoricoReservaCarroDAO(EntityManager entityManager) {
		super(HistoricoReservaCarro.class, entityManager);
	}

	public HistoricoReservaCarro buscarPorIdCarroEDataLiberacao(Long id, Date dataLiberacao) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<HistoricoReservaCarro> criteriaQuery = criteriaBuilder.createQuery(HistoricoReservaCarro.class);
		Root<HistoricoReservaCarro> entityRoot = criteriaQuery.from(HistoricoReservaCarro.class);

		Predicate predicate = criteriaBuilder.conjunction();

		predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(entityRoot.get("carro").get("id"), id));

		if (dataLiberacao == null) {
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.isNull(entityRoot.get("dataLiberacao")));
		} else {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.equal(entityRoot.get("dataLiberacao"), dataLiberacao));
		}

		criteriaQuery.where(predicate);

		try {
			return getEntityManager().createQuery(criteriaQuery).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<HistoricoReservaCarro> listarPorUsuarioId(Long id){
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<HistoricoReservaCarro> criteriaQuery = criteriaBuilder.createQuery(HistoricoReservaCarro.class);
		Root<HistoricoReservaCarro> entityRoot = criteriaQuery.from(HistoricoReservaCarro.class);

		Predicate predicate = criteriaBuilder.conjunction();

		predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(entityRoot.get("usuario").get("id"), id));
		
		criteriaQuery.where(predicate);

		try {
			return getEntityManager().createQuery(criteriaQuery).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void excluirPorCarroId(Long carroId) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaDelete<HistoricoReservaCarro> delete = criteriaBuilder
				.createCriteriaDelete(HistoricoReservaCarro.class);
		Root<HistoricoReservaCarro> entityRoot = delete.from(HistoricoReservaCarro.class);

		delete.where(criteriaBuilder.equal(entityRoot.get("carro").get("id"), carroId));

		getEntityManager().createQuery(delete).executeUpdate();
	}
	
	public void excluirPorUsuarioId(Long usuarioId) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaDelete<HistoricoReservaCarro> delete = criteriaBuilder
				.createCriteriaDelete(HistoricoReservaCarro.class);
		Root<HistoricoReservaCarro> entityRoot = delete.from(HistoricoReservaCarro.class);

		delete.where(criteriaBuilder.equal(entityRoot.get("usuario").get("id"), usuarioId));

		getEntityManager().createQuery(delete).executeUpdate();
	}
	
	public List<HistoricoReservaCarro> buscarPorNomeTelefoneDataCadastro(String nome, String telefone, Date dataCadastro) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<HistoricoReservaCarro> criteriaQuery = criteriaBuilder.createQuery(HistoricoReservaCarro.class);
		Root<HistoricoReservaCarro> entityRoot = criteriaQuery.from(HistoricoReservaCarro.class);

		Predicate predicate = criteriaBuilder.conjunction();

		predicate = criteriaBuilder.and(predicate,
				criteriaBuilder.like(criteriaBuilder.lower(entityRoot.get("usuario").get("nome")), "%" + nome.toLowerCase() + "%"));

		String telefoneAux = telefone.replaceAll("\\D", "");
		predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(entityRoot.get("usuario").get("telefone"), telefoneAux));
		
		Date truncatedDataCadastro = dtExtensions.truncateToDay(dataCadastro);
		
		predicate = criteriaBuilder.and(predicate,
	            criteriaBuilder.greaterThanOrEqualTo(entityRoot.get("usuario").get("dadosCadastro").get("dataCriacao"), truncatedDataCadastro),
	            criteriaBuilder.lessThan(entityRoot.get("usuario").get("dadosCadastro").get("dataCriacao"),
	            		dtExtensions.addDays(truncatedDataCadastro, 1)));

		criteriaQuery.where(predicate);

		try {
			return getEntityManager().createQuery(criteriaQuery).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<HistoricoReservaCarro> listarPorFiltros(FiltroListarHistoricoReservaCarros filtro) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<HistoricoReservaCarro> criteriaQuery = criteriaBuilder.createQuery(HistoricoReservaCarro.class);
		Root<HistoricoReservaCarro> entityRoot = criteriaQuery.from(HistoricoReservaCarro.class);

		Predicate predicate = criteriaBuilder.conjunction();

		if (filtro.getLogin() != null && !filtro.getLogin().isEmpty()) {
			predicate = criteriaBuilder.and(predicate, criteriaBuilder
					.like(criteriaBuilder.lower(entityRoot.get("usuario").get("login")), "%" + filtro.getLogin().toLowerCase() + "%"));
		}

		if (filtro.getTelefone() != null && !filtro.getTelefone().isEmpty()) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.equal(entityRoot.get("usuario").get("telefone"), filtro.getTelefone()));
		}

		if (filtro.getDataReservaInicio() != null) {

			try {
				predicate = criteriaBuilder.and(predicate, criteriaBuilder
						.greaterThanOrEqualTo(entityRoot.get("dataReserva"), filtro.getDataReservaInicio()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (filtro.getDataReservaFim() != null) {

			try {
				predicate = criteriaBuilder.and(predicate,
						criteriaBuilder.lessThanOrEqualTo(entityRoot.get("dataReserva"), filtro.getDataReservaFim()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (filtro.getDataLiberacaoInicio() != null) {

			try {
				predicate = criteriaBuilder.and(predicate, criteriaBuilder
						.greaterThanOrEqualTo(entityRoot.get("dataLiberacao"), filtro.getDataLiberacaoInicio()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (filtro.getDataLiberacaoFim() != null) {

			try {
				predicate = criteriaBuilder.and(predicate, criteriaBuilder
						.lessThanOrEqualTo(entityRoot.get("dataLiberacao"), filtro.getDataLiberacaoFim()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (filtro.getValorInicio() > 0) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.greaterThanOrEqualTo(entityRoot.get("carro").get("valor"), filtro.getValorInicio()));
		}

		if (filtro.getValorFim() > 0) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.lessThanOrEqualTo(entityRoot.get("carro").get("valor"), filtro.getValorFim()));
		}

		criteriaQuery.where(predicate);

		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}
}

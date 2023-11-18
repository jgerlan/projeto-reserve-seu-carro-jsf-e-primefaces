package com.projetoes.ecommerce.respository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projetoes.ecommerce.model.FiltroListarHistoricoReservaCarros;
import com.projetoes.ecommerce.model.HistoricoReservaCarro;

public class HistoricoReservaCarroDAO extends RepositorioCRUD<HistoricoReservaCarro, Long> {
	private static final long serialVersionUID = 1L;

	@Inject
	public HistoricoReservaCarroDAO(EntityManager entityManager) {
		super(HistoricoReservaCarro.class, entityManager);
	}

	public List<HistoricoReservaCarro> listarPorFiltros(FiltroListarHistoricoReservaCarros filtro) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<HistoricoReservaCarro> criteriaQuery = criteriaBuilder.createQuery(HistoricoReservaCarro.class);
		Root<HistoricoReservaCarro> entityRoot = criteriaQuery.from(HistoricoReservaCarro.class);

		Predicate predicate = criteriaBuilder.conjunction();

		if (filtro.getLogin() != null && !filtro.getLogin().isEmpty()) {
			predicate = criteriaBuilder.and(predicate, criteriaBuilder
					.like(criteriaBuilder.lower(entityRoot.get("login")), "%" + filtro.getLogin().toLowerCase() + "%"));
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
				predicate = criteriaBuilder.and(predicate,
						criteriaBuilder.lessThanOrEqualTo(entityRoot.get("dataLiberacao"), filtro.getDataLiberacaoFim()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (filtro.getValorInicio() > 0) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.greaterThanOrEqualTo(entityRoot.get("valor"), filtro.getValorInicio()));
		}

		if (filtro.getValorFim() > 0) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.lessThanOrEqualTo(entityRoot.get("valor"), filtro.getValorFim()));
		}
		
		criteriaQuery.where(predicate);

		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}
}

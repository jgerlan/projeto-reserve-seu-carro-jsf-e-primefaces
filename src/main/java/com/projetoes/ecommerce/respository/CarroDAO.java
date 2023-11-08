package com.projetoes.ecommerce.respository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projetoes.ecommerce.model.Carro;
import com.projetoes.ecommerce.model.FiltroListarCarros;

public class CarroDAO extends RepositorioCRUD<Carro, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	public CarroDAO(EntityManager entityManager) {
		super(Carro.class, entityManager);
	}

	public List<Carro> pesquisar(String marca) {
		TypedQuery<Carro> query = getEntityManager().createQuery("from Carro where LOWER(marca) like ?1 ", Carro.class);
		query.setParameter(1, "%" + marca.toLowerCase() + "%");

		return query.getResultList();
	}

	public List<Carro> listarPorFiltros(FiltroListarCarros filtro) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = criteriaBuilder.createQuery(Carro.class);
		Root<Carro> carroRoot = criteriaQuery.from(Carro.class);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

		Predicate predicate = criteriaBuilder.conjunction();

		if (filtro.getMarca() != null && !filtro.getMarca().isEmpty()) {
			predicate = criteriaBuilder.and(predicate, criteriaBuilder
					.like(criteriaBuilder.lower(carroRoot.get("marca")), "%" + filtro.getMarca().toLowerCase() + "%"));
		}

		if (filtro.getModelo() != null && !filtro.getModelo().isEmpty()) {
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
					criteriaBuilder.lower(carroRoot.get("modelo")), "%" + filtro.getModelo().toLowerCase() + "%"));
		}

		if (filtro.getAnoModeloInicio() != null && !filtro.getAnoModeloInicio().isEmpty()) {
			
			try {
				Date anoModeloInicio = sdf.parse(filtro.getAnoModeloInicio());
				predicate = criteriaBuilder.and(predicate, criteriaBuilder
						.greaterThanOrEqualTo(carroRoot.get("anoModelo"), anoModeloInicio));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (filtro.getAnoModeloFim() != null && !filtro.getAnoModeloFim().isEmpty()) {
			
			try {
				Date anoModeloFim = sdf.parse(filtro.getAnoModeloFim());
				predicate = criteriaBuilder.and(predicate, criteriaBuilder
						.lessThanOrEqualTo(carroRoot.get("anoModelo"), anoModeloFim));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (filtro.getValorInicio() > 0) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.greaterThanOrEqualTo(carroRoot.get("valor"), filtro.getValorInicio()));
		}

		if (filtro.getValorFim() > 0) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.lessThanOrEqualTo(carroRoot.get("valor"), filtro.getValorFim()));
		}

		if (filtro.getStatus() != null) {
			predicate = criteriaBuilder.and(predicate, criteriaBuilder
					.equal(carroRoot.get("status"), filtro.getStatus()));
		}

		criteriaQuery.where(predicate);

		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}
}

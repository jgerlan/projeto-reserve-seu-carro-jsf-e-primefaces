package com.projetoes.ecommerce.respository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.projetoes.ecommerce.model.Carro;

public class Carros extends RepositorioCRUD<Carro, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	public Carros(EntityManager entityManager) {
		super(Carro.class, entityManager);
	}
	
	public List<Carro> pesquisar(String marca) {
		TypedQuery<Carro> query = getEntityManager().createQuery("from Carro where LOWER(marca) like ?1 ", Carro.class);
		query.setParameter(1, "%" + marca.toLowerCase() + "%");
		
		return query.getResultList();
	}
}

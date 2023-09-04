package com.projetoes.ecommerce.respository;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.projetoes.ecommerce.model.Carro;

public class Carros extends RepositorioCRUD<Carro, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	public Carros(EntityManager entityManager) {
		super(Carro.class, entityManager);
	}
}

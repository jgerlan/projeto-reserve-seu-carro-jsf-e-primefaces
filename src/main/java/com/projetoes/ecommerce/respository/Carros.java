package com.projetoes.ecommerce.respository;

import javax.persistence.EntityManager;

import com.projetoes.ecommerce.model.Carro;

public class Carros extends RepositorioCRUD<Carro, Long> {

	private static final long serialVersionUID = 1L;
	
	public Carros(EntityManager entityManager) {
		super(Carro.class, entityManager);
	}

}

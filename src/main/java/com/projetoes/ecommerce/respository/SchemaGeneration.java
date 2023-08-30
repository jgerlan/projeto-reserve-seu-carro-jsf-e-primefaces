package com.projetoes.ecommerce.respository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.projetoes.ecommerce.model.Carro;

public class SchemaGeneration {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoesPU");

		EntityManager em = emf.createEntityManager();

		List<Carro> lista = em.createQuery("from Carro", Carro.class).getResultList();

		System.out.println(lista);
		
		em.close();
		emf.close();
	}
}

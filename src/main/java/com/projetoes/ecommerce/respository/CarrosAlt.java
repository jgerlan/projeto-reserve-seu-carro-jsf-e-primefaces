package com.projetoes.ecommerce.respository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.projetoes.ecommerce.model.Carro;

public class CarrosAlt implements IRepositorioCRUD<Carro, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public CarrosAlt() {
	}

	public CarrosAlt(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Carro porId(Long id) {
		return manager.find(Carro.class, id);
	}

	public List<Carro> pesquisar(String modelo) {
		TypedQuery<Carro> query = manager.createQuery("from Carro where modelo like ?1 ", Carro.class);
		query.setParameter(1, "%" + modelo + "%");
		return query.getResultList();
	}

	@Override
	public Carro guardar(Carro carro) {
		return manager.merge(carro);
	}

	@Override
	public void remover(Long id) {
		Carro carro = porId(id);
		manager.remove(carro);
	}

	@Override
	public List<Carro> listarTodos() {
		TypedQuery<Carro> query = manager.createQuery("SELECT e FROM " + Carro.class.getSimpleName() + " e",
				Carro.class);
		return query.getResultList();
	}

}


package com.projetoes.ecommerce.respository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public abstract class RepositorioCRUD<T, ID> implements IRepositorioCRUD<T, ID> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	private final Class<T> entityClass;

	public RepositorioCRUD(Class<T> entityClass, EntityManager manager) {
		this.manager = manager;
		this.entityClass = entityClass;
	}

	@Override
	public T porId(ID id) {
		return manager.find(entityClass, id);
	}

	@Override
	public List<T> listarTodos() {
		TypedQuery<T> query = manager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass);
		return query.getResultList();
	}

	@Override
	public List<T> pesquisar(String atributo) {
		TypedQuery<T> query = manager.createQuery("from Carro where modelo like ?1 ", entityClass);
		query.setParameter(1, "%" + atributo + "%");
		return query.getResultList();
	}

	@Override
	public T guardar(T entity) {
		return manager.merge(entity);
	}

	@Override
	public void remover(ID id) {
		T entity = manager.find(entityClass, id);
		if (entity != null) {
			manager.remove(entity);
		}
	}

}

package com.projetoes.ecommerce.respository;

import java.io.Serializable;
import java.util.List;

public interface IRepositorioCRUD<T, ID> extends Serializable {

	public T porId(ID id);
	
	public List<T> listarTodos();

	public List<T> pesquisar(String atributo);

	public T guardar(T entity);

	public void remover(ID id);

}

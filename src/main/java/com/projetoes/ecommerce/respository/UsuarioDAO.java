package com.projetoes.ecommerce.respository;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.projetoes.ecommerce.model.Usuario;
	
public class UsuarioDAO extends RepositorioCRUD<Usuario, Long> {

	private static final long serialVersionUID = 1L;
		
	@Inject
	public UsuarioDAO(EntityManager entityManager) {
		super(Usuario.class, entityManager);
	}
		
	public List<Usuario> pesquisar(String marca) {
	TypedQuery<Usuario> query = getEntityManager().createQuery("from Usuario where marca like ?1 ", Usuario.class);
	query.setParameter(1, "%" + marca + "%");
			
	return query.getResultList();
	}
}

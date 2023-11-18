package com.projetoes.ecommerce.respository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projetoes.ecommerce.model.FiltroListarUsuarios;
import com.projetoes.ecommerce.model.Usuario;

public class UsuarioDAO extends RepositorioCRUD<Usuario, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	public UsuarioDAO(EntityManager entityManager) {
		super(Usuario.class, entityManager);
	}

	public List<Usuario> pesquisar(String nome) {
		TypedQuery<Usuario> query = getEntityManager().createQuery("from Usuario where nome like ?1 ", Usuario.class);
		query.setParameter(1, "%" + nome + "%");

		return query.getResultList();
	}

	public Usuario buscarPorNomeEDataNascimento(String nome, Date dataNascimento) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> usuarioRoot = criteriaQuery.from(Usuario.class);

		Predicate predicate = criteriaBuilder.conjunction();

		predicate = criteriaBuilder.and(predicate,
				criteriaBuilder.like(criteriaBuilder.lower(usuarioRoot.get("nome")), "%" + nome.toLowerCase() + "%"));

		predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(usuarioRoot.get("dataNasc"), dataNascimento));

		criteriaQuery.where(predicate);

		try {
			return getEntityManager().createQuery(criteriaQuery).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Usuario> listarPorFiltros(FiltroListarUsuarios filtro) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> usuarioRoot = criteriaQuery.from(Usuario.class);

		Predicate predicate = criteriaBuilder.conjunction();

		if (filtro.getLogin() != null && !filtro.getLogin().isEmpty()) {
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
					criteriaBuilder.lower(usuarioRoot.get("login")), "%" + filtro.getLogin().toLowerCase() + "%"));
		}

		if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
			predicate = criteriaBuilder.and(predicate, criteriaBuilder
					.like(criteriaBuilder.lower(usuarioRoot.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
		}

		if (filtro.getStatus() != null) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.equal(usuarioRoot.get("status"), filtro.getStatus()));
		}

		if (filtro.getTipo() != null) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.equal(usuarioRoot.get("tipo"), filtro.getTipo()));
		}

		if (filtro.getDeDataNasc() != null) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.greaterThanOrEqualTo(usuarioRoot.get("dataNasc"), filtro.getDeDataNasc()));
		}

		if (filtro.getAteDataNasc() != null) {
			predicate = criteriaBuilder.and(predicate,
					criteriaBuilder.lessThanOrEqualTo(usuarioRoot.get("dataNasc"), filtro.getAteDataNasc()));
		}

		criteriaQuery.where(predicate);

		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}
	
	// métodos adicionados:

    public boolean existeNomeUsuario(String nomeUsuario) {
        TypedQuery<Long> query = getEntityManager()
                .createQuery("select count(u) from Usuario u where u.nome = :nomeUsuario", Long.class);
        query.setParameter("nomeUsuario", nomeUsuario);
        return query.getSingleResult() > 0;
    }

    public void salvar(Usuario usuario) {
        if (usuario.getId() == 0) {
            // Se o ID for 0, é um novo usuário, então persiste
            getEntityManager().persist(usuario);
        } else {
            // Se o ID não for 0, é um usuário existente
            getEntityManager().merge(usuario);
        }
    }
    
    public Usuario autenticar(String username, String password) {
        TypedQuery<Usuario> query = getEntityManager()
                .createQuery("SELECT u FROM Usuario u WHERE u.login = :username AND u.senha = :password", Usuario.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Usuário não encontrado ou senha incorreta
            return null;
        }
    }
}

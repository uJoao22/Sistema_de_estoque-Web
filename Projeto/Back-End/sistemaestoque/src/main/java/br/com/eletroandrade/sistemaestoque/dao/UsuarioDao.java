package br.com.eletroandrade.sistemaestoque.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.eletroandrade.sistemaestoque.model.Usuario;
import br.com.eletroandrade.sistemaestoque.util.Dao;
import br.com.eletroandrade.sistemaestoque.util.Repository;

public class UsuarioDao extends Dao<Usuario> {
	private static final long serialVersionUID = 1L;

	@Inject
	private Repository<Usuario, Long> repository;
	
	@Override
	@Transactional
	public void criar(Usuario obj) {
		repository.persistAndFlush(obj);
	}

	@Override
	@Transactional
	public void atualizar(Usuario obj) {
		repository.mergeAndFlush(obj);
	}

	@Override
	@Transactional
	public void remover(Long obj) {
		Usuario usuario = localizar(obj);
		if (usuario == null)
			throw new RuntimeException("Usuário não encontrado para exclusão");
		
		repository.remove(usuario);
	}

	@Override
	public Usuario localizar(Object obj) {
		Usuario usuario = em.find(Usuario.class, obj);
		if (usuario == null)
			throw new RuntimeException("Usuário não encontrado.");
			
		return usuario;
	}

	@Override
	public List<Usuario> listAll() {
		String jpql = "SELECT u FROM Usuario u";
		return em.createQuery(jpql, Usuario.class).getResultList();
	}
	
	public Usuario authUser(String email, String senha) {
		Query qry =  em.createQuery("select u from Usuario u "
				+ " where u.email = :email "
				+ "  and u.senha = :senha ")
				.setParameter("email", email)
				.setParameter("senha", senha);
		
		@SuppressWarnings("unchecked")
		List<Usuario> list = qry.getResultList();
		
		if(list.isEmpty())
			return null;
		
		return list.get(0);
	}

}

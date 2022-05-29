package br.com.eletroandrade.sistemaestoque.dao;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.eletroandrade.sistemaestoque.model.Categoria;
import br.com.eletroandrade.sistemaestoque.util.Dao;
import br.com.eletroandrade.sistemaestoque.util.Repository;

public class CategoriaDao extends Dao<Categoria> {
	private static final long serialVersionUID = 1L;

	@Inject
	private Repository<Categoria, Long> repository;
	
	@Override
	@Transactional
	public void criar(Categoria obj) {
		repository.persistAndFlush(obj);
	}

	@Override
	@Transactional
	public void atualizar(Categoria obj) {
		repository.mergeAndFlush(obj);
	}

	@Override
	@Transactional
	public void remover(Long obj) {
		Categoria categoria = localizar(obj);
		if(categoria == null)
			throw new RuntimeException("Categoria não encontrada para a exclusão");
		
		repository.remove(categoria);
	}

	@Override
	public Categoria localizar(Object obj) {
		Categoria categoria = em.find(Categoria.class, obj);
		if(categoria == null)
			throw new RuntimeException("Categoria não encontrada.");
		
		return categoria;
	}

	@Override
	public List<Categoria> listAll() {
		String jpql = "SELECT c FROM Categoria c";
		return em.createQuery(jpql, Categoria.class).getResultList();
	}

}

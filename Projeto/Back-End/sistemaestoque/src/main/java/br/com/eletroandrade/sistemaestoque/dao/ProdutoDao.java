package br.com.eletroandrade.sistemaestoque.dao;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.eletroandrade.sistemaestoque.model.Produto;
import br.com.eletroandrade.sistemaestoque.util.Dao;
import br.com.eletroandrade.sistemaestoque.util.Repository;

public class ProdutoDao extends Dao<Produto> {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Repository<Produto, Long> repository;

	@Override
	@Transactional
	public void criar(Produto obj) {
		repository.persistAndFlush(obj);
	}

	@Override
	@Transactional
	public void atualizar(Produto obj) {
		repository.mergeAndFlush(obj);
	}

	@Override
	@Transactional
	public void remover(Long obj) {
		Produto produto = localizar(obj);
		if(produto == null)
			throw new RuntimeException("Produto não encontrado para a exclusão");
		
		repository.remove(produto);
	}

	@Override
	public Produto localizar(Object obj) {
		Produto produto = em.find(Produto.class, obj);
		if(produto == null)
			throw new RuntimeException("Produto não encontrado.");
		
		return produto;
	}
	
	@Override
	public List<Produto> listAll(){
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
}

package br.com.eletroandrade.dao;

import java.util.List;

import javax.transaction.Transactional;

import br.com.eletroandrade.model.Produto;
import br.com.eletroandrade.util.Dao;

public class ProdutoDao extends Dao<Produto> {
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional
	public void criar(Produto obj) {
		em.getTransaction().begin(); //Inicia transação
		
		em.persist(obj); //persistindo/inserindo
		em.flush(); //Salvar alteração
		
		em.getTransaction().commit(); //Efetuar a alteração no banco
		em.close(); //Fechar a transação
	}

	@Override
	public void atualizar(Produto obj) {
		
	}

	@Override
	public void remover(Long obj) {
		
	}

	@Override
	public Produto localizar(Object obj) {
		return em.find(Produto.class, obj);
	}
	
	@Override
	public List<Produto> listAll(){
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
}

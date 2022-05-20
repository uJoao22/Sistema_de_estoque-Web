package br.com.eletroandrade.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;

import br.com.eletroandrade.model.Produto;
import br.com.eletroandrade.util.JPAUtil;

@RequestScoped
public class ProdutoDao {
	
	private EntityManager em = JPAUtil.getEntityManager();

	public List<Produto> listAll(){
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public Produto findProdutoById(Long id) {
		return em.find(Produto.class, id);
	}
}

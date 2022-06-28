package br.com.eletroandrade.sistemaestoque.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import br.com.eletroandrade.sistemaestoque.action.VendaProduto;
import br.com.eletroandrade.sistemaestoque.model.Venda;
import br.com.eletroandrade.sistemaestoque.util.Dao;
import br.com.eletroandrade.sistemaestoque.util.JPAUtil;
import br.com.eletroandrade.sistemaestoque.util.Repository;

public class VendaDao extends Dao<Venda> {
    private static final long serialVersionUID = 1L;

	private EntityManager em = JPAUtil.getEntityManager();

    @Inject
    private Repository<Venda, Long> repository;
    
    @Override
    @Transactional
    public void criar(Venda obj) {
    	obj.setDataVenda(new Date());

		em.getTransaction().begin();
    	for(VendaProduto pv : obj.getProdutos()) {
    		pv.getProduto().setQuantidade(pv.getProduto().getQuantidade() - pv.getQuantidade());
    		em.merge(pv.getProduto());
    		em.flush();
    	}

		em.getTransaction().commit();
		em.close();
    	
    	
        repository.persistAndFlush(obj);
    }

    @Override
    @Transactional
    public void atualizar(Venda obj) {
        repository.mergeAndFlush(obj);
    }

    @Override
    @Transactional
    public void remover(Long obj) {
        Venda venda = localizar(obj);
        if(venda == null)
            throw new RuntimeException("Venda não encontrada para a exclusão");

        repository.remove(venda);
    }

    @Override
    public Venda localizar(Object obj) {
        Venda venda = em.find(Venda.class, obj);
        if(venda == null)
            throw new RuntimeException("Venda não encontrada.");

        return venda;
    }

    @Override
    public List<Venda> listAll() {
        String jpql = "SELECT v FROM Venda v";
        return em.createQuery(jpql, Venda.class).getResultList();
    }

}

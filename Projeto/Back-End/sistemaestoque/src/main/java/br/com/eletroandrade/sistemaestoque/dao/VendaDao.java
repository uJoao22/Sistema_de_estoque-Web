package br.com.eletroandrade.sistemaestoque.dao;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.eletroandrade.sistemaestoque.model.Venda;
import br.com.eletroandrade.sistemaestoque.util.Dao;
import br.com.eletroandrade.sistemaestoque.util.Repository;

public class VendaDao extends Dao<Venda> {
    private static final long serialVersionUID = 1L;

    @Inject
    private Repository<Venda, Long> repository;

    @Override
    @Transactional
    public void criar(Venda obj) {
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

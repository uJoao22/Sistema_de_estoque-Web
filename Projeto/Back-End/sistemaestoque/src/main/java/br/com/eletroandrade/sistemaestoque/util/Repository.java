package br.com.eletroandrade.sistemaestoque.util;

import java.io.Serializable;

import javax.persistence.EntityManager;

public class Repository<E, T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = JPAUtil.getEntityManager();
	
    public E persist(E entity) {
        em.persist(entity);
        return entity;
    }
    
    public E merge(E entity) {
        em.merge(entity);
        return entity;
    }
    
    public void flush() {
        em.flush();
    }

    public E persistAndFlush(E entity) {
		em.getTransaction().begin();
		
        final E savedEntity = this.persist(entity);
        this.flush();
        
		em.getTransaction().commit();
		em.close();

        return savedEntity;
    }
    
    public E mergeAndFlush(E entity) {
		em.getTransaction().begin();
		
        final E savedEntity = this.merge(entity);
        this.flush();
        
		em.getTransaction().commit();
		em.close();

        return savedEntity;
    }
	
    public void remove(E entity) {
		em.getTransaction().begin();
		
		entity = em.merge(entity);
        em.remove(entity);
        
		em.getTransaction().commit();
		em.close();
    }
}

package br.com.eletroandrade.sistemaestoque.util;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public abstract class Dao<T> implements Serializable {
	protected EntityManager em = JPAUtil.getEntityManager();
	
	private static final long serialVersionUID = -7507235250477480071L;
	public abstract void criar(T obj);
	public abstract void atualizar(T obj);
	public abstract void remover(Long obj);
	public abstract T localizar(Object obj);
	public abstract List<T> listAll();
}

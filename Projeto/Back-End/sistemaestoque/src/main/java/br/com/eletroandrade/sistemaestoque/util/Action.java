package br.com.eletroandrade.sistemaestoque.util;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.Response;

public abstract class Action<E, T> implements Serializable {
	private static final long serialVersionUID = 1373522602960492209L;
	
	protected Response buildResponse(E obj){
		return Response.ok(obj).build();
	}
	
	protected Response buildId() {
		return Response.ok().build();
	}

	public abstract Response update(E item);
	public abstract Response remove(T id);
	public abstract Response cria(E item);
	public abstract E localiza(T id);
	public abstract List<E> listAll();
}

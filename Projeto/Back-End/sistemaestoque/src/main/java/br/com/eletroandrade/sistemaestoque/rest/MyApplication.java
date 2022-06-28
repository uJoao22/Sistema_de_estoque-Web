package br.com.eletroandrade.sistemaestoque.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.com.eletroandrade.sistemaestoque.action.CategoriaAction;
import br.com.eletroandrade.sistemaestoque.action.ProdutoAction;
import br.com.eletroandrade.sistemaestoque.action.UsuarioAction;
import br.com.eletroandrade.sistemaestoque.action.VendaAction;

public class MyApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();

		  classes.add(ProdutoAction.class);
		  classes.add(UsuarioAction.class);
		  classes.add(CategoriaAction.class);
		  classes.add(VendaAction.class);
	
		  return classes;
	  }
}

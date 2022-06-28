package br.com.eletroandrade.sistemaestoque.action;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.eletroandrade.sistemaestoque.dao.CategoriaDao;
import br.com.eletroandrade.sistemaestoque.model.Categoria;
import br.com.eletroandrade.sistemaestoque.util.Action;

@Path("categorias")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CategoriaAction extends Action<Categoria, Long>{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaDao dao;
	
	@PUT
	@Override
	public Response update(Categoria item) {
		dao.atualizar(item);
		return buildResponse(item);
	}
	
	@DELETE
	@Override
	@Path("{id}")
	public Response remove(@PathParam("id") Long id) {
		dao.remover(id);
		return buildId();
	}

	@POST
	@Override
	public Response cria(Categoria item) {
		dao.criar(item);
		return buildId();
	}

	@GET
	@Override
	@Path("{id}")
	public Categoria localiza(@PathParam("id") Long id) {
		return dao.localizar(id);
	}

	@GET
	@Override
	public List<Categoria> listAll() {
		return dao.listAll();
	}

}

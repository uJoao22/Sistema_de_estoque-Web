package br.com.eletroandrade.sistemaestoque.action;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.eletroandrade.sistemaestoque.dao.ProdutoDao;
import br.com.eletroandrade.sistemaestoque.model.Produto;
import br.com.eletroandrade.sistemaestoque.util.Action;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ProdutoAction extends Action<Produto, Long> {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoDao dao;
	
	@GET
	@Override
	public List<Produto> listAll() {
		return dao.listAll();
	}

	@PUT
	@Override
	public Response update(Produto item) {
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
	public Response cria(Produto item) {
		dao.criar(item);
		return buildResponse(item);
	}
	
	@GET
	@Override
	@Path("{id}")
	public Produto localiza(@PathParam("id") Long id) {
		return dao.localizar(id);
	}
}

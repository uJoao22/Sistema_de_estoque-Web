package br.com.eletroandrade.action;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.eletroandrade.dao.ProdutoDao;
import br.com.eletroandrade.model.Produto;
import br.com.eletroandrade.util.Action;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
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

	@Override
	public Response update(Long id, Produto item) {
		return null;
	}

	@DELETE
	@Override
	@Path("{id}")
	public Response remove(@PathParam("id") Long id) {
		dao.remover(id);
		return buildResponse(null);
	}

	@POST
	@Override
	public Response cria(Produto item) {
		dao.criar(item);
		return buildId();
	}
	
	@GET
	@Override
	@Path("{id}")
	public Produto localiza(@PathParam("id") Long id) {
		return dao.localizar(id);
	}
}

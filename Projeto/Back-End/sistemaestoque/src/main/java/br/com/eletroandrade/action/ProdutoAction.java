package br.com.eletroandrade.action;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.eletroandrade.dao.ProdutoDao;
import br.com.eletroandrade.model.Produto;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ProdutoAction {
	@Inject
	private ProdutoDao produtoDao;
	
	private Response buildResponse(Produto produto){
		return Response.ok(produto).build();
	}


	@GET
	public List<Produto> listProdutos() {
		return produtoDao.listAll();
	}
	
	@GET
	@Path("{id}")
	public Response findProdutoById(@PathParam("id") Long id) {
		return buildResponse(produtoDao.findProdutoById(id));
	}
}

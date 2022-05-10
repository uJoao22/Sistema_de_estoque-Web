package br.com.eletroandrade.action;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("produtos")
@Produces("application/json;charset=UTF-8")
@Consumes("application/json;charset=UTF-8")
public class ProdutoAction {

	@GET
	public Response listProdutos() {
		return null;
	}
}

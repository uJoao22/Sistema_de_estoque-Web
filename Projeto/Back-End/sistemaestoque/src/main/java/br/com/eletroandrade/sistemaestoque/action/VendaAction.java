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

import br.com.eletroandrade.sistemaestoque.dao.VendaDao;
import br.com.eletroandrade.sistemaestoque.model.Venda;
import br.com.eletroandrade.sistemaestoque.util.Action;

@Path("vendas")
@Produces(MediaType.APPLICATION_JSON+ ";charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON+ ";charset=UTF-8")
@RequestScoped
public class VendaAction extends Action<Venda, Long> {
    private static final long serialVersionUID = 1L;

    @Inject
    private VendaDao dao;

    @GET
    @Override
    public List<Venda> listAll() {
        return dao.listAll();
    }

    @PUT
    @Override
    public Response update(Venda item) {
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
    public Response cria(Venda item) {
        dao.criar(item);
        return buildId();
    }

    @GET
    @Override
    @Path("{id}")
    public Venda localiza(@PathParam("id") Long id) {
        return dao.localizar(id);
    }
}

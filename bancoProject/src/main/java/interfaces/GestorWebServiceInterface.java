package interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import servidor.Conta;

import java.util.List;

@WebService
public interface GestorWebServiceInterface {
    @WebMethod
    @GET
    @Path("/criarconta/{a}/{b}")
    @Produces(MediaType.TEXT_PLAIN)
    boolean criarConta(@PathParam("a") String nome, @PathParam("b")int conta);

    @WebMethod
    @GET
    @Path("/removerconta/{a}")
    @Produces(MediaType.TEXT_PLAIN)
    boolean removerConta(@PathParam("a")int conta);

    @WebMethod
    @GET
    @Path("/sacar/{a}/{b}")
    @Produces(MediaType.TEXT_PLAIN)
    boolean saque(@PathParam("a") int conta, @PathParam("b") double valor);

    @WebMethod
    @GET
    @Path("/transferir/{a}/{b}/{c}")
    @Produces(MediaType.TEXT_PLAIN)
    boolean transferir(@PathParam("a") int conta1, @PathParam("b") int conta2, @PathParam("c") double valor);

    @WebMethod
    @GET
    @Path("/deposita/{a}/{b}")
    @Produces(MediaType.TEXT_PLAIN)
    boolean depositar(@PathParam("a") int conta, @PathParam("b") double valor);

    @WebMethod
    @GET
    @Path("/getContas")
    @Produces(MediaType.TEXT_PLAIN)
    List<Conta> getContas();
}

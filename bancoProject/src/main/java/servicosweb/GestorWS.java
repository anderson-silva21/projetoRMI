/*package servicosweb;

import servidor.Conta;
import servidor.Gestor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/contas")
public class GestorWS {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarConta(Conta conta) {
        try {
            boolean criada = Gestor.getInstance().criarConta(conta.getNome(), conta.getConta());
            if (criada) {
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.CONFLICT).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
*/
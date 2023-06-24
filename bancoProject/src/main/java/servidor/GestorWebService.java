package servidor;

import java.rmi.RemoteException;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import interfaces.GestorWebServiceInterface;

import java.util.List;

@WebService
public class GestorWebService implements GestorWebServiceInterface{

    private Gestor gestor;

    public GestorWebService() {
        try {
            gestor = (Gestor) Gestor.getInstance();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    public boolean criarConta(@WebParam(name = "nome") String nome, @WebParam(name = "conta") int conta) {
        try {
            return gestor.criarConta(nome, conta);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @WebMethod
    public boolean removerConta(@WebParam(name = "conta") int conta) {
        try {
            return gestor.removerConta(conta);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @WebMethod
    public boolean saque(@WebParam(name = "conta") int conta, @WebParam(name = "valor") double valor) {
        try {
            return gestor.saque(conta, valor);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @WebMethod
    public boolean transferir(
            @WebParam(name = "conta1") int conta1,
            @WebParam(name = "conta2") int conta2,
            @WebParam(name = "valor") double valor) {
        try {
            return gestor.transferir(conta1, conta2, valor);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @WebMethod
    public boolean depositar(@WebParam(name = "conta") int conta, @WebParam(name = "valor") double valor) {
        try {
            return gestor.depositar(conta, valor);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @WebMethod
    public List<Conta> getContas() {
        try {
            return gestor.getContas();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package servidor;

import java.rmi.RemoteException;
import javax.jws.WebService;

import interfaces.GestorWebServiceInterface;

import java.util.List;

@WebService(endpointInterface = "interfaces.GestorWebServiceInterface")
public class GestorWebService implements GestorWebServiceInterface{

    private Gestor gestor;

    public GestorWebService() {
        try {
            gestor = (Gestor) Gestor.getInstance();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean criarConta(String nome, int conta) {
        try {
            return gestor.criarConta(nome, conta);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removerConta(int conta) {
        try {
            return gestor.removerConta(conta);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saque(int conta, double valor) {
        try {
            return gestor.saque(conta, valor);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean transferir(
            int conta1,
            int conta2,
            double valor) {
        try {
            return gestor.transferir(conta1, conta2, valor);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean depositar(int conta, double valor) {
        try {
            return gestor.depositar(conta, valor);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Conta> getContas() {
        try {
            return gestor.getContas();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}

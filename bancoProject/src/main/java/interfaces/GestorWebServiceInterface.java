package interfaces;

import javax.jws.WebParam;
import javax.jws.WebService;

import servidor.Conta;

import java.util.List;

@WebService
public interface GestorWebServiceInterface {
    boolean criarConta(String nome, int conta);
    boolean removerConta(int conta);
    boolean saque(int conta, double valor);
    boolean transferir(int conta1, int conta2, double valor);
    boolean depositar(int conta, double valor);
    List<Conta> getContas();
}

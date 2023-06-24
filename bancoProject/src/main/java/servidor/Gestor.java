/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import interfaces.MensageiroInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public final class Gestor extends UnicastRemoteObject implements MensageiroInterface {

    private static MensageiroInterface instance = null;
    private static final long serialVersionUID = 1L;
    private List<Conta> contas;
    private Object lock = new Object();

    private Gestor() throws RemoteException {
        this.contas = new ArrayList<Conta>();
    }

    @Override
    public boolean criarConta(String nome, int conta) throws RemoteException {
        synchronized (lock) {
            for (Conta c : contas) {
                if (c.getConta() == conta) {
                    return false;
                }
            }
            Conta novaConta = new Conta(nome, conta);
            contas.add(novaConta);
            return true;
        }
    }

    @Override
    public boolean removerConta(int conta) throws RemoteException {
        synchronized (lock) {
            for (Conta c : contas) {
                if (c.getConta() == conta) {
                    contas.remove(c);
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public boolean saque(int conta, double valor) throws RemoteException {
        synchronized (lock) {
            for (Conta c : contas) {
                if (c.getConta() == conta) {
                    return c.sacar(valor);
                }
            }
            return false;
        }
    }

    @Override
    public boolean transferir(int conta1, int conta2, double valor) throws RemoteException {
        synchronized (lock) {
            Conta contaOrigem = null;
            Conta contaDestino = null;
            for (Conta c : contas) {
                if (c.getConta() == conta1) {
                    contaOrigem = c;
                }
                if (c.getConta() == conta2) {
                    contaDestino = c;
                }
            }
            if (contaOrigem != null && contaDestino != null) {
                if (contaOrigem.getSaldo() >= valor) {
                    return contaOrigem.transferir(valor, contaDestino);
                } else {
                    return false; // Saldo insuficiente na conta de origem
                }
            }
            return false; // Contas não encontradas
        }
    }

    @Override
    public boolean depositar(int conta, double valor) throws RemoteException {
        synchronized (lock) {
            for (Conta c : contas) {
                if (c.getConta() == conta) {
                    c.depositar(valor);
                    return true;
                }
            }
            return false;
        }
    }

    public static MensageiroInterface getInstance() throws RemoteException {
        if (instance == null) {
            instance = new Gestor();
        }
        return instance;
    }

    @Override
    public List<Conta> getContas() throws RemoteException {
        synchronized (lock) {
            return new ArrayList<>(contas);
        }
    }

}

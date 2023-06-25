/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;

import interfaces.MensageiroInterface;

/**
 *
 * @author User
 */
public class Main {
    /**
     * @param args
     * @throws RemoteException
     * @throws MalformedURLException
     * @throws AlreadyBoundException 
     */
    
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        try {
            MensageiroInterface mensageiro = new Gestor();
            //cria o gerente que disponibiliza objetos informando a porta
            Registry registry = LocateRegistry.createRegistry(1099);
            //disponibiliza o objeto remoto
            registry.rebind("gestor", mensageiro);
            
            // Disponibiliza o Web Service
            String address = "http://localhost:8080/gestor";
            Endpoint.publish(address, new GestorWebService());

            JOptionPane.showMessageDialog(null, "Servidor iniciado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao iniciar o servidor: " + e.getMessage());
            System.out.println(e);
        }
    }
}

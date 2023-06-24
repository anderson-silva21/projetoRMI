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

import javax.xml.ws.Endpoint;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
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
        
        //cria o gerente que disponibiliza objetos informando a porta
        Registry registry = LocateRegistry.createRegistry(80);
        //disponibiliza o objeto remoto
        registry.bind("gestor", Gestor.getInstance());
        
        // Disponibiliza o Web Service
        String address = "http://localhost:8080/gestor";
        Endpoint.publish(address, new GestorWebService());

        System.out.println("Servidor RMI e Web Service iniciado.");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import excepciones.ClienteExceptions.ClienteIncomploteException;
import modelo.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositorio.ClienteDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

/**
 *
 * @author bangho
 */
public class ClienteController {
    
    ClienteDao clienteDao; 
    Session session;
    Transaction tx;
    Cliente clienteNuevo = new Cliente();
    
    public void guardarClienteNuevo(){
       try{ 
          // clienteNuevo.validarDatos();
           guardarCliente();
       }
       catch(Exception e){
           throw  (new ClienteIncomploteException(e.getMessage()));
       }
    }
    public void guardarCliente(){
        
	session = SessionFactoryProvider.getInstance().createSession();
	tx = session.beginTransaction();	
	Runner.addSession(session);
        clienteDao = new ClienteDao();
        clienteDao.guardar(clienteNuevo);
        tx.commit();
        session.close();
        
    }

    public Cliente getClienteNuevo() {
        return clienteNuevo;
    }

    public void setClienteNuevo(Cliente clienteNuevo) {
        this.clienteNuevo = clienteNuevo;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Observable;
import modelo.Cliente;
import modelo.Credito;

/**
 *
 * @author OVERCROSS
 */
public class CreditoObservado extends Observable {
    public Credito credito;
    
    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }
    
    public void setCliente(Cliente cliente) {
        this.credito.setCliente(cliente);
        this.setChanged();
        this.notifyObservers(this);
        
    }
    public void actualizarVista(){
        this.setChanged();
        this.notifyObservers(this);
    }
    
}

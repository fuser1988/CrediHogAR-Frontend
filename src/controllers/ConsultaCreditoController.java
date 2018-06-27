/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import modelo.Cliente;
import modelo.Credito;
import modelo.FormaDePago;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositorio.CreditoDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;



public class ConsultaCreditoController extends Observable {
    Credito creditoBuscado;
    CreditoDao creditoDao;
    Session session;
    Transaction tx;

    List<FormaDePago> formasDePago;

    public ConsultaCreditoController() {
        creditoBuscado = new Credito();
        creditoBuscado.setCodigo("A-2354");
        Cliente cli= new Cliente();
        cli.setApellido("fuser");
        creditoBuscado.setCliente(cli);
        creditoDao = new CreditoDao();

        formasDePago = new ArrayList<FormaDePago>();
        formasDePago.add(FormaDePago.SEMANAL);
        formasDePago.add(FormaDePago.QUINCENAL);
        formasDePago.add(FormaDePago.MENSUAL);
     
    }

    public Credito getCreditoBuscado() {
        return creditoBuscado;
    }

    public void setCreditoBuscado(Credito creditoBuscado) {
        this.creditoBuscado = creditoBuscado;
        this.setChanged();
        this.notifyObservers();
    }

    public List<FormaDePago> getFormasDePago() {
        return formasDePago;
    }

    public void setFormasDePago(List<FormaDePago> formasDePago) {
        this.formasDePago = formasDePago;
    }


    public void buscarCredito(String codigo) {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        creditoDao = new CreditoDao();
        this.setCreditoBuscado(creditoDao.traerPorId(codigo)); // arrojar exepcion si no encuentra el codigo
        tx.commit();
        session.close();
    }
    
    
    
}

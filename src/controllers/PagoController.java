/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import modelo.Credito;
import modelo.Pago;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositorio.CreditoDao;
import repositorio.PagoDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

/**
 *
 * @author Bautista
 */
public class PagoController {

    PagoDao pagoDao;
    Pago pago;
    Pago pagoEditado;
    CreditoDao creditoDao;
    Credito credito;
    Pago pagoNuevo;
    Session session;
    Transaction tx;
    Pago pagoSeleccionado;

    public PagoController() {
        pagoNuevo = new Pago();
        pago = new Pago();
    }

    public void guardar() {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        pagoDao = new PagoDao();
        pagoDao.guardar(pagoNuevo);
        tx.commit();
        session.close();
    }

    public void eliminarPago() {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        credito.removepago(pagoSeleccionado);
        tx.commit();
        session.close();
    }
    
    public void editarPago(Pago pagoEditado){
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        pagoDao = new PagoDao();
        pagoDao.actualizar(pagoEditado);
        tx.commit();
        session.close();
    }
    
    public Pago recuperarPago() {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        pagoDao = new PagoDao();
        pagoDao.recuperar("id", pagoEditado.getId());
        tx.commit();
        session.close();
        return pagoEditado;
    }
    
    
    public void actualizarCredito(Credito credito) {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        creditoDao = new CreditoDao();
        creditoDao.actualizar(credito);
        tx.commit();
        session.close();
    }

    public Pago getPagoEditado() {
        return pagoEditado;
    }

    public void setPagoEditado(Pago pagoEditado) {
        this.pagoEditado = pagoEditado;
    }

    public Pago getPagoNuevo() {
        return pagoNuevo;
    }

    public void setPagoNuevo(Pago pagoNuevo) {
        this.pagoNuevo = pagoNuevo;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Pago getPago() {
        return pago;
    }  
    
    public Pago getPagoSeleccionado() {
        return pagoSeleccionado;
    }

    public void setPagoSeleccionado(Pago pagoSeleccionado) {
        this.pagoSeleccionado = pagoSeleccionado;
    }

        public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }
}

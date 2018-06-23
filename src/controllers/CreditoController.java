package controllers;

import excepciones.CreditoException.CreditoInvalidoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import modelo.Credito;
import modelo.Cliente;
import modelo.FormaDePago;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositorio.CreditoDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;
import utils.CreditoObservado;

public class CreditoController {

    Credito creditoNuevo;
    CreditoDao creditoDao;
    Session session;
    Transaction tx;

    List<FormaDePago> formasDePago;
    CreditoObservado creditoObservado;
    public CreditoController() {

        creditoNuevo = new Credito();
        creditoNuevo.setCliente(new Cliente());
        creditoDao = new CreditoDao();

        formasDePago = new ArrayList<FormaDePago>();
        formasDePago.add(FormaDePago.SEMANAL);
        formasDePago.add(FormaDePago.QUINCENAL);
        formasDePago.add(FormaDePago.MENSUAL);
        
        creditoObservado = new CreditoObservado();
        creditoObservado.setCredito(creditoNuevo);
        
    }

    public void guardarCreditoNuevo() {
        try {
            // crecditoNuevo.validarDatos();
            guardarCredito();
        } catch (Exception e) {
            throw (new CreditoInvalidoException(e.getMessage()));
        }

    }

    public void guardarCredito() {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        creditoDao = new CreditoDao();
        creditoDao.guardar(creditoNuevo);
        tx.commit();
        session.close();
    }

    public Credito getCreditoNuevo() {
        return creditoNuevo;
    }

    public void setCreditoNuevo(Credito creditoNuevo) {
        this.creditoNuevo = creditoNuevo;
    }

    public CreditoDao getCreditoDao() {
        return creditoDao;
    }

    public void setCreditoDao(CreditoDao creditoDao) {
        this.creditoDao = creditoDao;
    }

    public List<FormaDePago> getFormasDePago() {
        return formasDePago;
    }
  
    public CreditoObservado getCreditoObservado() {
        return creditoObservado;
    }

    public void setCreditoObservado(CreditoObservado creditoObservado) {
        this.creditoObservado = creditoObservado;
    }
    
}

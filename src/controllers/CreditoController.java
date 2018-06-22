package controllers;
import excepciones.CreditoException.CreditoInvalidoException;
import java.util.ArrayList;
import java.util.List;
import modelo.Credito;
import modelo.Cliente;
import modelo.FormaDePago;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositorio.CreditoDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

public class CreditoController {
    Credito creditoNuevo;
    CreditoDao creditoDao;
    Session session;
    Transaction tx;
    Cliente clienteDeCreditoNuevo;
    List<FormaDePago> formasDePago;
    
    public CreditoController() {
        creditoNuevo = new Credito();
        creditoDao = new CreditoDao();
        formasDePago = new ArrayList<FormaDePago>();
        formasDePago.add(FormaDePago.SEMANAL);
        formasDePago.add(FormaDePago.QUINCENAL);
        formasDePago.add(FormaDePago.MENSUAL);
    }    
    
    public void guardarCreditoNuevo(){
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

    public Cliente getClienteDeCreditoNuevo() {
        return clienteDeCreditoNuevo;
    }

    public void setClienteDeCreditoNuevo(Cliente clienteDeCreditoNuevo) {
        this.clienteDeCreditoNuevo = clienteDeCreditoNuevo;
    }

   

    public List<FormaDePago> getFormasDePago() {
        return formasDePago;
    }
    
}

/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import org.hibernate.Session;
import org.hibernate.Transaction;
import repositorio.PagoDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

/**
 *
 * @author Bautista
 */
public class ConsultaController {
    
    Session session;
    Transaction tx;
    PagoDao pagoDao;
    
    public long consultarRecaudo(String fecha1, String fecha2, int seleccionarConsulta) {
        long recaudo = 0;
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        pagoDao = new PagoDao();
        switch (seleccionarConsulta){
            case 1:
                recaudo = pagoDao.consultarRecaudoMensual(fecha1, fecha2);
                break;
            default:
                break;
        }
        
        tx.commit();
        session.close();
        return recaudo;
    }
}

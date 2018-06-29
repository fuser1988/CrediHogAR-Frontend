/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import modelo.Pago;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositorio.PagoDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

/**
 *
 * @author Bautista
 */
public class PagosController {

    Session session;
    Transaction tx;
    PagoDao pagoDao;
    Pago pagoNuevo;
    Pago pagoCredito;


}

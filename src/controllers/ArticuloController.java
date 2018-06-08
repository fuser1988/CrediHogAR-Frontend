/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import excepciones.ArticuloExceptions.ArticuloInvalidoException;
import modelo.Articulo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositorio.ArticuloDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

public class ArticuloController {
    
    public Articulo articuloSeleccionado;
    public ArticuloDao articuloDao;
    Session session;
    Transaction tx;
    
    

    public ArticuloController(){
        articuloSeleccionado = new Articulo();
        traeUnArticuloCualquiera();
    }

    public void guardarArticuloNuevo(){
        try {
            // ArticuloSeleccionado.validarDatos();
            guardarArticulo();
        } catch (Exception e) {
            throw (new ArticuloInvalidoException(e.getMessage()));
        }

    }

    private void traeUnArticuloCualquiera() {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        articuloDao = new ArticuloDao();
        if (articuloDao.contiene("id", 1)){
            this.articuloSeleccionado = articuloDao.recuperar(1);
        }
        tx.commit();
        session.close();
    }

    private void guardarArticulo() {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        articuloDao = new ArticuloDao();
        articuloDao.guardar(articuloSeleccionado);
        tx.commit();
        session.close();
    }
}
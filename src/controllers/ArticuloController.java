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
    private String articuloBuscar;
    private Articulo articulo;
    Session session;
    Transaction tx;

    public ArticuloController() {
        articuloSeleccionado = new Articulo();
        traeUnArticuloCualquiera();
    }

    public void guardarArticuloNuevo() {
        try {
            // ArticuloSeleccionado.validarDatos();
            guardarArticulo();
        } catch (Exception e) {
            throw (new ArticuloInvalidoException(e.getMessage()));
        }
    }
    
    public void editarArticulo(Articulo articuloedit){
        try{
        editar(articuloedit);
        }catch(Exception e){
        }
    }
    
    public void eliminarArticulo(int codigo){
        try{
            eliminar(codigo);
        }catch(Exception e){
        }
    }

    public Articulo buscarArticulo(int seleccion) {
        return buscar(seleccion);
    }
    
    public String getArticuloBuscar(){
        return articuloBuscar;
    }
    
    public void setArticuloBuscar(String articulo){
        this.articuloBuscar=articulo;
    }

    private void traeUnArticuloCualquiera() {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        articuloDao = new ArticuloDao();
        if (articuloDao.contiene("id", 1)) {
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

    private Articulo buscar(int seleccionarBusqueda) {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        articuloDao = new ArticuloDao();
        switch (seleccionarBusqueda) {
            case 1:
                articulo = articuloDao.buscarCodigo(Integer.parseInt(articuloBuscar));
                break;
            case 2:
                articulo = articuloDao.buscarNombre(articuloBuscar);
                break;
            default:
                break;
        }
        tx.commit();
        session.close();
        return articulo;
    }
    
    private void editar(Articulo articuloEditar){
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        articuloDao = new ArticuloDao();
        articuloDao.actualizar(articuloEditar);
        tx.commit();
        session.close();
    }
    
    private void eliminar(int codigo){
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        articuloDao = new ArticuloDao();
        articuloDao.borrar("codigoArticulo", codigo);
        tx.commit();
        session.close();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import excepciones.ClienteExceptions.ClienteIncomploteException;
import java.io.Serializable;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Credito;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositorio.ClienteDao;
import repositorio.PagoDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;
import utils.CreditoObservado;

/**
 *
 * @author bangho
 */
public class ClienteController {

    ClienteDao clienteDao;
    Session session;
    Transaction tx;
    Cliente cliente = new Cliente();
    Cliente clienteNuevo = new Cliente();
    private Cliente clienteEdit = new Cliente();
    private String clienteBuscar = null;
    private Cliente clienteSeleccionado = new Cliente();
    private int clienteEditado = 0;
    public List<Cliente> resultadoBusquedaClientes;
    public CreditoObservado creditoNuevo; 
    
    public ClienteController (){
        resultadoBusquedaClientes = this.traerClientes();
    }

    public List<Cliente> getResultadoBusquedaClientes() {
        return resultadoBusquedaClientes;
    }

    public void setResultadoBusquedaClientes(List<Cliente> resultadoBusquedaClientes) {
        this.resultadoBusquedaClientes = resultadoBusquedaClientes;
    }
    
    public void guardarClienteNuevo() {
        try {
            //clienteNuevo.validarDatos();
            guardarCliente();
        } catch (Exception e) {
            throw (new ClienteIncomploteException(e.getMessage()));
        }
    }

    private void guardarCliente() {

        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        clienteDao = new ClienteDao();
        clienteDao.guardar(clienteNuevo);
        tx.commit();
        session.close();
    }
    
    public Cliente buscarCliente(int seleccionar){
        return buscar(seleccionar);
    }

    private Cliente buscar(int seleccionarBusqueda) {

        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        clienteDao = new ClienteDao();

        switch (seleccionarBusqueda) {

            case 1:
                cliente = clienteDao.buscarNombre(clienteBuscar);
                break;
            case 2:
                cliente = clienteDao.buscarApellido(clienteBuscar);
                break;
            case 3:
                if (clienteBuscar.equalsIgnoreCase("")) {
                    clienteBuscar = "0";
                }
                cliente = clienteDao.buscarDni(Integer.parseInt(clienteBuscar));
                break;
            case 4:
                if (clienteBuscar.equalsIgnoreCase("")) {
                    clienteBuscar = "0";
                }
                cliente = clienteDao.buscarID(Integer.parseInt(clienteBuscar));
                break;
            case 5:
                cliente = clienteDao.buscarCalificacion(clienteBuscar);
                break;
            default:
                break;
        }
        tx.commit();
        session.close();
        return cliente;
    }
    
    public List<Cliente> traerClientes(){
        return traerLista();
    }

    private List<Cliente> traerLista() {
        List<Cliente> traertodo;
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        clienteDao = new ClienteDao();
        traertodo = clienteDao.traerTodo();
        tx.commit();
        session.close();
        return traertodo;
    }
     
    public void borrarClienteSeleccionado(){
    try {
        borrar();
    }catch(Exception e){
    }
    }
    
    private void borrar() {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        clienteDao = new ClienteDao();
        clienteDao.borrar("id", clienteSeleccionado.getId());
        tx.commit();
        session.close();
    }

    public Cliente recuperarCliente(){
        return recuperar();
    }
    
    private Cliente recuperar() {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        clienteDao = new ClienteDao();
        clienteEdit = clienteDao.recuperar("id", clienteEditado);
        tx.commit();
        session.close();
        return clienteEdit;
    }
    
    public void editarCliente(Cliente cliente){
        try{
            editar(cliente);
        }catch(Exception e){
        }
    }

    private void editar(Cliente clienteeditado) {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        clienteDao = new ClienteDao();
        clienteDao.actualizar(clienteeditado);
        tx.commit();
        session.close();
    }

     public Cliente getClienteNuevo() {
        return clienteNuevo;
    }

    public void setClienteNuevo(Cliente clienteNuevo) {
        this.clienteNuevo = clienteNuevo;
    }

    public String getBuscarCliente() {
        return clienteBuscar;
    }

    public void setBuscarCliente(String clientebuscar) {
        this.clienteBuscar = clientebuscar;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public int getClienteEditado() {
        return clienteEditado;
    }
    
    public void setClienteaEditar(int clienteEditar) {
        this.clienteEditado = clienteEditar;
    }
    
    public void setClienteSelecionado(Cliente cliente) {
        this.clienteSeleccionado = cliente;
    }

    public Cliente getClienteSelecionado() {
        return clienteSeleccionado;
    }
    
    public void setCreditoNuevo(CreditoObservado creditoNuevo) {
        this.creditoNuevo = creditoNuevo;
    }
    
    
}

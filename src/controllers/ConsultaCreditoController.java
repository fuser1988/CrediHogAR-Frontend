/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import modelo.Cargo;
import modelo.Cliente;
import modelo.Credito;
import modelo.Empleado;
import modelo.EstadoDeCredito;
import modelo.FormaDePago;
import modelo.Pago;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositorio.CreditoDao;
import repositorio.EmpleadoDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

public class ConsultaCreditoController extends Observable {

    Credito creditoBuscado;
    CreditoDao creditoDao;
    Session session;
    Transaction tx;
    Credito credito;
    
    List<FormaDePago> formasDePago;
    List<EstadoDeCredito> estadosDeCredito;
    List<Credito> creditoCodigo;
    List<Empleado> empleados;
    EmpleadoDao empleadoDao ;
    Empleado cobrador;
        
    public ConsultaCreditoController() {
        empleados = new ArrayList();
        empleadoDao = new EmpleadoDao();
        recuperarEmpleados();//
        creditoBuscado = new Credito();
        credito = new Credito();

        Cliente cli = new Cliente();

        creditoBuscado.setCliente(cli);
        creditoDao = new CreditoDao();

        formasDePago = new ArrayList<FormaDePago>();
        formasDePago.add(FormaDePago.SEMANAL);
        formasDePago.add(FormaDePago.QUINCENAL);
        formasDePago.add(FormaDePago.MENSUAL);

        estadosDeCredito = new ArrayList<EstadoDeCredito>();
        estadosDeCredito.add(EstadoDeCredito.VIGENTE);
        estadosDeCredito.add(EstadoDeCredito.PAGO);
        estadosDeCredito.add(EstadoDeCredito.MORA);
        estadosDeCredito.add(EstadoDeCredito.BAJA);

    }

    public Empleado getCobrador() {
        return cobrador;
    }

    public void setCobrador(Empleado cobrador) {
        this.cobrador = cobrador;
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
        cobrador = credito.getCobrador();
        tx.commit();
        session.close();
    }

    public List<EstadoDeCredito> getEstadosDeCredito() {
        return estadosDeCredito;
    }

    public void actualizarCredito() {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        creditoDao = new CreditoDao();
        creditoDao.actualizar(creditoBuscado); // arrojar exepcion si no encuentra el codigo
        tx.commit();
        session.close();
    }

    public int sumaTotal() {
        List<Pago> pago = credito.getunpago();
        int total = 0;
        for (Pago e : pago) {
            total += e.getMonto();
        }
        total += credito.getAnticipo();
        return total;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public List<Empleado> getCobradores() {
        List<Empleado> cobradores = new ArrayList();
        empleados.stream().filter((empleado) -> (empleado.getCargo() == Cargo.COBRADOR)).forEachOrdered((empleado) -> {
            cobradores.add(empleado);
        });
        return cobradores;
    }

    private void recuperarEmpleados() {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        empleados = empleadoDao.traerTodo();
        tx.commit();
        session.close();
        
    }
    }

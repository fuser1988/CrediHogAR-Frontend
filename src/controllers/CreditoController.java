package controllers;

import excepciones.CreditoException.CreditoInvalidoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import modelo.Articulo;
import modelo.Cargo;
import modelo.Credito;
import modelo.Cliente;
import modelo.Empleado;
import modelo.FormaDePago;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositorio.ArticuloDao;
import repositorio.CreditoDao;
import repositorio.EmpleadoDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;
import utils.CreditoObservado;

public class CreditoController {

    Credito creditoNuevo;
    CreditoDao creditoDao;
    Session session;
    Transaction tx;
    public ArticuloDao articuloDao;
    List<FormaDePago> formasDePago;
    CreditoObservado creditoObservado;
    List<Articulo> articulos;
    List<Empleado> empleados;
    EmpleadoDao empleadoDao ;
    public CreditoController() {
        empleados = new ArrayList<Empleado>();
        empleadoDao = new EmpleadoDao();
        recuperarEmpleados();//
        
        creditoNuevo = new Credito();
        creditoNuevo.setCliente(new Cliente());
        creditoDao = new CreditoDao();

        formasDePago = new ArrayList<FormaDePago>();
        formasDePago.add(FormaDePago.SEMANAL);
        formasDePago.add(FormaDePago.QUINCENAL);
        formasDePago.add(FormaDePago.MENSUAL);
        
        creditoObservado = new CreditoObservado();
        creditoObservado.setCredito(creditoNuevo);
        articulos = new ArrayList<Articulo>();
//         session = SessionFactoryProvider.getInstance().createSession();
//        tx = session.beginTransaction();
//        Runner.addSession(session);
//        Articulo unArticulo = new Articulo();
//        unArticulo.setCodigoArticulo(1324);
//        unArticulo.setCosto(1000);
//        unArticulo.setNombre("Tostatador");
//        unArticulo.setDescripcion("marca Acme");
//        unArticulo.setPrecio(2000);
//        
//        articulos.add(unArticulo);
        
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
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

    public void cargarArticulo(int codigo) {
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        articuloDao = new ArticuloDao();
        Articulo articulo = articuloDao.buscarCodigo(codigo);
        
        tx.commit();
        session.close();
        
        creditoNuevo.getArticulos().add(articulo);
        articulos = creditoNuevo.getArticulos();
        creditoObservado.actualizarVista();
        
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


package controllers;

import java.util.ArrayList;
import java.util.List;
import modelo.Cargo;
import modelo.Empleado;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositorio.EmpleadoDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

public class EmpleadosController {
    
    Session session;
    Transaction tx;
    Empleado empleadoNuevo;
    List<Cargo> cargos;
    EmpleadoDao empleadoDao;
    public EmpleadosController(){
    empleadoNuevo = new Empleado();
    empleadoDao = new EmpleadoDao();
    initCargos();
    }

    private void initCargos() {
        cargos = new ArrayList<Cargo>();
        cargos.add(Cargo.VENDEDOR);
        cargos.add(Cargo.COBRADOR);
        cargos.add(Cargo.JEFEDeEQUIPO);
        cargos.add(Cargo.ADMINISTRADOR);
    }
    
    public void guardar(){
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        empleadoDao.guardar(empleadoNuevo);
        tx.commit();
        session.close();
    
    }

    public Empleado getEmpleadoNuevo() {
        return empleadoNuevo;
    }

    public void setEmpleadoNuevo(Empleado empleadoNuevo) {
        this.empleadoNuevo = empleadoNuevo;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public EmpleadoDao getEmpleadoDao() {
        return empleadoDao;
    }

    public void setEmpleadoDao(EmpleadoDao empleadoDao) {
        this.empleadoDao = empleadoDao;
    }
    
}

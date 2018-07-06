
package controllers;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.Articulo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositorio.ArticuloDao;
import repositorio.Runner;
import repositorio.SessionFactoryProvider;

public class SelecionarArticuloController {
    public Articulo articuloSeleccionado;
    public ArticuloDao articuloDao;
    private String articuloBuscar = "";
    private Articulo articulo;
    Session session;
    Transaction tx;
    List<Articulo> articulosBuscados;
    DefaultTableModel articulosModel;
    
    public void buscarArticulos(){
        session = SessionFactoryProvider.getInstance().createSession();
        tx = session.beginTransaction();
        Runner.addSession(session);
        articuloDao = new ArticuloDao();
        articulosBuscados = articuloDao.buscarNombreSimilares(articuloBuscar);
        
        tx.commit();
        session.close();
        llenarTabla();
    }
    private void llenarTabla( ) {
        articulosModel.setRowCount(0);
        List<Articulo> articulos  = articulosBuscados; 
        if (articulos.size() >0) {
            articulos.stream().map((articulo) -> new Object[]{
                articulo.getCodigoArticulo(),
                articulo.getNombre(),
                articulo.getDescripcion(),
                articulo.getPrecio(),
                articulo.getComision()
            }).map((objetos) -> {
                articulosModel.addRow(objetos);
                return objetos;
            }).forEachOrdered((_item) -> {
                articulosModel.getDataVector();
            });
        } else {
        }    
    }

    public Articulo getArticuloSeleccionado() {
        return articuloSeleccionado;
    }

    public void setArticuloSeleccionado(Articulo articuloSeleccionado) {
        this.articuloSeleccionado = articuloSeleccionado;
    }

    public String getArticuloBuscar() {
        return articuloBuscar;
    }

    public void setArticuloBuscar(String articuloBuscar) {
        this.articuloBuscar = articuloBuscar;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public List<Articulo> getArticulosBuscados() {
        return articulosBuscados;
    }

    public void setArticulosBuscados(List<Articulo> articulosBuscados) {
        this.articulosBuscados = articulosBuscados;
    }

    public DefaultTableModel getArticulosModel() {
        return articulosModel;
    }

    public void setArticulosModel(DefaultTableModel articulosModel) {
        this.articulosModel = articulosModel;
    }

}

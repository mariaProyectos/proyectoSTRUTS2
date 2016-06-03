package struts;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import comun.Errores;
import negocio.*;

import java.util.ArrayList;

import java.util.Date;


/**
 * Created by MRCOGUSA on 02/06/2016.
 */
public class FacturaAction extends ActionSupport {
    ControlAccesoFacturaDao controlAccesoFacturaDao;
    Usuario usuarioActual;
    ArrayList<Factura> listaFacturas;



    public String generar(){
        usuarioActual=(Usuario) ActionContext.getContext().getSession().get("usuario");
        controlAccesoFacturaDao=new ControlAccesoFacturaDao(usuarioActual);
        Factura factura=new Factura();
        Carro carro= (Carro) ActionContext.getContext().getSession().get("carro");
        factura.ponerLineas(carro.getLineasCompras());
        factura.setIdUsuario(usuarioActual.getCodigo());
        factura.setFecha(new Date());
        try {
            controlAccesoFacturaDao.insertar(factura);
        } catch (Errores errores) {
            errores.printStackTrace();
        }
        return "index";
    }


    public String ver(){
        Usuario user=(Usuario)ActionContext.getContext().getSession().get("usuario");
        ControlAccesoFacturaDao instancia=new ControlAccesoFacturaDao(user);
        try {
            listaFacturas=instancia.obtener();
        } catch (Errores errores) {
            errores.printStackTrace();
        }
        return"verFactura";
    }

    public String borrar(){
        Usuario user=(Usuario)ActionContext.getContext().getSession().get("usuario");
        ControlAccesoFacturaDao instancia=new ControlAccesoFacturaDao(user);
        try {
            listaFacturas=instancia.obtener();
        } catch (Errores errores) {
            errores.printStackTrace();
        }
        return"verFactura";
    }

    public ArrayList<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(ArrayList<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }
}

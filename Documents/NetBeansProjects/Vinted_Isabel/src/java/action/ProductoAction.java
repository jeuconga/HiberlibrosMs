package action;

import DAO.ProductoDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Producto;

/**
 *
 * @author Isabel
 */

public class ProductoAction implements interfaces.Action {
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        String cadDestino = "";
        
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
        }
        return cadDestino;
    }
    
    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Producto> listaProductos = null;
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        String filtro = request.getParameter("FILTRO");
        
        ProductoDAO productoDao = new ProductoDAO();
        Producto prod = new Producto();
        
        if(filtro != null){
            String[] arrayFiltro = filtro.split("\\.");
            
            switch(arrayFiltro[0]){
                case "CATEGORIA":
                    String categoria = request.getParameter("CATEGORIA");
                    listaProductos = productoDao.productosCategoria(categoria);
                    break;
                case "TOP10":
                    listaProductos = productoDao.top10();
            }
        }else{
            listaProductos = productoDao.findAll(null);
        }
        return Producto.toArrayJSon(listaProductos);
    }
}
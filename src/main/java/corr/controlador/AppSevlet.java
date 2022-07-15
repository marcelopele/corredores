package corr.controlador;

import corr.modelo.Carrera;
import corr.modelo.Modelo;
import corr.modelo.Corredor;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "AppSevlet", urlPatterns = {"/app"})
public class AppSevlet extends HttpServlet {


@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        Modelo m = new Modelo();

        String accion = request.getParameter("accion");
        Object accion2 = request.getAttribute("accion2");
        accion = accion == null ? "" : accion;
        accion = (String) (accion2 != null ? accion2 : accion);
        
        switch (accion) {
                
            case "getCarreras":
                request.setAttribute("cardCorredor", m.getCorredor(Integer.parseInt(request.getParameter("id_corredor"))));
                request.setAttribute("listCarreras", m.getCarreras(Integer.parseInt(request.getParameter("id_corredor"))));
                request.getRequestDispatcher("carreras.jsp").forward(request, response);
                break;

            default: 
                request.setAttribute("cardsCorredores", m.getCorredores());
                request.setAttribute("Mensaje", accion);
                request.getRequestDispatcher("main.jsp").forward(request, response);

                break;                
        }
    }

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            Modelo m = new Modelo();
            Corredor corredor;
            Carrera carrera;
            int id_corredor;
            int id_carrera;
            int id;
            String accion = request.getParameter("accion");
            accion = accion == null ? "" : accion;
            
        switch (accion) {
                
            case "newCorredor":
                corredor = new Corredor();
                cargarCorredorSegunParams(corredor,request);
                m.addCorredor(corredor);
                doGet(request, response);
                break;

            case "updCorredor":
                corredor = new Corredor();
                cargarCorredorSegunParams(corredor,request);
                m.updCorredor(corredor);
                doGet(request, response);
                break;

            case "delCorredor":
                id = Integer.parseInt(request.getParameter("id_corredor"));
                
                /* Actualizaicón del modelo */
                m.delCorredor(id);

                doGet(request, response);
                break;

            case "newCarrera":
                carrera = new Carrera();
                carrera.setTit_carrera(request.getParameter("tit_carrera"));
                carrera.setFh_carrera(request.getParameter("fh_carrera"));
                carrera.setKm(Double.parseDouble(request.getParameter("km")));
                carrera.setMin(Double.parseDouble(request.getParameter("min")));
                carrera.setId_corredor(Integer.parseInt(request.getParameter("id_corredor")));
                        
                m.addCarrera(carrera);        
                
                request.setAttribute("accion2", "getCarreras");
                doGet(request, response);
                break;

            case "delCarrera":
                id = Integer.parseInt(request.getParameter("id_carrera"));
                m.delCarrera(id);
                request.setAttribute("accion2", "getCarreras");
                doGet(request, response);
                break;

                case "updCarrera":
                    carrera = new Carrera();
                    carrera.setId_carrera(Integer.parseInt(request.getParameter("id_carrera")));
                    carrera.setTit_carrera(request.getParameter("tit_carrera"));
                    carrera.setFh_carrera(request.getParameter("fh_carrera"));
                    carrera.setKm(Double.parseDouble(request.getParameter("km")));
                    carrera.setMin(Double.parseDouble(request.getParameter("min")));
                    carrera.setId_corredor(Integer.parseInt(request.getParameter("id_corredor")));
                    
                    id_carrera=carrera.getId_carrera();
                    id_corredor=carrera.getId_corredor();
                    double min=carrera.getMin();
                    double km=carrera.getKm();
                    String fh_carrera=carrera.getFh_carrera();
                    String tit_carrera = carrera.getTit_carrera();
                    
                    try {
                        m.updCarrera(carrera);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(AppSevlet.class.getName()).log(Level.SEVERE, null, ex);
                    }                

                    /*request.setAttribute("msgError", query);
                    request.getRequestDispatcher("logErr.jsp").forward(request, response);*/
                    request.setAttribute("accion2", "getCarreras");
                    doGet(request, response);
                    break;

            default:
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }
    }
    
    

    private void cargarCorredorSegunParams(Corredor corredor, HttpServletRequest request) {
        if(request.getParameter("id_corredor")!=null){
            corredor.setId_corredor(Integer.parseInt(request.getParameter("id_corredor")));
        }
        corredor.setNom_corredor(request.getParameter("nom_corredor"));
        corredor.setApe_corredor(request.getParameter("ape_corredor"));
        corredor.setImg_corredor(request.getParameter("img_corredor"));
    }
    
}

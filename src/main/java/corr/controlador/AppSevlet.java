package corr.controlador;

import corr.modelo.Carrera;
import corr.modelo.Modelo;
import corr.modelo.Corredor;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        List<Corredor> corredores;
        corredores = new ArrayList();

        String accion = request.getParameter("accion");
        Object accion2 = request.getAttribute("accion2");
        accion = accion == null ? "" : accion;
        accion = (String) (accion2 != null ? accion2 : accion);
        
        switch (accion) {
                
            case "getCarreras":
                try {
                    request.setAttribute("cardCorredor", m.getCorredor(Integer.parseInt(request.getParameter("id_corredor"))));
                } catch (SQLException ex) {
                    request.setAttribute("cardCorredor", "ERROR: "+ex);
                } catch (ClassNotFoundException ex) {
                    request.setAttribute("cardCorredor", "ERROR: "+ex);
                }
                
                try {
                    request.setAttribute("listCarreras", m.getCarreras(Integer.parseInt(request.getParameter("id_corredor"))));
                } catch (SQLException ex) {
                    request.setAttribute("listCarreras", "ERROR: "+ex);
                } catch (ClassNotFoundException ex) {
                    request.setAttribute("listCarreras", "ERROR: "+ex);
                }
                
                request.getRequestDispatcher("carreras.jsp").forward(request, response);
                break;
            
            default: 
                try {
                    request.setAttribute("cardsCorredores", m.getCorredores());
                } catch (SQLException ex) {
                    request.setAttribute("cardsCorredores", "ERROR: "+ex);
                } catch (ClassNotFoundException ex) {
                    request.setAttribute("cardsCorredores", "ERROR: "+ex);
                }
                request.setAttribute("Mensaje", "el mensaje es:"+accion);
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
                corredor.setNom_corredor(request.getParameter("nom_corredor"));
                corredor.setApe_corredor(request.getParameter("ape_corredor"));
                corredor.setImg_corredor(request.getParameter("img_corredor"));
                
                try {
                    m.addCorredor(corredor);
                } catch (SQLException ex) {
                    Logger.getLogger(AppSevlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AppSevlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                doGet(request, response);
                break;
            
            case "updCorredor":
                /* Asignación de valores al objeto */
                corredor = new Corredor();
                corredor.setId_corredor(Integer.parseInt(request.getParameter("id_corredor")));
                corredor.setNom_corredor(request.getParameter("nom_corredor"));
                corredor.setApe_corredor(request.getParameter("ape_corredor"));
                corredor.setImg_corredor(request.getParameter("img_corredor"));
                
                try {
                    /* Actualizaicón del modelo */
                    m.updCorredor(corredor);
                } catch (SQLException ex) {
                    Logger.getLogger(AppSevlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AppSevlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                doGet(request, response);
                break;
                
            case "delCorredor":
                id = Integer.parseInt(request.getParameter("id_corredor"));
                
                try {
                    /* Actualizaicón del modelo */
                    m.delCorredor(id);
                } catch (SQLException ex) {
                    Logger.getLogger(AppSevlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AppSevlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                doGet(request, response);
                break;
                
            case "newCarrera":
                carrera = new Carrera();
                carrera.setTit_carrera(request.getParameter("tit_carrera"));
                carrera.setFh_carrera(request.getParameter("fh_carrera"));
                carrera.setKm(Double.parseDouble(request.getParameter("km")));
                carrera.setMin(Double.parseDouble(request.getParameter("min")));
                carrera.setId_corredor(Integer.parseInt(request.getParameter("id_corredor")));
                        
                try {
                    m.addCarrera(carrera);
                } catch (SQLException ex) {
                    Logger.getLogger(AppSevlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AppSevlet.class.getName()).log(Level.SEVERE, null, ex);
                }                
                
                request.setAttribute("accion2", "getCarreras");
                doGet(request, response);
                break;

            case "delCarrera":
                id = Integer.parseInt(request.getParameter("id_carrera"));
                
                try {
                    /* Actualizaicón del modelo */
                    m.delCarrera(id);
                } catch (SQLException ex) {
                    Logger.getLogger(AppSevlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AppSevlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("accion2", "getCarreras");
                doGet(request, response);
                break;
                
            default:
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }
    }
}

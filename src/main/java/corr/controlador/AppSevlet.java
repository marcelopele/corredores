package corr.controlador;

import corr.modelo.Modelo;
import corr.modelo.Corredor;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
        accion = accion == null ? "listCorredores" : accion;
        
        switch (accion) {
            
            case "listCorredores": 
                try {
                    request.setAttribute("cardsCorredores", m.getCorredores());
                } catch (SQLException ex) {
                    request.setAttribute("cardsCorredores", "ERROR1");
                } catch (ClassNotFoundException ex) {
                    request.setAttribute("cardsCorredores", "ERROR2");
                }

                request.getRequestDispatcher("main.jsp").forward(request, response);

                break;
                


        }

    }

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            Modelo m = new Modelo();
            Corredor corredor;
            String accion = request.getParameter("accion");
            accion = accion == null ? "listCorredores" : accion;
            
        switch (accion) {
            
            case "listCorredores": 
                doGet(request, response);
                break;
                
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


        }
        
    }

}

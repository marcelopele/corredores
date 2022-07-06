package corr.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
    Corredor corredor = null;
    Conexion cn = new Conexion();
    PreparedStatement pst;
    ResultSet rs;    
    
        public List<Corredor> getCorredores() throws SQLException, ClassNotFoundException {
            List<Corredor> corredores;
            corredores = new ArrayList();
            
            Connection cnn = cn.conectar();
            pst = cnn.prepareStatement("SELECT a.id_corredor, a.nom_corredor, a.ape_corredor, a.img_corredor, Count(b.id_carrera) AS q_carreras, Sum(b.km) AS sumKm, Sum(b.min) AS sumMin, km/(min/60) AS vel_promedio\n" +
"FROM corredores AS a LEFT JOIN carreras AS b ON a.id_corredor = b.id_corredor\n" +
"GROUP BY a.id_corredor, a.nom_corredor, a.ape_corredor, a.img_corredor, vel_promedio");
            rs = pst.executeQuery();
            
            while(rs.next()){
                int id_corredor=rs.getInt("id_corredor");
                String nom_corredor=rs.getString("nom_corredor");
                String ape_corredor=rs.getString("ape_corredor");
                String img_corredor=rs.getString("img_corredor");
                int q_carreras=rs.getInt("q_carreras");
                double vel_corredor=rs.getDouble("vel_promedio");

                corredores.add(new Corredor(id_corredor, nom_corredor, ape_corredor, img_corredor, q_carreras, vel_corredor));
            }
            
            return corredores;
    }
        
        public Corredor getCorredor(int id_corredor) throws SQLException, ClassNotFoundException{
            Connection cnn = cn.conectar();
            pst = cnn.prepareStatement("SELECT * FROM corredores as a WHERE a.id_corredor = "+id_corredor);
            rs = pst.executeQuery();
            while(rs.next()){
                id_corredor=rs.getInt("id_corredor");
                String nom_corredor=rs.getString("nom_corredor");
                String ape_corredor=rs.getString("ape_corredor");
                String img_corredor=rs.getString("img_corredor");

                corredor = new Corredor(id_corredor, nom_corredor, ape_corredor, img_corredor);
            }
            return corredor;
        }
        
        public void addCorredor(Corredor corredor) throws SQLException, ClassNotFoundException{
            Connection cnn = cn.conectar();
            
            String nom_corredor = corredor.getNom_corredor();
            String ape_corredor = corredor.getApe_corredor();
            String img_corredor = corredor.getImg_corredor();

            pst = cnn.prepareStatement("INSERT INTO `corredores`(`nom_corredor`, `ape_corredor`, `img_corredor`) VALUES (?,?,?)");
            pst.setString(1, nom_corredor);
            pst.setString(2, ape_corredor);
            pst.setString(3, img_corredor);
            pst.executeUpdate();

        }
        
        public void updCorredor(Corredor corredor) throws SQLException, ClassNotFoundException{
            Connection cnn = cn.conectar();
            
            int id_corredor = corredor.getId_corredor();
            String nom_corredor = corredor.getNom_corredor();
            String ape_corredor = corredor.getApe_corredor();
            String img_corredor = corredor.getImg_corredor();

            pst = cnn.prepareStatement("UPDATE `corredores` SET `nom_corredor`='"+nom_corredor+"',`ape_corredor`='"+ape_corredor+"',`img_corredor`='"+img_corredor+"' WHERE id_corredor="+id_corredor);
            pst.executeUpdate();

        }
        
        public void delCorredor(int id_corredor) throws SQLException, ClassNotFoundException{
            Connection cnn = cn.conectar();

            pst = cnn.prepareStatement("DELETE FROM `corredores` WHERE id_corredor="+id_corredor);
            pst.executeUpdate();

        }
}

package corr.modelo;

import java.sql.Connection;
import java.sql.Date;
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
            pst = cnn.prepareStatement("SELECT a.id_corredor, a.nom_corredor, a.ape_corredor, a.img_corredor, Count(b.id_carrera) AS q_carreras, Sum(b.km) AS sumKm, Sum(b.min) AS sumMin, Sum(b.km)/(Sum(b.min)/60) AS vel_promedio\n" +
"FROM corredores AS a LEFT JOIN carreras AS b ON a.id_corredor = b.id_corredor\n" +
"GROUP BY a.id_corredor, a.nom_corredor, a.ape_corredor, a.img_corredor");
            rs = pst.executeQuery();
            
            while(rs.next()){
                int id_corredor=rs.getInt("id_corredor");
                String nom_corredor=rs.getString("nom_corredor");
                String ape_corredor=rs.getString("ape_corredor");
                String img_corredor=rs.getString("img_corredor");
                int q_carreras=rs.getInt("q_carreras");
                double vel_corredor_sin_redondear=rs.getDouble("vel_promedio");
                double vel_corredor=Math.round(vel_corredor_sin_redondear*100.0)/100.0;

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
        
        public List<Carrera> getCarreras(int id_corredor) throws SQLException, ClassNotFoundException {
            List<Carrera> carreras;
            carreras = new ArrayList();
            
            Connection cnn = cn.conectar();
            pst = cnn.prepareStatement("SELECT carreras.id_carrera, carreras.tit_carrera, carreras.fh_carrera, carreras.km, carreras.min, carreras.id_corredor FROM carreras\n WHERE carreras.id_corredor="+id_corredor);
            rs = pst.executeQuery();
            
            while(rs.next()){
                int id_carrera=rs.getInt("id_carrera");
                String tit_carrera=rs.getString("tit_carrera");
                String fh_carrera=rs.getString("fh_carrera");
                double km=rs.getDouble("km");
                double min=rs.getDouble("min");

                carreras.add(new Carrera(id_carrera, tit_carrera, fh_carrera, km, min, id_corredor));
            }
            
            return carreras;
        }
        
        public void addCarrera(Carrera carrera) throws SQLException, ClassNotFoundException{
            Connection cnn = cn.conectar();
            
            String tit_carrera = carrera.getTit_carrera();
            String fh_carrera = carrera.getFh_carrera();
            double km = carrera.getKm();
            double min = carrera.getMin();
            int id_corredor = carrera.getId_corredor();

            pst = cnn.prepareStatement("INSERT INTO `carreras`(`tit_carrera`, `fh_carrera`, `km`, `min`, `id_corredor`) VALUES (?,?,?,?,?)");
            pst.setString(1, tit_carrera);
            pst.setString(2, fh_carrera);
            pst.setDouble(3, km);
            pst.setDouble(4, min);
            pst.setInt(5, id_corredor);
            pst.executeUpdate();

        }
        
        public void delCarrera(int id_carrera) throws SQLException, ClassNotFoundException{
            Connection cnn = cn.conectar();

            pst = cnn.prepareStatement("DELETE FROM `carreras` WHERE id_carrera="+id_carrera);
            pst.executeUpdate();

        }
        
}

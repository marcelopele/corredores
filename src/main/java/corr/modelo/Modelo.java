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
    
    private static final String QUERY_getCorredores = "SELECT a.id_corredor, a.nom_corredor, a.ape_corredor, a.img_corredor, Count(b.id_carrera) AS q_carreras, Sum(b.km) AS sumKm, Sum(b.min) AS sumMin, Sum(b.km)/(Sum(b.min)/60) AS vel_promedio FROM corredores AS a LEFT JOIN carreras AS b ON a.id_corredor = b.id_corredor GROUP BY a.id_corredor, a.nom_corredor, a.ape_corredor, a.img_corredor";
    private static final String QUERY_getCorredor = "SELECT * FROM corredores as a WHERE a.id_corredor = ?";
    private static final String QUERY_addCorredor = "INSERT INTO `corredores`(`nom_corredor`, `ape_corredor`, `img_corredor`) VALUES (?,?,?)";
    private static final String QUERY_updCorredor = "UPDATE `corredores` SET `nom_corredor`=?,`ape_corredor`=?,`img_corredor`=? WHERE id_corredor=?";
    private static final String QUERY_delCorredor = "DELETE FROM `corredores` WHERE id_corredor=?";
    
    private static final String QUERY_getCarreras = "SELECT carreras.id_carrera, carreras.tit_carrera, carreras.fh_carrera, carreras.km, carreras.min, carreras.id_corredor FROM carreras WHERE carreras.id_corredor=?";
    private static final String QUERY_addCarrera = "INSERT INTO `carreras`(`tit_carrera`, `fh_carrera`, `km`, `min`, `id_corredor`) VALUES (?,?,?,?,?)";
    private static final String QUERY_delCarrera = "DELETE FROM `carreras` WHERE id_carrera=?";
    private static final String QUERY_updCarrera = "UPDATE `carreras` SET `tit_carrera`=?,`fh_carrera`=?,`km`=?,`min`=?,`id_corredor`=? WHERE id_carrera=?";
    
    
        public List<Corredor> getCorredores() {
            List<Corredor> corredores = new ArrayList();
            
            try(Connection cnn = cn.conectar();
                PreparedStatement pst = cnn.prepareStatement(QUERY_getCorredores);
                ResultSet rs = pst.executeQuery()){
                
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
                
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException("Error de SQL",ex);
            }
            return corredores;
        }
        
        public Corredor getCorredor(int id_corredor){
            try(Connection cnn = cn.conectar();
                PreparedStatement pst = cnn.prepareStatement(QUERY_getCorredor);){
                
                pst.setInt(1, id_corredor);
                try(ResultSet rs = pst.executeQuery();){
                    while(rs.next()){
                        id_corredor=rs.getInt("id_corredor");
                        String nom_corredor=rs.getString("nom_corredor");
                        String ape_corredor=rs.getString("ape_corredor");
                        String img_corredor=rs.getString("img_corredor");

                        corredor = new Corredor(id_corredor, nom_corredor, ape_corredor, img_corredor);
                    }
                }
                
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException("Error de SQL",ex);
            }
            return corredor;
        }
        
        public void addCorredor(Corredor corredor) {
            try(Connection cnn = cn.conectar();
                PreparedStatement pst = cnn.prepareStatement(QUERY_addCorredor);){
                
                String nom_corredor = corredor.getNom_corredor();
                String ape_corredor = corredor.getApe_corredor();
                String img_corredor = corredor.getImg_corredor();

                pst.setString(1, nom_corredor);
                pst.setString(2, ape_corredor);
                pst.setString(3, img_corredor);
                pst.executeUpdate();
                
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException("Error de SQL",ex);
            }
        }
        
        public void updCorredor(Corredor corredor) {
            try(Connection cnn = cn.conectar();
                PreparedStatement pst = cnn.prepareStatement(QUERY_updCorredor);){
                
                int id_corredor = corredor.getId_corredor();
                String nom_corredor = corredor.getNom_corredor();
                String ape_corredor = corredor.getApe_corredor();
                String img_corredor = corredor.getImg_corredor();

                pst.setString(1, nom_corredor);
                pst.setString(2, ape_corredor);
                pst.setString(3, img_corredor);
                pst.setInt(4, id_corredor);

                pst.executeUpdate();
                
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException("Error de SQL",ex);
            }
        }
        
        public void delCorredor(int id_corredor) {
            try(Connection cnn = cn.conectar();
                PreparedStatement pst = cnn.prepareStatement(QUERY_delCorredor);){
                
                pst.setInt(1, id_corredor);
                pst.executeUpdate();
                
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException("Error de SQL",ex);
            }
        }
        
        public List<Carrera> getCarreras(int id_corredor) {
            List<Carrera> carreras = new ArrayList();
            
            try(Connection cnn = cn.conectar();
                PreparedStatement pst = cnn.prepareStatement(QUERY_getCarreras);){
                
                pst.setInt(1, id_corredor);
                try(ResultSet rs = pst.executeQuery();){
                    while(rs.next()){
                        int id_carrera=rs.getInt("id_carrera");
                        String tit_carrera=rs.getString("tit_carrera");
                        String fh_carrera=rs.getString("fh_carrera");
                        double km=rs.getDouble("km");
                        double min=rs.getDouble("min");

                        carreras.add(new Carrera(id_carrera, tit_carrera, fh_carrera, km, min, id_corredor));
                    }
                }
                
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException("Error de SQL",ex);
            }
            return carreras;
        }
        
        public void addCarrera(Carrera carrera) {
            try(Connection cnn = cn.conectar();
                PreparedStatement pst = cnn.prepareStatement(QUERY_addCarrera);){
                
                String tit_carrera = carrera.getTit_carrera();
                String fh_carrera = carrera.getFh_carrera();
                double km = carrera.getKm();
                double min = carrera.getMin();
                int id_corredor = carrera.getId_corredor();

                pst.setString(1, tit_carrera);
                pst.setString(2, fh_carrera);
                pst.setDouble(3, km);
                pst.setDouble(4, min);
                pst.setInt(5, id_corredor);
                pst.executeUpdate();
                
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException("Error de SQL",ex);
            }
        }
        
        public void delCarrera(int id_carrera) {
            try(Connection cnn = cn.conectar();
                PreparedStatement pst = cnn.prepareStatement(QUERY_delCarrera);){
                
                pst.setInt(1, id_carrera);
                pst.executeUpdate();
                
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException("Error de SQL",ex);
            }
        }
        
        public void updCarrera(Carrera carrera) throws SQLException, ClassNotFoundException{
            try(Connection cnn = cn.conectar();
                PreparedStatement pst = cnn.prepareStatement(QUERY_updCarrera);){
                
                int id_carrera = carrera.getId_carrera();
                String tit_carrera = carrera.getTit_carrera();
                String fh_carrera = carrera.getFh_carrera();
                double km = carrera.getKm();
                double min = carrera.getMin();
                int id_corredor = carrera.getId_corredor();

                pst.setString(1, tit_carrera);
                pst.setString(2, fh_carrera);
                pst.setDouble(3, km);
                pst.setDouble(4, min);
                pst.setInt(5, id_corredor);
                pst.setInt(6, id_carrera);
                pst.executeUpdate();
                
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException("Error de SQL",ex);
            }
        }
        
        
}

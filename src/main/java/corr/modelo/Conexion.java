package corr.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private final String base="cac_runners";
    private final String usr="root";
    private final String pwd="";
    private final String url="jdbc:mysql://localhost:3306/" + base;
    
    public Connection conectar() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection cn = DriverManager.getConnection(url, usr, pwd);

        return cn;
    }
    
}

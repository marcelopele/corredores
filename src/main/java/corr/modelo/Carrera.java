package corr.modelo;

import java.sql.Date;
import java.time.LocalDate;

public class Carrera {
    private int id_carrera;
    private String tit_carrera;
    private String fh_carrera;
    private double km;
    private double min;
    private int id_corredor;
    
    public Carrera(){}

    public Carrera(int id_carrera, String tit_carrera, String fh_carrera, double km, double min, int id_corredor) {
        setId_carrera(id_carrera);
        setTit_carrera(tit_carrera);
        setFh_carrera(fh_carrera);
        setKm(km);
        setMin(min);
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public void setTit_carrera(String tit_carrera) {
        this.tit_carrera = tit_carrera;
    }

    public void setFh_carrera(String fh_carrera) {
        this.fh_carrera = fh_carrera;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public void setMin(double min) {
        this.min = min;
    }
    
    public void setId_corredor(int id_corredor) {
        this.id_corredor = id_corredor;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public String getTit_carrera() {
        return tit_carrera;
    }

    public String getFh_carrera() {
        return fh_carrera;
    }

    public double getKm() {
        return km;
    }

    public double getMin() {
        return min;
    }
    
    public int getId_corredor() {
        return id_corredor;
    }
    
}

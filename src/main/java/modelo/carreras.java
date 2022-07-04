package modelo;

import java.time.LocalDate;

public class carreras {
    private int id_carrera;
    private String tit_carrera;
    private LocalDate fh_carrera;
    private double km;
    private double min;
    
    public carreras(){}

    public carreras(int id_carrera, String tit_carrera, LocalDate fh_carrera, double km, double min) {
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

    public void setFh_carrera(LocalDate fh_carrera) {
        this.fh_carrera = fh_carrera;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public String getTit_carrera() {
        return tit_carrera;
    }

    public LocalDate getFh_carrera() {
        return fh_carrera;
    }

    public double getKm() {
        return km;
    }

    public double getMin() {
        return min;
    }

}

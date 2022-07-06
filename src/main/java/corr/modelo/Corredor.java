package corr.modelo;

public class Corredor {
    private int id_corredor;
    private String nom_corredor;
    private String ape_corredor;
    private String img_corredor;
    private int q_carreras;
    private double vel_promedio;
    
    public Corredor(){}

    public Corredor(int id_corredor, String nom_corredor, String ape_corredor, String img_corredor) {
        setId_corredor(id_corredor);
        setNom_corredor(nom_corredor);
        setApe_corredor(ape_corredor);
        setImg_corredor(img_corredor);
    }

    public Corredor(int id_corredor, String nom_corredor, String ape_corredor, String img_corredor, int q_carreras, double vel_promedio) {
        setId_corredor(id_corredor);
        setNom_corredor(nom_corredor);
        setApe_corredor(ape_corredor);
        setImg_corredor(img_corredor);
        setQ_carreras(q_carreras);
        setVel_promedio(vel_promedio);
    }

    public void setId_corredor(int id_corredor) {
        this.id_corredor = id_corredor;
    }

    public void setNom_corredor(String nom_corredor) {
        this.nom_corredor = nom_corredor;
    }

    public void setApe_corredor(String ape_corredor) {
        this.ape_corredor = ape_corredor;
    }

    public void setImg_corredor(String img_corredor) {
        this.img_corredor = img_corredor;
    }

    public void setQ_carreras(int q_carreras) {
        this.q_carreras = q_carreras;
    }
    
    public void setVel_promedio(double vel_promedio) {
        this.vel_promedio = vel_promedio;
    }

    public int getId_corredor() {
        return id_corredor;
    }

    public String getNom_corredor() {
        return nom_corredor;
    }

    public String getApe_corredor() {
        return ape_corredor;
    }

    public String getNombreCompleto() {
        return nom_corredor + " " + ape_corredor;
    }
    
    public String getImg_corredor() {
        return img_corredor;
    }

    public int getQ_carreras() {
        return q_carreras;
    }
    
    public double getVel_promedio() {
        return vel_promedio;
    }
    
}

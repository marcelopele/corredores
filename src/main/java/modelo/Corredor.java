package modelo;

public class Corredor {
    private int id_corredor;
    private String nom_corredor;
    private String ape_corredor;
    private String img_corredor;
    
    public Corredor(){}

    public Corredor(int id_corredor, String nom_corredor, String ape_corredor, String img_corredor) {
        setId_corredor(id_corredor);
        setNom_corredor(nom_corredor);
        setApe_corredor(ape_corredor);
        setImg_corredor(img_corredor);
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

    public int getId_corredor() {
        return id_corredor;
    }

    public String getNom_corredor() {
        return nom_corredor;
    }

    public String getApe_corredor() {
        return ape_corredor;
    }

    public String getImg_corredor() {
        return img_corredor;
    }


    
    
}

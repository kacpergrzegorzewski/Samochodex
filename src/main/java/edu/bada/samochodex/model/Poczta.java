package edu.bada.samochodex.model;

public class Poczta {

    private int id_poczty;
    private String kod_pocztowy;
    private String poczta;

    public Poczta() {}

    public Poczta(int id_poczty, String kod_pocztowy, String poczta) {
        this.id_poczty = id_poczty;
        this.kod_pocztowy = kod_pocztowy;
        this.poczta = poczta;
    }

    public int getId_poczty() {
        return id_poczty;
    }

    public void setId_poczty(int id_poczty) {
        this.id_poczty = id_poczty;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getPoczta() {
        return poczta;
    }

    public void setPoczta(String poczta) {
        this.poczta = poczta;
    }

    @Override
    public String toString() {
        return "Poczta{" +
                "id_poczty=" + id_poczty +
                ", kod_pocztowy='" + kod_pocztowy + '\'' +
                ", poczta='" + poczta + '\'' +
                '}';
    }
}

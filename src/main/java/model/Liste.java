package model;

public class Liste {
    public int getIdListe() {
        return idListe;
    }

    public void setIdListe(int idListe) {
        this.idListe = idListe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private int idListe;
    private String nom;

    public Liste(int idListe, String nom) {
        this.idListe = idListe;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Liste{" +
                "idListe=" + idListe +
                ", nom='" + nom + '\'' +
                '}';
    }
}

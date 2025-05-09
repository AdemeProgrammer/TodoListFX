package model;

public class Liste {
    private int idListe;
    private String nom;
    private int idUtilisateur;

    public Liste(int idListe, String nom, int idUtilisateur) {
        this.idListe = idListe;
        this.nom = nom;
        this.idUtilisateur = idUtilisateur;
    }

    public Liste(String nom, int idUtilisateur) {
        this.nom = nom;
        this.idUtilisateur = idUtilisateur;
    }

    public Liste(int idListe, String nom) {
        this.idListe = idListe;
        this.nom = nom;
    }

    public Liste(String nom) {
        this.nom = nom;
    }

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

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public String toString() {
        return "Liste{" +
                "idListe=" + idListe +
                ", nom='" + nom + '\'' +
                ", idUtilisateur=" + idUtilisateur +
                '}';
    }
}

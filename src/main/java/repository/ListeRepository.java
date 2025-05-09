package repository;

import database.Database;
import model.Liste;
import model.Utilisateur;
import session.SessionUtilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListeRepository {
    private Connection cnx;

    public ListeRepository() {
        this.cnx = Database.getConnexion();
    }

    public boolean ajouterListe(Liste liste) {
        Utilisateur utilisateurConnecte = SessionUtilisateur.getInstance().getUtilisateur();

        if (utilisateurConnecte != null) {
            int idUtilisateur = utilisateurConnecte.getIdUtilisateur();
            liste.setIdUtilisateur(idUtilisateur);
            String sqlListe = "INSERT INTO liste (nom) VALUES (?)";
            try {
                PreparedStatement stmt = cnx.prepareStatement(sqlListe, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, liste.getNom());
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int idListe = rs.getInt(1);
                    String sqlRelation = "INSERT INTO utilisateur_liste (ref_utilisateur, ref_liste) VALUES (?, ?)";
                    PreparedStatement stmtRelation = cnx.prepareStatement(sqlRelation);
                    stmtRelation.setInt(1, idUtilisateur);
                    stmtRelation.setInt(2, idListe);
                    stmtRelation.executeUpdate();

                    System.out.println("Liste ajoutée et associée à l'utilisateur avec succès !");
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'ajout de la liste : " + e.getMessage());
            }
        } else {
            System.out.println("Aucun utilisateur connecté !");
        }
        return false;
    }

    public Liste getListeParId(int idListe) {
        String sql = "SELECT * FROM liste WHERE id_liste = ?";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setInt(1, idListe);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Liste(
                        rs.getInt("id_liste"),
                        rs.getString("nom"),
                        rs.getInt("id_utilisateur")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la liste : " + e.getMessage());
        }
        return null;
    }

    public boolean supprimerListeParId(int idListe) {
        String sql = "DELETE FROM liste WHERE id_liste = ?";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setInt(1, idListe);
            stmt.executeUpdate();
            System.out.println("Liste supprimée avec succès !");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la liste : " + e.getMessage());
        }
        return false;
    }

    public boolean mettreAJourListe(Liste liste) {
        String sql = "UPDATE liste SET nom = ?, id_utilisateur = ? WHERE id_liste = ?";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setString(1, liste.getNom());
            stmt.setInt(2, liste.getIdUtilisateur());
            stmt.setInt(3, liste.getIdListe());
            stmt.executeUpdate();
            System.out.println("Liste mise à jour avec succès !");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la liste : " + e.getMessage());
        }
        return false;
    }

    public List<Liste> findAll() {
        List<Liste> listes = new ArrayList<>();
        String sql = "SELECT * FROM liste";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Liste l = new Liste(
                        rs.getInt("id_liste"),
                        rs.getString("nom"),
                        rs.getInt("id_utilisateur")
                );
                listes.add(l);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des listes : " + e.getMessage());
        }

        return listes;
    }

    public List<Liste> getListesParUtilisateur(int idUtilisateur) {
        List<Liste> listes = new ArrayList<>();

        Utilisateur utilisateurConnecte = SessionUtilisateur.getInstance().getUtilisateur();

        if (utilisateurConnecte != null) {
            idUtilisateur = utilisateurConnecte.getIdUtilisateur();
            String sql = "SELECT l.*,ul.* FROM liste l " +
                    "JOIN utilisateur_liste ul ON l.id_liste = ul.ref_liste " +
                    "WHERE ul.ref_utilisateur = ?";
            try {
                PreparedStatement stmt = cnx.prepareStatement(sql);
                stmt.setInt(1, idUtilisateur);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Liste liste = new Liste(
                            rs.getInt("id_liste"),
                            rs.getString("nom"),
                            rs.getInt("ref_utilisateur")
                    );
                    listes.add(liste);
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la récupération des listes : " + e.getMessage());
            }
        } else {
            System.out.println("Aucun utilisateur connecté !");
        }
        return listes;
    }
}

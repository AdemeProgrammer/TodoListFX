package repository;

import database.Database;
import model.Liste;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListeRepository {
    private Connection cnx;

    public ListeRepository() {
        this.cnx = Database.getConnexion();
    }

    public boolean ajouterListe(Liste liste) {
        String sql = "INSERT INTO liste (nom, id_utilisateur) VALUES (?, ?)";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setString(1, liste.getNom());
            stmt.setInt(2, liste.getIdUtilisateur());
            stmt.executeUpdate();
            System.out.println("Liste ajoutée avec succès !");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la liste : " + e.getMessage());
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
        String sql = "SELECT * FROM liste WHERE id_utilisateur = ?";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setInt(1, idUtilisateur);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Liste liste = new Liste(
                        rs.getInt("id_liste"),
                        rs.getString("nom")
                );
                listes.add(liste);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des listes : " + e.getMessage());
        }
        return listes;
    }
}

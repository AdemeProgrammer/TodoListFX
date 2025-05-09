package repository;

import database.Database;
import model.Tache;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TacheRepository {
    private Connection cnx;

    public TacheRepository() {
        this.cnx = Database.getConnexion();
    }

    public boolean ajouterTache(Tache tache) {
        String sql = "INSERT INTO tache (nom, etat, ref_liste, ref_type) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setString(1, tache.getNom());
            stmt.setInt(2, tache.getEtat());
            stmt.setInt(3, tache.getRefListe());
            stmt.setInt(4, tache.getRefType());
            stmt.executeUpdate();
            System.out.println("Tâche ajoutée avec succès !");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la tâche : " + e.getMessage());
        }
        return false;
    }

    public Tache getTacheParId(int idTache) {
        String sql = "SELECT * FROM tache WHERE id_tache = ?";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setInt(1, idTache);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Tache(
                        rs.getInt("id_tache"),
                        rs.getString("nom"),
                        rs.getInt("etat"),
                        rs.getInt("ref_liste"),
                        rs.getInt("ref_type")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la tâche : " + e.getMessage());
        }
        return null;
    }

    public boolean supprimerTacheParId(int idTache) {
        String sql = "DELETE FROM tache WHERE id_tache = ?";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setInt(1, idTache);
            stmt.executeUpdate();
            System.out.println("Tâche supprimée avec succès !");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la tâche : " + e.getMessage());
        }
        return false;
    }

    public boolean mettreAJourTache(Tache tache) {
        String sql = "UPDATE tache SET nom = ?, etat = ?, ref_liste = ?, ref_type = ? WHERE id_tache = ?";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setString(1, tache.getNom());
            stmt.setInt(2, tache.getEtat());
            stmt.setInt(3, tache.getRefListe());
            stmt.setInt(4, tache.getRefType());
            stmt.setInt(5, tache.getIdTache());
            stmt.executeUpdate();
            System.out.println("Tâche mise à jour avec succès !");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la tâche : " + e.getMessage());
        }
        return false;
    }

    public List<Tache> findAll() {
        List<Tache> taches = new ArrayList<>();
        String sql = "SELECT * FROM tache";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Tache t = new Tache(
                        rs.getInt("id_tache"),
                        rs.getString("nom"),
                        rs.getInt("etat"),
                        rs.getInt("ref_liste"),
                        rs.getInt("ref_type")
                );
                taches.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des tâches : " + e.getMessage());
        }

        return taches;
    }
}
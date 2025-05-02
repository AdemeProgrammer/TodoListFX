package repository;

import database.Database;
import java.sql.PreparedStatement;
import model.Utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;

public class UtilisateurRepository {
    private Connection cnx;
    public UtilisateurRepository() {
        this.cnx = Database.getConnexion();
    }

    public boolean ajouterUtilisateur(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, role) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.cnx.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getMotDePasse());
            stmt.setString(5, utilisateur.getRole());
            stmt.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès !");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
        return false;
    }

    public Utilisateur getUtilisateurParEmail(String email) {
        String sql = "SELECT * FROM utilisateur WHERE email = ?";
        try{
            PreparedStatement stmt = this.cnx.prepareStatement(sql);
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                System.out.println("Utilisateur trouvé ! Let's goooooo !");
                Utilisateur user = new Utilisateur(
                        rs.getInt("id_utilisateur"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role")
                );
                return user;
            }
            System.out.println("Utilisateur non trouvé !");
        }catch (SQLException e){
            System.out.println("Erreur lors de l'utilisateur : " + e.getMessage());
        }
        return null;
    }

    public boolean supprimerUtilisateurParEmail(String email) {
        String sql = "DELETE FROM utilisateur WHERE email = ?";
        try {
            PreparedStatement stmt = this.cnx.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.executeUpdate();
            System.out.println("Utilisateur supprimé avec succès !");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        }
        return false;
    }

    public boolean mettreAJourUtilisateur(Utilisateur utilisateur) {
        String sql = "UPDATE utilisateur SET nom = ?, prenom = ?, mot_de_passe = ? WHERE email = ?";
        try {
            PreparedStatement stmt = this.cnx.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getMotDePasse());
            stmt.setString(4, utilisateur.getEmail());
            stmt.executeUpdate();
            System.out.println("Utilisateur mis à jour avec succès !");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
        }
        return false;
    }

    public ArrayList<Utilisateur> getTousLesUtilisateurs() {
        String sql = "SELECT * FROM utilisateur";
        System.out.println(sql);
        return null;
    }

    public class InscriptionController {
        private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
    }

    public class UpdateController {
        private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
    }

    public class DeleteController {
        private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
    }
}

package repository;

import database.Database;
import model.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeRepository {
    private Connection cnx;

    public TypeRepository() {
        this.cnx = Database.getConnexion();
    }

    public boolean ajouterType(Type type) {
        String sql = "INSERT INTO type (nom, code_couleur) VALUES (?, ?)";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setString(1, type.getNom());
            stmt.setString(2, type.getCodeCouleur());
            stmt.executeUpdate();
            System.out.println("Type ajouté avec succès !");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du type : " + e.getMessage());
        }
        return false;
    }

    public Type getTypeParId(int idType) {
        String sql = "SELECT * FROM type WHERE id_type = ?";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setInt(1, idType);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Type(
                        rs.getInt("id_type"),
                        rs.getString("nom"),
                        rs.getString("code_couleur")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du type : " + e.getMessage());
        }
        return null;
    }

    public boolean mettreAJourType(Type type) {
        String sql = "UPDATE type SET nom = ?, code_couleur = ? WHERE id_type = ?";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setString(1, type.getNom());
            stmt.setString(2, type.getCodeCouleur());
            stmt.setInt(3, type.getIdType());
            stmt.executeUpdate();
            System.out.println("Type mis à jour avec succès !");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du type : " + e.getMessage());
        }
        return false;
    }

    public boolean supprimerTypeParId(int idType) {
        String sql = "DELETE FROM type WHERE id_type = ?";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setInt(1, idType);
            stmt.executeUpdate();
            System.out.println("Type supprimé avec succès !");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du type : " + e.getMessage());
        }
        return false;
    }

    public List<Type> findAll() {
        List<Type> types = new ArrayList<>();
        String sql = "SELECT * FROM type";
        try {
            PreparedStatement stmt = cnx.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Type type = new Type(
                        rs.getInt("id_type"),
                        rs.getString("nom"),
                        rs.getString("code_couleur")
                );
                types.add(type);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des types : " + e.getMessage());
        }
        return types;
    }
}

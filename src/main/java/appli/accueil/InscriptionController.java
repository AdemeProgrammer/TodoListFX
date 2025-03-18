package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import database.Database;
import model.Utilisateur;
import repository.UtilisateurRepository;
import java.sql.Connection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InscriptionController {

    private UtilisateurRepository repo= new UtilisateurRepository();

    @FXML
    private Button btnConnexion;

    @FXML
    private Button btnInscription;

    @FXML
    private TextField confirmationField;

    @FXML
    private TextField emailField;

    @FXML
    private Label error;

    @FXML
    private TextField motDePasseField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    void onIConnexionButtonClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/login");
    }

@FXML
void onInscriptionButtonClick (ActionEvent event) throws IOException {
        if(confirmationField.getText().isEmpty()||
                motDePasseField.getText().isEmpty()||
                emailField.getText().isEmpty()||
                prenomField.getText().isEmpty()||
                nomField.getText().isEmpty()){
            System.out.println("Il faut que tous les champs soient renseignés !");
            error.setText("Il faut que tous les champs soient renseignés !");
        }else if (!confirmationField.getText().equals(motDePasseField.getText())){
            System.out.println("La confirmation n'est pas égal au mot de passe !");
            error.setText("La confirmation n'est pas égale au mot de passe !");
        }else{
            Utilisateur user = new Utilisateur(nomField.getText(),prenomField.getText(),emailField.getText(),motDePasseField.getText(),"utilisateur");
            boolean estInscrit = repo.ajouterUtilisateur(user);
            if (estInscrit) {
                System.out.println("Inscription réussie");
                error.setText("Inscription réussie !");
                StartApplication.changeScene("accueil/login");
            }else{
                System.out.println("Erreur lors de l'inscriiption");
                error.setText("Erreur lors de l'inscription");
            }
        }
    }


    public Button getBtnConnexion() {
        return btnConnexion;
    }

    public void setBtnConnexion(Button btnConnexion) {
        this.btnConnexion = btnConnexion;
    }

    public Button getBtnInscription() {
        return btnInscription;
    }

    public void setBtnInscription(Button btnInscription) {
        this.btnInscription = btnInscription;
    }
}

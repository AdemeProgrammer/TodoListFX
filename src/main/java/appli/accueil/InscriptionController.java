package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class InscriptionController {

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
    void onSInscrireButtonClick(ActionEvent event) {
        System.out.println("Nom"+nomField.getText());
        System.out.println("Prenom"+prenomField.getText());
        System.out.println("Email"+ emailField.getText());
        System.out.println("Password"+ motDePasseField.getText());
        System.out.println("Confirmation"+ confirmationField.getText());
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String password =  motDePasseField.getText();
        String confirmation = confirmationField.getText();
            if( nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || password.isEmpty() || confirmation.isEmpty() ){
                error.setText("Veuillez remplir tous les champs");
            }else if ( !nom.isEmpty() || !prenom.isEmpty() || !email.isEmpty() || !password.isEmpty() || !confirmation.isEmpty() ){
                error.setText("Inscription réussie");
            }else{
                error.setText("Il y a un problème quelque part");
            }

    }


}

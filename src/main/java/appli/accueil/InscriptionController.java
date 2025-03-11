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
    private TextField emailField;

    @FXML
    private Label error;

    @FXML
    private TextField motDePasseField;

    void onInscriptionButtonClick (ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/inscription");
    }

    @FXML
    void onLoginButtonClick(ActionEvent event) {
        System.out.println("Email"+ emailField.getText());
        System.out.println("Password"+ motDePasseField.getText());
        String email = emailField.getText();
        String password =  motDePasseField.getText();
        if(email.isEmpty() || password.isEmpty()) {
            error.setText("Veuillez remplir tous les champs");
        }else if (!password.isEmpty() && !email.isEmpty()){
            error.setText("Inscription réussie");
        }else{
            error.setText("Il y a un problème quelque part");
        }

    }
}

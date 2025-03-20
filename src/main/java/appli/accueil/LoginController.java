package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;
import model.Utilisateur;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    private UtilisateurRepository repo= new UtilisateurRepository();

    @FXML
    private Button btnConnexion;

    @FXML
    private Button btnInscription;

    @FXML
    private Button btnMotDePasseOublie;

    @FXML
    private TextField emailField;

    @FXML
    private Label error;

    @FXML
    private TextField motDePasseField;

    @FXML
    private Label motDePasseOublie;

    @FXML
    void onSInscrireButtonClick (ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/inscription");
    }

    @FXML
    void onConnexionButtonClick(ActionEvent event) throws IOException {
        System.out.println("Email"+ emailField.getText());
        System.out.println("Password"+ motDePasseField.getText());
        String email = emailField.getText();
        String password =  motDePasseField.getText();
        if(email.isEmpty() || password.isEmpty()) {
           error.setText("Veuillez remplir tous les champs");
        }else{
            Utilisateur user = repo.getUtilisateurParEmail(email);
            if (user == null) {
                error.setText("Les informations fournies ne vous permettent pas de vous identifier !");

            }else if (!user.getMotDePasse().equals(password)) {
                error.setText("Veuillez remplir tous les champs");
            }else {
                error.setText("Connexion réussie !");
                System.out.println("Connexion réussie");
                System.out.println("Petite redirection des familles...");
                StartApplication.changeScene("accueil/main");
            }
        }

    }

    @FXML
    void onMotDePasseOublieButtonClick(ActionEvent event) {

    }

}

package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Utilisateur;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UtilisateurRepository;
import session.SessionUtilisateur;

import java.io.IOException;

public class LoginController {
    private final UtilisateurRepository repo = new UtilisateurRepository();

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
    void handleLogin(ActionEvent event) throws IOException {
        System.out.println("Email: " + emailField.getText());
        System.out.println("Password: " + motDePasseField.getText());

        String email = emailField.getText();
        String password = motDePasseField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            error.setText("Veuillez remplir tous les champs");
        } else {
            Utilisateur utilisateur = repo.getUtilisateurParEmail(email);
            if (utilisateur != null && new BCryptPasswordEncoder().matches(password, utilisateur.getMotDePasse())) {
                System.out.println("Connexion réussie pour : " + utilisateur.getNom());
                SessionUtilisateur.getInstance().sauvegardeSession(utilisateur);
                error.setVisible(false);
                StartApplication.changeScene("accueil/main"); // Redirection vers la page d'accueil
            } else {
                System.out.println("Échec de la connexion. Email ou mot de passe incorrect.");
                error.setText("Email ou mot de passe incorrect.");
                error.setVisible(true);
            }
        }
    }

    @FXML
    void onSInscrireButtonClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/inscription");
    }

    @FXML
    void onMotDePasseOublieButtonClick(ActionEvent event) {
    }
}

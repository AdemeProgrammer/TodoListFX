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

import java.io.IOException;

public class UpdateController {

    @FXML
    private Button btnRetourAccueil;

    @FXML
    private Button btnUpdate;

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
    void onRetourAccueilButtonClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/main");
    }

    @FXML
    void onUpdateButtonClick(ActionEvent event) throws IOException {
        if (motDePasseField.getText().isEmpty() ||
                emailField.getText().isEmpty() ||
                prenomField.getText().isEmpty() ||
                nomField.getText().isEmpty()) {
            System.out.println("Il faut que tous les champs soient renseignés !");
            error.setText("Il faut que tous les champs soient renseignés !");
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String motDePasseHashe = passwordEncoder.encode(motDePasseField.getText());
            Utilisateur user = new Utilisateur(nomField.getText(), prenomField.getText(), emailField.getText(), motDePasseHashe, "utilisateur");
//            boolean estUpdate = repo.mettreAJourUtilisateur(email);
//            if (estUpdate) {
                System.out.println("Modification de compte réussie");
                error.setText("Modification de compte réussie !");
                StartApplication.changeScene("accueil/main");
//            } else {
                System.out.println("Erreur lors de la modification de compte");
                error.setText("Erreur lors de la modification de compte");
            }
        }
    }




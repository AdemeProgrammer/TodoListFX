package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import session.SessionUtilisateur;

import java.io.IOException;

public class MainController {

    @FXML
    private Button btnDeconnexion;

    @FXML
    private Button btnListe;

    @FXML
    private Button btnModif;

    @FXML
    private Button btnSuppression;

    @FXML
    void onDeconnexionButtonClick(ActionEvent event) throws IOException {
        SessionUtilisateur.getInstance().deconnecter();
        System.out.println("Utilisateur déconnecté.");
        StartApplication.changeScene("accueil/login");
        //Petite redirection des familles vers la page de connexion
    }

    @FXML
    void onListeButtonClick(ActionEvent event) {

    }

    @FXML
    void onModifButtonClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/update");
    }

    @FXML
    void onSupressionButtonClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/delete");
    }

}
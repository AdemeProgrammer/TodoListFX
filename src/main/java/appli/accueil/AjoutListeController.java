package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Liste;
import model.Utilisateur;
import repository.ListeRepository;
import session.SessionUtilisateur;

import java.io.IOException;

public class AjoutListeController {

    @FXML
    private TextField champNomListe;

    @FXML
    private Button btnCreer;

    @FXML
    private Button btnAnnuler;

    private ListeRepository listeRepository = new ListeRepository();

    @FXML
    void onCreerListeClick() throws IOException {
        String nomListe = champNomListe.getText().trim();

        if (nomListe.isEmpty()) {
            afficherAlerte("Le nom de la liste ne peut pas être vide.");
            return;
        }

        Utilisateur utilisateur = SessionUtilisateur.getInstance().getUtilisateur();

        if (utilisateur == null) {
            afficherAlerte("Aucun utilisateur connecté.");
            StartApplication.changeScene("accueil/login");
            return;
        }

        Liste nouvelleListe = new Liste(0, nomListe, utilisateur.getIdUtilisateur());

        boolean ajoutOK = listeRepository.ajouterListe(nouvelleListe);

        if (ajoutOK) {
            StartApplication.changeScene("accueil/gestionListeView");
        } else {
            afficherAlerte("Erreur lors de l'ajout de la liste.");
        }
    }

    @FXML
    void onAnnulerClick() throws IOException {
        StartApplication.changeScene("accueil/gestionListeView");
    }

    private void afficherAlerte(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

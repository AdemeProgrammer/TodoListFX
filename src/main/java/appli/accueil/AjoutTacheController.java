package appli.accueil;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Tache;
import repository.TacheRepository;

import java.io.IOException;

public class AjoutTacheController {

    @FXML
    private TextField champNomTache;

    @FXML
    private ComboBox<String> comboEtat;

    @FXML
    private Button btnCreer;

    @FXML
    private Button btnAnnuler;

    private TacheRepository tacheRepository = new TacheRepository();

    private int refListe = 1;  // À adapter dynamiquement selon le contexte

    private int refType = 1;   // À adapter selon votre logique métier

    @FXML
    public void initialize() {
        comboEtat.getItems().addAll("À faire", "En cours", "Terminée");
        comboEtat.getSelectionModel().select(0);
    }

    @FXML
    void onCreerTacheClick() throws IOException {
        String nom = champNomTache.getText().trim();
        int etat = comboEtat.getSelectionModel().getSelectedIndex();

        if (nom.isEmpty()) {
            afficherAlerte("Le nom de la tâche ne peut pas être vide.");
            return;
        }

        Tache nouvelleTache = new Tache(nom, etat, refListe, refType);
        boolean success = tacheRepository.ajouterTache(nouvelleTache);

        if (success) {
            StartApplication.changeScene("accueil/gestionTache");
        } else {
            afficherAlerte("Erreur lors de l'ajout de la tâche.");
        }
    }

    @FXML
    void onAnnulerClick() throws IOException {
        StartApplication.changeScene("accueil/main");
    }

    private void afficherAlerte(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


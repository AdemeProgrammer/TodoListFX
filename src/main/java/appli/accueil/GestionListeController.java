package appli.accueil;

import appli.StartApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Liste;
import repository.ListeRepository;
import session.SessionUtilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GestionListeController implements Initializable {

    @FXML
    private TableView<Liste> tableauListe;

    @FXML
    private Button Accueil;

    @FXML
    private Button Deconnexion;

    @FXML
    private Button Supprimer;

    @FXML
    void onAccueilButtonClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/main");
    }

    @FXML
    void onDeconnexionButtonClick(ActionEvent event) throws IOException {
        SessionUtilisateur.getInstance().deconnecter();
        System.out.println("Utilisateur déconnecté.");
        StartApplication.changeScene("accueil/login");
    }

    @FXML
    void onSupprimerButtonClick(ActionEvent event) throws IOException {
        Liste selection = tableauListe.getSelectionModel().getSelectedItem();
        if (selection != null) {
            ListeRepository repo = new ListeRepository();
            repo.supprimerListeParId(selection.getIdListe());
            tableauListe.getItems().remove(selection);
        }
    }

    @FXML
    void cliqueTableauEvent(javafx.scene.input.MouseEvent event) {
        Liste selection = tableauListe.getSelectionModel().getSelectedItem();
        Supprimer.setDisable(selection == null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String[][] colonnes = {
                { "Id Liste", "idListe" },
                { "Nom", "nom" }
        };

        for (String[] col : colonnes) {
            TableColumn<Liste, String> tableColumn = new TableColumn<>(col[0]);
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(col[1]));
            tableauListe.getColumns().add(tableColumn);
        }

        int idUtilisateur = SessionUtilisateur.getInstance().getUtilisateur().getIdUtilisateur();

        // Charger les listes de cet utilisateur
        ListeRepository listeRepository = new ListeRepository();
        List<Liste> listes = listeRepository.getListesParUtilisateur(idUtilisateur);

        tableauListe.setItems(FXCollections.observableArrayList(listes));
    }

}

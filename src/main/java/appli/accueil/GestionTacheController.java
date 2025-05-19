package appli.accueil;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Tache;
import repository.TacheRepository;
import appli.StartApplication;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GestionTacheController implements Initializable {

    @FXML
    private TableView<Tache> tableauTache;

    @FXML
    private Button btnAccueil;

    @FXML
    private Button btnSupprimer;

    private TacheRepository tacheRepository = new TacheRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[][] colonnes = {
                {"Id", "idTache"},
                {"Nom", "nom"},
                {"État", "etat"},
                {"Liste", "refListe"},
                {"Type", "refType"}
        };

        for (String[] col : colonnes) {
            TableColumn<Tache, String> tableColumn = new TableColumn<>(col[0]);
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(col[1]));
            tableauTache.getColumns().add(tableColumn);
        }

        List<Tache> taches = tacheRepository.findAll();
        tableauTache.setItems(FXCollections.observableArrayList(taches));
    }

    @FXML
    void onAccueilClick() throws IOException {
        StartApplication.changeScene("accueil/main");
    }

    @FXML
    void onSupprimerClick() {
        Tache selection = tableauTache.getSelectionModel().getSelectedItem();
        if (selection != null) {
            tacheRepository.supprimerTacheParId(selection.getIdTache());
            tableauTache.getItems().remove(selection);
        }
    }

    @FXML
    void cliqueTableauEvent(javafx.scene.input.MouseEvent event) {
        Tache selection = tableauTache.getSelectionModel().getSelectedItem();
        btnSupprimer.setDisable(selection == null);
    }
}

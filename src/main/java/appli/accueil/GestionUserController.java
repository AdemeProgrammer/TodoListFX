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
import model.Utilisateur;
import repository.UtilisateurRepository;
import session.SessionUtilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GestionUserController implements Initializable {

    @FXML
    private TableView<Utilisateur> tableauUser;

    @FXML
    private Button Accueil;

    @FXML
    private Button Deconnexio;

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
        Utilisateur selection = tableauUser.getSelectionModel().getSelectedItem();
        if (selection != null) {
            UtilisateurRepository repo = new UtilisateurRepository();
            repo.supprimerUtilisateurParEmail(selection.getEmail());
            tableauUser.getItems().remove(selection);
        }
    }

    @FXML
    void cliqueTableauEvent(javafx.scene.input.MouseEvent event) throws IOException {
        Utilisateur selection = tableauUser.getSelectionModel().getSelectedItem();
        Supprimer.setDisable(selection == null);
        if (event.getClickCount() == 2 && selection != null) {
            StartApplication.changeScene("accueil/update");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String[][] colonnes = {
                { "Id Utilisateur", "idUtilisateur" },
                { "Nom", "nom" },
                { "Prénom", "prenom" },
                { "Email", "email" },
                { "Rôle", "role" }
        };

        for (String[] col : colonnes) {
            TableColumn<Utilisateur, String> tableColumn = new TableColumn<>(col[0]);
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(col[1]));
            tableauUser.getColumns().add(tableColumn);
        }

        UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
        List<Utilisateur> lesUtilisateurs = utilisateurRepository.findAll();

        tableauUser.setItems(FXCollections.observableArrayList(lesUtilisateurs));
    }
}

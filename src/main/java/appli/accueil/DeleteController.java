package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class DeleteController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRetourAccueil;

    @FXML
    private Label error;

    @FXML
    void onDeleteButtonClick(ActionEvent event) throws IOException {
    }
    @FXML
    void onRetourAccueilButtonClick(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/main");
    }}

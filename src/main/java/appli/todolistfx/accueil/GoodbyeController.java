package appli.todolistfx.accueil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GoodbyeController {
    @FXML
    private Label goodbyeText;

    @FXML
    protected void onGoodbyeButtonClick() {
        goodbyeText.setText("Goodbye la team !");
    }
}
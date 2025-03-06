module appli.todolistfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens appli.todolistfx to javafx.fxml;
    exports appli.todolistfx;
    exports appli.todolistfx.accueil;
    opens appli.todolistfx.accueil to javafx.fxml;
}
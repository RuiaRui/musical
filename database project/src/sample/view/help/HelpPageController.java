package sample.view.help;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import sample.model.Controller;

public class HelpPageController extends Controller {
    @FXML
    private Label lblClose;

    public void handleExit(){
        getStage().close();
    }
}

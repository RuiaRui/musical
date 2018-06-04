package sample.view.home;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import sample.model.Controller;


public class HomePageController extends Controller {

    @FXML
    private Label nameContent;
    @FXML
    private Label lblClose;

    public void handleExit(){
        getStage().close();
    }

    public void showName(){
        nameContent.setText(getMain().getCurrentUser());
    }
    public void initializeHomePage(){
        showName();
    }
    public void handleEdit(){
        getMain().showEditPage();
    }

}

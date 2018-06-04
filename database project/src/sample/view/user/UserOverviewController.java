package sample.view.user;

import sample.model.Controller;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.fxml.FXML;

public class UserOverviewController extends Controller {
    @FXML
    private Label lblClose;

    public void handleExit(){
        Platform.runLater(() -> {
            lblClose.setOnMouseClicked((MouseEvent event) -> { Platform.exit();
                System.exit(0);
            });
        });
    }
    public void handleSearch(){
        getMain().showSearchPage();
        getStage().hide();
    }

    public void handleMusicals(){
        getMain().showMusicalsPage();
        getStage().hide();
    }

    public void handleLikes(){
        getMain().showLikesPage();
        getStage().hide();
    }

    public void handleMe(){
        getMain().showHomePage();

    }
    public void handleHelp(){
        getMain().showHelpPage();
    }
    public void handleRank(){
        getMain().showRankPage();
    }



}

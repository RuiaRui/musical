package sample.view.search;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.model.Controller;


import javax.naming.directory.SearchResult;

public class SearchPageController extends Controller {

    @FXML
    private TextField searchContent;
    @FXML
    private RadioButton musicalBtn;

    @FXML
    private RadioButton venueBtn;
    @FXML
    private RadioButton dateBtn;
    @FXML
    private ToggleGroup searchGroup;
    @FXML
    private Label wrongLabel;
    @FXML
    private Label infoLabel;
    @FXML
    private Label lblClose;




    private String searchType="";
    private String search;

    public void showSearchResultView(){
        if(searchType=="")
            infoLabel.setText("Please select search type:");
        else {
            getMain().showSearchResult(searchType, getSearch());
            getStage().hide();
        }
    }

    public void handleExit(){
        Platform.runLater(() -> {
            lblClose.setOnMouseClicked((MouseEvent event) -> { Platform.exit();
                System.exit(0);
            });
        });
    }


    public void setSearchType(){

        musicalBtn.setUserData("musicalname");
        venueBtn.setUserData("venue");
        dateBtn.setUserData("date");

        infoLabel.setText("");

        searchGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> changed, Toggle oldVal, Toggle newVal)
            {
                if(searchGroup.getSelectedToggle()!=null) {
                    infoLabel.setText("Select musicals through " + searchGroup.getSelectedToggle().getUserData().toString());
                    searchType = searchGroup.getSelectedToggle().getUserData().toString();
                }
                else{
                    searchType="";
                }
            }
        });

    }
    public String getSearch(){
        if(searchContent.getText().equals("")){
            wrongLabel.setText("Please fill the search text area!");
        }
        else{
            wrongLabel.setText("");
            search = searchContent.getText();
        }
        return search;
    }

    public void handleBack(){
        getStage().close();
        getMain().showUserOverview();
    }

    public void handleMe(){
        getMain().showHomePage();
    }

    public void handleHelp(){
        getMain().showHelpPage();
    }
}

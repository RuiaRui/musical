package sample.view.likes;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import sample.model.Controller;
import sample.model.JDBC;
import sample.model.Musical;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LikesPageController extends Controller {
    @FXML
    private TableView<Musical> likesTable;
    @FXML
    private TableColumn<Musical, String> musicalColumn;
    @FXML
    private TableColumn<Musical, String>  dateColumn;
    @FXML
    private TableColumn<Musical, String> venueColumn;
    @FXML
    private TableColumn<Musical, String> bookColumn;
    @FXML
    private TableColumn<Musical, String> notesColumn;
    @FXML
    private TableColumn<Musical, String> referenceColumn;
    @FXML
    private Label lblClose;

    private ObservableList<Musical> musicalData = FXCollections.observableArrayList();

    private int selectMusicalId;


    public void initializeTable(){
        musicalColumn.setCellValueFactory(cellData->cellData.getValue().musicalNameProperty());
        dateColumn.setCellValueFactory(cellData->cellData.getValue().dateProperty());
        venueColumn.setCellValueFactory(cellData->cellData.getValue().venueProperty());
        bookColumn.setCellValueFactory(cellData->cellData.getValue().bookProperty());
        notesColumn.setCellValueFactory(cellData->cellData.getValue().notesProperty());
        referenceColumn.setCellValueFactory(cellData->cellData.getValue().referenceProperty());
        setMusicalData();
        likesTable.setItems(getMusicalData());
    }

    public void handleExit(){
        Platform.runLater(() -> {
            lblClose.setOnMouseClicked((MouseEvent event) -> { Platform.exit();
                System.exit(0);
            });
        });
    }

    public ObservableList<Musical> getMusicalData() {
        return musicalData;
    }
    public void setMusicalData() {
        try {
            musicalData.clear();
            JDBC jdbc = new JDBC();
            Connection connection = jdbc.getConnection();
            Statement statement = connection.createStatement();
            String sql ="select * from musicals where musical_id in(select musical_id from user_like where userid=\'"+getMain().getCurrentUser()+"\');";
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                musicalData.add(new Musical(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleBack(){
        getMain().showUserOverview();
        getStage().close();
    }
    public int getSelectMusicalId(){

        int selectIndex = likesTable.getSelectionModel().getSelectedIndex();
        if(selectIndex>=0) {
            selectMusicalId = likesTable.getItems().get(selectIndex).getMusicalId();
        }
        else selectMusicalId=0;

        return selectMusicalId;
    }

    public void handleDetails(){
        getMain().showMusicalPage(getSelectMusicalId());
    }

    public void handleMe(){
        getMain().showHomePage();
    }

    public void handleDelete(){
        try{
                int musical_id=getSelectMusicalId();
                JDBC jdbc = new JDBC();
                Connection connection = jdbc.getConnection();
                Statement statement = connection.createStatement();
                String sql = "delete from user_like where userid=\'" + getMain().getCurrentUser() + "\' and musical_id=\'" + musical_id + '\'';
                statement.executeUpdate(sql);
                likesTable.getItems().remove(likesTable.getSelectionModel().getSelectedItem());

        }catch (Exception se){
            se.printStackTrace();
        }

    }

    public void handleHelp(){
        getMain().showHelpPage();
    }

    public void handleCut(){
        getMain().snapshot(getStage().getScene().getRoot());
    }
}

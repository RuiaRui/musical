package sample.view.musicals;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.model.Controller;
import sample.model.JDBC;
import sample.model.Musical;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class MusicalsPageController extends Controller {

    @FXML
    private TableView<Musical> musicalTable;
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

    @FXML
    private Label label_wrong;

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
        musicalTable.setItems(getMusicalData());
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
            String sql ="SELECT * FROM musicals";
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

    /*public void setSelectMusicalId(int id){
        this.selectMusicalId=id;
        }*/
    public int getSelectMusicalId(){

            int selectIndex = musicalTable.getSelectionModel().getSelectedIndex();
            if(selectIndex>=0) {
                selectMusicalId = musicalTable.getItems().get(selectIndex).getMusicalId();
            }
            else selectMusicalId=0;

        return selectMusicalId;
    }
    public void handleLike(){
        try{
                JDBC jdbc = new JDBC();
                Connection connection = jdbc.getConnection();
                Statement statement=connection.createStatement();
                //to find whether the user has add the musical to his like list
                String isExist="SELECT * FROM user_like WHERE userid="+"\'"+getMain().getCurrentUser()+"\' AND musical_id="+getSelectMusicalId()+";";
                ResultSet rs=statement.executeQuery(isExist);
                if(rs.next()){
                    label_wrong.setText("The musical has already in your like list, please do not add again");
                }
                else{
                    label_wrong.setText("");
                    String addLike="INSERT INTO user_like(userid, musical_id) VALUES(\'"+getMain().getCurrentUser()+"\', \'"+getSelectMusicalId()+"\');";
                    statement.execute(addLike);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleShowDetails(){

        getMain().showMusicalPage(getSelectMusicalId());

    }
    public void handleBack(){
        getMain().showUserOverview();
        getStage().close();
    }

    public void handleMe(){
        getMain().showHomePage();
    }

    public void handleHelp(){
        getMain().showHelpPage();
    }

    public void handleCut(){
        getMain().snapshot(getStage().getScene().getRoot());
    }
}

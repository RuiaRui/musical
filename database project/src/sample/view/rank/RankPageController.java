package sample.view.rank;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.model.Controller;
import sample.model.JDBC;
import sample.model.MusicalRank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RankPageController extends Controller {

    @FXML
    private TableView<MusicalRank> rankTable;
    @FXML
    private TableColumn<MusicalRank, Number> rankColumn;
    @FXML
    private TableColumn<MusicalRank, Number> musicalidColumn;
    @FXML
    private TableColumn<MusicalRank, String> musicalnameColumn;
    @FXML
    private TableColumn<MusicalRank, String> opendateColumn;
    @FXML
    private TableColumn<MusicalRank, String> colsedateColumn;
    @FXML
    private TableColumn<MusicalRank, Number> pronumColumn;
    @FXML
    private TableColumn<MusicalRank, String> comentColumn;
    @FXML
    private TableColumn<MusicalRank, String> locationColumn;


    @FXML
    private Label wrongLabel;

    private ObservableList<MusicalRank> rankData = FXCollections.observableArrayList();
    private int selectMusicalId;

    public void handleExit(){
        getStage().close();
    }
    public void initializeTable(){
        rankColumn.setCellValueFactory(cellData->cellData.getValue().rankProperty());
        musicalidColumn.setCellValueFactory(cellData->cellData.getValue().musicalidProperty());
        musicalnameColumn.setCellValueFactory(cellData->cellData.getValue().musicalnameProperty());
        opendateColumn.setCellValueFactory(cellData->cellData.getValue().opendateProperty());
        colsedateColumn.setCellValueFactory(cellData->cellData.getValue().closedateProperty());
        pronumColumn.setCellValueFactory(cellData->cellData.getValue().proNumProperty());
        comentColumn.setCellValueFactory(cellData->cellData.getValue().commentProperty());
        locationColumn.setCellValueFactory(cellData->cellData.getValue().locationProperty());
        setRankData();
        rankTable.setItems(getRankData());
    }



    public ObservableList<MusicalRank> getRankData() {
        return rankData;
    }

    public void setRankData() {
        try {
            rankData.clear();
            JDBC jdbc = new JDBC();
            Connection connection = jdbc.getConnection();
            Statement statement = connection.createStatement();
            String sql ="SELECT rank,musicalid,musicalname,opendate,closedate,proNum,comment,location FROM longestrun join musicals on longestrun.musicalid=musicals.musical_id";
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                rankData.add(new MusicalRank(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getString(8)));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public int getSelectMusicalId(){

        int selectIndex = rankTable.getSelectionModel().getSelectedIndex();
        if(selectIndex>=0) {
            selectMusicalId = rankTable.getItems().get(selectIndex).getMusicalid();
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
                wrongLabel.setText("The musical has already in your like list, please do not add again");
            }
            else{
                wrongLabel.setText("");
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

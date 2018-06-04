package sample.view.musical;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import sample.view.musicals.MusicalsPageController;
import sample.model.Controller;
import sample.model.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MusicalPageController extends Controller {


    @FXML
    private Label label_id;
    @FXML
    private Label label_name;
    @FXML
    private Label label_musicWriter;
    @FXML
    private Label label_lyricsWriter;
    @FXML
    private Label label_bookWriter;
    @FXML
    private Label label_ref;
    @FXML
    private Label label_venue;
    @FXML
    private Label label_date;
    @FXML
    private Label label_notes;

    @FXML
    private Label label_wrong;
    @FXML
    private Label lblClose;

    public void handleExit(){
        getStage().close();
    }

    public void showMusical(int id){

        try {
            int musicalId = id;
            JDBC jdbc = new JDBC();
            Connection connection = jdbc.getConnection();
            Statement statement = connection.createStatement();
            String musicalDetails = "SELECT * FROM musicals WHERE musical_id = " + musicalId + ";";
            ResultSet resultSet = statement.executeQuery(musicalDetails);

            if(resultSet.next()){
                label_id.setText(resultSet.getString(1));
                label_name.setText(resultSet.getString(2));
                label_date.setText(resultSet.getString(3));
                label_venue.setText(resultSet.getString(4));
                label_bookWriter.setText(resultSet.getString((5)));
                label_notes.setText((resultSet.getString(6)));
                label_ref.setText((resultSet.getString(7)));
            }
            else return;
            musicalDetails="select lwname from lyricswriter where idlyricswriter in(select lwid from musical_lw where musicalid=\'"+musicalId +"\')";
            resultSet=statement.executeQuery(musicalDetails);
            String lw="";
            while(resultSet.next()){
                lw+=resultSet.getString(1);
                lw+=",";
            }
            label_lyricsWriter.setText(lw);

            musicalDetails="select mwname from musicalwriter where idmusicalwriter in(select mwid from musical_mw where musicalid=\'"+musicalId +"\')";
            resultSet=statement.executeQuery(musicalDetails);
            String mw="";
            while (resultSet.next()){
                mw+=resultSet.getString(1);
                mw+=",";
            }
            label_musicWriter.setText(mw);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void handleLike(){

        try{

            JDBC jdbc = new JDBC();
            Connection connection = jdbc.getConnection();
            Statement statement=connection.createStatement();
            int id=Integer.parseInt(label_id.getText());
            //to find whether the user has add the musical to his like list
            String isExist="SELECT * FROM user_like WHERE userid="+"\'"+getMain().getCurrentUser()+"\' AND musical_id="+id+";";
            ResultSet rs=statement.executeQuery(isExist);
            if(rs.next()){
                label_wrong.setText("The musical has been added!");
            }
            else{
                label_wrong.setText("");
                String addLike="INSERT INTO user_like(userid, musical_id) VALUES(\'"+getMain().getCurrentUser()+"\', "+id+");";
                statement.execute(addLike);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

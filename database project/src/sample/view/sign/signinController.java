package sample.view.sign;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.model.Controller;
import sample.model.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class signinController extends Controller {
    @FXML
    private TextField userIDField;
    @FXML
    private PasswordField psswdField;
    @FXML
    private PasswordField cfmPsswdField;
    @FXML
    private Label wrongUsr;
    @FXML
    private Label wrongPsswd;
    @FXML
    private Label wrongCPsswd;
    @FXML
    private Label lblClose;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public void handleExit(){
        Platform.runLater(() -> {
            lblClose.setOnMouseClicked((MouseEvent event) -> { Platform.exit();
                System.exit(0);
            });
        });
    }

    public void handleOK(){
        wrongUsr.setText("");
        wrongPsswd.setText("");
        wrongCPsswd.setText("");
        if(isVadildInput()) {
            getMain().showUserOverview();
            getStage().close();
        }
    }

    public boolean isVadildInput(){
        String userID=userIDField.getText();
        String password=psswdField.getText();
        String confPassword=cfmPsswdField.getText();
        try {
            JDBC jdbc = new JDBC();
            connection = jdbc.getConnection();

            if (userID.length() < 1 || userID.length() >= 10) {
                wrongUsr.setText("Invalid user name.Length is between 1 and 10!");
                return false;
            }
            String sql = "select * from user where userid=\'" + userID + '\'';
            statement = connection.createStatement();
            resultSet=statement.executeQuery(sql);
            if(resultSet.next()){
                wrongUsr.setText("Your name has been used!");
                return false;
            }
            if (password.length()<6||password.length()>=20){
                wrongPsswd.setText("Length is between 6 and 20!");
                return false;
            }
            if(!password.equals(confPassword)){
                wrongCPsswd.setText("Password Wrong!");
                return false;
            }
            sql="insert into user values (\'"+userID+"\',\'"+password+"\')";
            statement.executeUpdate(sql);
            return true;
        }catch (SQLException se){
            se.printStackTrace();
        }
        wrongUsr.setText("Operation Fail!");
        return false;
    }
}

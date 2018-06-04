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

public class loginController extends Controller {

    @FXML
    private TextField userIDField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label wrongUsr;
    @FXML
    private Label wrongPsswd;
    @FXML
    private Label lblClose;

    public void handleSignin() {
        getMain().showSigninPage();
        getStage().close();

    }

    public void handleExit(){
        Platform.runLater(() -> {
            lblClose.setOnMouseClicked((MouseEvent event) -> { Platform.exit();
                System.exit(0);
            });
        });
    }

    public void handleSignUp() {
        wrongUsr.setText("");
        wrongPsswd.setText("");
        if (isInputValid()) {
            getMain().setCurrentUser(userIDField.getText());
            getMain().showUserOverview();
            getStage().close();
        }

    }

    private boolean isInputValid() {
        String password = passwordField.getText();
        String userID = userIDField.getText();

        if (userID != null) {
            try {
                JDBC jdbc = new JDBC();
                Connection connection = jdbc.getConnection();
                Statement statement=connection.createStatement();
                String sql = "select * from user where userid=\'" + userID + '\'';
                ResultSet resultSet = statement.executeQuery(sql);

                if (!resultSet.next()) {
                    wrongUsr.setText("Wrong User ID!");
                    return false;
                } else {
                    if (resultSet.getString(2).equals(password))
                        return true;
                    else {
                        wrongPsswd.setText("Wrong Password!");
                        return false;
                    }

                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        wrongUsr.setText("Operation Fail!");
        return false;
    }
}




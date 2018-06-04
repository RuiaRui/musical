package sample.view.home;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import sample.model.Controller;
import sample.model.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class EditPageController extends Controller {

    @FXML
    private PasswordField OPsswField;
    @FXML
    private PasswordField NPsswField;
    @FXML
    private PasswordField CNPsswField;
    @FXML
    private Label wrongInfo;
    @FXML
    private Label nameContent;
    @FXML
    private Label lblClose;

    public void handleExit(){
        getStage().close();
    }
//
    public void showName(){
        nameContent.setText(getMain().getCurrentUser());
    }
    public void initializeEditPage(){
        showName();
    }

    public boolean isValidInput() {

        String userid=getMain().getCurrentUser();
        String oPssw=OPsswField.getText();
        String nPssw=NPsswField.getText();
        String cNPssw=CNPsswField.getText();

        try{
            JDBC jdbc=new JDBC();
            Connection connection=jdbc.getConnection();
            Statement statement=connection.createStatement();
            String sql="select * from user where userid=\'"+userid+'\'';
            ResultSet resultSet=statement.executeQuery(sql);

            if(resultSet.next()) {

                if (oPssw.equals("") || nPssw.equals("") || cNPssw.equals("")) {
                    wrongInfo.setText("The field can not be empty!");
                    return false;
                }

                if (!resultSet.getString(2).equals(oPssw)) {
                    wrongInfo.setText("The former password is wrong!");
                    return false;
                }
                if(resultSet.getString(1).equals(nPssw)){
                    wrongInfo.setText("New password should be different from the previous one!");
                    return false;
                }
                if (!nPssw.equals(cNPssw)) {
                    wrongInfo.setText("Inconsistent password!");
                    return false;
                }
                if(nPssw.length()<6||nPssw.length()>20){
                    wrongInfo.setText("Length of password must be between 6 and 20!");
                    return false;
                }
                sql = "update user set password=\'" + nPssw + "\' where userid=\'" + userid + '\'';
                statement.executeUpdate(sql);
                return true;
            }

        }catch (SQLException se){
            se.printStackTrace();
        }
        wrongInfo.setText("Operation Fail!");
        return false;
    }

    public void handleOk(){
        wrongInfo.setText("");
        if(isValidInput()){
            getStage().close();
        }
    }

    public void handleCancel(){
        getStage().close();
    }


}

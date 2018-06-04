package sample.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.jws.soap.SOAPBinding;

public class User {
    private StringProperty userID;
    private StringProperty password;

   public User(){
       this("","");
   }
    public User(String userID,String password){
        this.userID=new SimpleStringProperty(userID);
        this.password=new SimpleStringProperty(password);
    }
    public User(User user){
        this.userID=new SimpleStringProperty(user.getUserID());
        this.password=new SimpleStringProperty(user.getPassword());
    }
    public void setUserID(String userID){
        this.userID.set(userID);
    }
    public void setPassword(String password){
        this.password.set(password);
    }

    public StringProperty userIDProperty(){
        return userID;
    }
    public StringProperty passwordProperty(){
        return password;
    }

    public String getUserID(){
        return userID.get();
    }
    public String getPassword(){
        return password.get();
    }
}

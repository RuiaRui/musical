package sample.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Performer {
    private StringProperty performerName;
    private IntegerProperty performerID;

    public Performer(){
        this("",0);
    }

    public Performer(String performerName, int performerID ){
        this.performerName = new SimpleStringProperty(performerName);
        this.performerID = new SimpleIntegerProperty(performerID);
    }

    public Performer(Performer performer){
        this.performerName = new SimpleStringProperty(performer.getPerformerName());
        this.performerID = new SimpleIntegerProperty(performer.getPerformerID());
    }
    //set
    public void setPerformerName(String performerName){
        this.performerName.set(performerName);
    }
    public void setPerformerID(int performerID){
        this.performerID.set(performerID);
    }

    //get info
    public String getPerformerName(){
        return performerName.get();
    }
    public int getPerformerID(){
        return performerID.get();
    }

    //get property

    public StringProperty performerNameProperty() {
        return performerName;
    }

    public IntegerProperty performerIDProperty() {
        return performerID;
    }
}

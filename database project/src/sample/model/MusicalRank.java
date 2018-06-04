package sample.model;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.MouseEvent;

public class MusicalRank {

    private IntegerProperty rank;
    private IntegerProperty musicalid;
    private StringProperty musicalname;
    private StringProperty opendate;
    private StringProperty closedate;
    private IntegerProperty proNum;
    private StringProperty comment;
    private StringProperty location;



    public MusicalRank(){
        this(0,0,"", "", "", 0, "","");
    }

    public void handleExit(){

    }
    public MusicalRank(int rank,int musicalid,String musicalname, String opendate, String closedate, int proNum, String comment, String location){

        this.rank= new SimpleIntegerProperty(rank);
        this.musicalid=new SimpleIntegerProperty(musicalid);
        this.musicalname=new SimpleStringProperty(musicalname);
        this.opendate = new SimpleStringProperty(opendate);
        this.closedate = new SimpleStringProperty(closedate);
        this.proNum= new SimpleIntegerProperty(proNum);
        this.comment = new SimpleStringProperty(comment);
        this.location = new SimpleStringProperty(location);

    }

    public MusicalRank(MusicalRank musical){



        this.musicalid= new SimpleIntegerProperty(musical.getMusicalid());
        this.musicalname=new SimpleStringProperty(musical.getMusicalName());
        this.rank=new SimpleIntegerProperty(musical.getRank());
        this.opendate = new SimpleStringProperty(musical.getOpendate());
        this.closedate = new SimpleStringProperty(musical.getClosedate());
        this.comment = new SimpleStringProperty(musical.getComent());
        this.location = new SimpleStringProperty(musical.getLocation());
        this.proNum = new SimpleIntegerProperty(musical.getProNum());

    }
    //set
    public void setRank(int rank){this.rank.set(rank);}
    public void setMusicalid(int musicalid){this.musicalid.set(musicalid);}
    public void setOpendate(String date){this.opendate.set(date);}
    public void setClosedate(String date){this.closedate.set(date);}
    public void setProNum(int num){this.proNum.set(num);}
    public void setComment(String c){this.comment.set(c);}
    public void setLocation(String location){this.location.set(location);}
    public void setMusicalname(String name){this.musicalname.set(name);}


    //get info
    public int getRank(){return rank.get();}
    public int getMusicalid(){return musicalid.get();}

    public String getMusicalName(){return musicalname.get();}
    public String getOpendate(){return opendate.get();}
    public String getClosedate(){return closedate.get();}
    public int getProNum(){return proNum.get();}
    public String getComent(){return comment.get();}
    public String getLocation(){return location.get();}


    //get property


    public IntegerProperty rankProperty() {
        return rank;
    }

    public IntegerProperty musicalidProperty() {
        return musicalid;
    }

    public StringProperty musicalnameProperty() {
        return musicalname;
    }

    public StringProperty opendateProperty() {
        return opendate;
    }

    public StringProperty closedateProperty() {
        return closedate;
    }

    public StringProperty commentProperty() {
        return comment;
    }

    public StringProperty locationProperty() {
        return location;
    }

    public IntegerProperty proNumProperty() {
        return proNum;
    }
}

package sample.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Musical {
    private IntegerProperty musicalId;
    private StringProperty musicalName;
    private  StringProperty date;
    private StringProperty venue;
    private StringProperty book;
    private StringProperty notes;
    private StringProperty reference;


    public Musical(){
        this(0,"", "", "", "", "", "");
    }

    public Musical(int id,String musicalName, String date, String venue, String book, String notes, String reference){

        this.musicalId= new SimpleIntegerProperty(id);
        this.musicalName=new SimpleStringProperty(musicalName);
        this.date = new SimpleStringProperty(date);
        this.venue = new SimpleStringProperty(venue);
        this.book = new SimpleStringProperty(book);
        this.notes = new SimpleStringProperty(notes);
        this.reference = new SimpleStringProperty(reference);
//        this.lw = new SimpleStringProperty(lw);
//        this.mw=new SimpleStringProperty(mw);
    }

    public Musical(Musical musical){
        this.musicalId= new SimpleIntegerProperty(musical.getMusicalId());
        this.musicalName=new SimpleStringProperty(musical.getMusicalName());
        this.date = new SimpleStringProperty(musical.getDate());
        this.venue = new SimpleStringProperty(musical.getVenue());
        this.book = new SimpleStringProperty(musical.getBook());
        this.notes = new SimpleStringProperty(musical.getNotes());
        this.reference = new SimpleStringProperty(musical.getReference());
//        this.lw = new SimpleStringProperty(musical.getLw());
//        this.mw=new SimpleStringProperty(musical.getMw());
    }



    //get info
    public int getMusicalId(){return musicalId.get();}
    public String getMusicalName(){return musicalName.get();}
    public String getDate(){return date.get();}
    public String getVenue(){return venue.get();}
    public String getBook(){return book.get();}
    public String getNotes(){return notes.get();}
    public String getReference(){return reference.get();}
    //public String getLw(){return lw.get();}
    //public String getMw(){return mw.get();}


    //get property

    public IntegerProperty musicalIdProperty() {
        return musicalId;
    }

    public StringProperty musicalNameProperty() {
        return musicalName;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty venueProperty() {
        return venue;
    }

    public StringProperty bookProperty() {
        return book;
    }

    public StringProperty notesProperty() {
        return notes;
    }

    public StringProperty referenceProperty() {
        return reference;
    }

//    public StringProperty lwProperty() {
//        return lw;
//    }
//
//    public StringProperty mwProperty(){return mw;}
}

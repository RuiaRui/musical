package sample.model;

import javafx.stage.Stage;
import sample.Main;

public class Controller {
    private Stage stage;
    private Main main;

    public Controller(){}
    public Controller(Controller controller){
        this.stage=controller.stage;
        this.main=controller.main;
    }

    public void setMain(Main main){
        this.main=main;
    }
    public void setStage(Stage stage){
        this.stage=stage;
    }
    public Main getMain(){
        return main;
    }

    public Stage getStage(){
        return stage;
    }

}

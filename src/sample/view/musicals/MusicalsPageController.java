package sample.view.musicals;

import sample.model.Controller;

public class MusicalsPageController extends Controller {
    public void handleBack(){
        getMain().showUserOverview();
    }

    public void handleMe(){
        getMain().showHomePage();
    }
}

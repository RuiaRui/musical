package sample.view.likes;

import sample.model.Controller;

public class LikesPageController extends Controller {
    public void handleBack(){
        getMain().showUserOverview();
        getStage().close();
    }

    public void handleMe(){
        getMain().showHomePage();
    }
}

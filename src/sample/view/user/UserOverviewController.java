package sample.view.user;

import sample.model.Controller;

public class UserOverviewController extends Controller {
    public void handleSearch(){
        getMain().showSearchPage();
        getStage().hide();
    }

    public void handleMusicals(){
        getMain().showMusicalsPage();
        getStage().hide();
    }

    public void handleLikes(){
        getMain().showLikesPage();
        getStage().hide();
    }

    public void handleMe(){
        getMain().showHomePage();
    }


}

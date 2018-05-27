package sample.view.search;

import sample.model.Controller;

public class SearchResultController extends Controller {
    public void handleBack(){
        getStage().close();
        getMain().showSearchPage();
    }

    public void handleMe(){
        getMain().showHomePage();
    }
}

package sample.view.search;

import sample.model.Controller;

import javax.naming.directory.SearchResult;

public class SearchPageController extends Controller {
    public void showSearchResultView(){
        getMain().showSearchResult();
        getStage().hide();
    }

    public void handleBack(){
        getStage().close();
        getMain().showUserOverview();
    }

    public void handleMe(){
        getMain().showHomePage();
    }

}

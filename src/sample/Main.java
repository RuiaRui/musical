package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.*;
import sample.view.home.EditPageController;
import sample.view.home.HomePageController;
import sample.view.likes.LikesPageController;
import sample.view.musical.MusicalPageController;
import sample.view.musicals.MusicalsPageController;
import sample.view.search.SearchPageController;
import sample.view.search.SearchResultController;
import sample.view.user.UserOverviewController;
import sample.view.sign.signinController;
import sample.view.sign.loginController;

import javax.naming.directory.SearchResult;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
       showLoginPage();
    }

    public Controller setOverviewControllor(String url, String title,boolean resize){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource(url));
            Parent overview=loader.load();
            Scene scene=new Scene(overview);
            Stage stage=new Stage();
            stage.setScene((scene));
            stage.setTitle(title);
            stage.setResizable(resize);

            Controller controller=loader.getController();
            controller.setMain(this);
            controller.setStage(stage);
            return controller;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void showSearchPage(){
        SearchPageController controller=(SearchPageController)setOverviewControllor("view/search/SearchPage.fxml","Search",false);
        controller.getStage().show();
    }

    public void showSearchResult(){
        SearchResultController controller=(SearchResultController)setOverviewControllor("view/search/SearchResult.fxml","SearchResult",true);
        controller.getStage().show();
    }

    public void showUserOverview(){
        UserOverviewController controller=(UserOverviewController)setOverviewControllor("view/user/UserOverview.fxml","User Overview",false);
        controller.getStage().show();
    }

    public void showLikesPage(){
        LikesPageController controller=(LikesPageController)setOverviewControllor("view/likes/LikesPage.fxml","My Likes",false);
        controller.getStage().show();
    }

    public void showMusicalsPage(){
        MusicalsPageController controller=(MusicalsPageController)setOverviewControllor("view/musicals/MusicalsPage.fxml","Musicals List",false);
        controller.getStage().show();
    }

    public void showHomePage(){
        HomePageController controller=(HomePageController)setOverviewControllor("view/home/HomePage.fxml","Home Page",false);
        controller.getStage().show();
    }

    public void showMusicalPage(){
        MusicalPageController controller=(MusicalPageController)setOverviewControllor("view/musical/MusicalPage.fxml","Musical Info",false);
        controller.getStage().show();
    }

    public void showEditPage(){
        EditPageController controller=(EditPageController)setOverviewControllor("view/home/EditPageController","Edit Page",false);
        controller.getStage().show();
    }

    public void showLoginPage(){
        loginController controller=(loginController)setOverviewControllor("view/sign/login.fxml","Log In",false);
        controller.getStage().show();
    }

    public void showSigninPage(){
        signinController controller=(signinController)setOverviewControllor("view/sign/signin.fxml","Sign In",false);
        controller.getStage().show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

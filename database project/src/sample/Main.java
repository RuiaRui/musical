package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.model.*;
import sample.view.help.HelpPageController;
import sample.view.home.EditPageController;
import sample.view.home.HomePageController;
import sample.view.likes.LikesPageController;
import sample.view.musical.MusicalPageController;
import sample.view.musicals.MusicalsPageController;
import sample.view.rank.RankPageController;
import sample.view.search.SearchPageController;
import sample.view.search.SearchResultController;
import sample.view.user.UserOverviewController;
import sample.view.sign.signinController;
import sample.view.sign.loginController;
import javax.imageio.ImageIO;
import javax.naming.directory.SearchResult;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.io.File;


public class Main extends Application {
    private String currentUser;

    @Override
    public void start(Stage primaryStage){
       Application.setUserAgentStylesheet(getClass().getResource("css/login.css").toExternalForm());
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

    public void setCurrentUser(String user){
        currentUser = user;
    }
    public String getCurrentUser(){
        return currentUser;
    }

    public void showSearchPage(){
        Application.setUserAgentStylesheet(getClass().getResource("css/searchPage.css").toExternalForm());
        SearchPageController controller=(SearchPageController)setOverviewControllor("view/search/SearchPage.fxml","Search",false);
        controller.setSearchType();
        controller.getStage().initStyle(StageStyle.UNDECORATED);
        controller.getStage().show();
    }

    public void showSearchResult(String type, String search){
        Application.setUserAgentStylesheet(getClass().getResource("css/searchResult.css").toExternalForm());
        SearchResultController controller=(SearchResultController)setOverviewControllor("view/search/SearchResult.fxml","SearchResult",true);
        controller.initializeTable(type, search);
        controller.getStage().initStyle(StageStyle.UNDECORATED);
        controller.getStage().show();
    }


    public void showUserOverview(){
        Application.setUserAgentStylesheet(getClass().getResource("css/userOverview.css").toExternalForm());
        UserOverviewController controller=(UserOverviewController)setOverviewControllor("view/user/UserOverview.fxml","User Overview",false);
        controller.getStage().initStyle(StageStyle.UNDECORATED);
        controller.getStage().show();
    }

    public void showLikesPage(){
        Application.setUserAgentStylesheet(getClass().getResource("css/likesPage.css").toExternalForm());
        LikesPageController controller=(LikesPageController)setOverviewControllor("view/likes/LikesPage.fxml","My Likes",false);
        controller.initializeTable();
        controller.getStage().initStyle(StageStyle.UNDECORATED);
        controller.getStage().show();
    }

    public void showMusicalsPage(){
        Application.setUserAgentStylesheet(getClass().getResource("css/musicalsPage.css").toExternalForm());
        MusicalsPageController controller=(MusicalsPageController)setOverviewControllor("view/musicals/MusicalsPage.fxml","Musicals List",false);
        controller.initializeTable();
        controller.getStage().initStyle(StageStyle.UNDECORATED);
        controller.getStage().show();
    }

    public void showHomePage(){
        Application.setUserAgentStylesheet(getClass().getResource("css/homePage.css").toExternalForm());
        HomePageController controller=(HomePageController)setOverviewControllor("view/home/HomePage.fxml","Home Page",false);
        controller.initializeHomePage();
        controller.getStage().initStyle(StageStyle.UNDECORATED);
        controller.getStage().show();
    }

    public void showMusicalPage(int musicalId){
        Application.setUserAgentStylesheet(getClass().getResource("css/musicalPage.css").toExternalForm());
        MusicalPageController controller=(MusicalPageController)setOverviewControllor("view/musical/MusicalPage.fxml","Musical Info",false);
        controller.showMusical(musicalId);
        controller.getStage().initStyle(StageStyle.UNDECORATED);
        controller.getStage().show();
    }

    public void showEditPage(){
        Application.setUserAgentStylesheet(getClass().getResource("css/editPage.css").toExternalForm());
        EditPageController controller=(EditPageController)setOverviewControllor("view/home/EditPage.fxml","Edit Page",false);
        controller.initializeEditPage();
        controller.getStage().initStyle(StageStyle.UNDECORATED);
        controller.getStage().show();
    }

    public void showLoginPage(){
        Application.setUserAgentStylesheet(getClass().getResource("css/login.css").toExternalForm());
        loginController controller=(loginController)setOverviewControllor("view/sign/login.fxml","Log In",false);
        controller.getStage().initStyle(StageStyle.UNDECORATED);
        controller.getStage().show();
    }

    public void showSigninPage(){
        Application.setUserAgentStylesheet(getClass().getResource("css/signin.css").toExternalForm());
        signinController controller=(signinController)setOverviewControllor("view/sign/signin.fxml","Sign In",false);
        controller.getStage().initStyle(StageStyle.UNDECORATED);
        controller.getStage().show();
    }

    public  void showHelpPage(){
        Application.setUserAgentStylesheet(getClass().getResource("css/helpPage.css").toExternalForm());
        HelpPageController controller=(HelpPageController)setOverviewControllor("view/help/HelpPage.fxml","Help:",false);
        controller.getStage().initStyle(StageStyle.UNDECORATED);
        controller.getStage().show();
    }


    public  void showRankPage(){
        Application.setUserAgentStylesheet(getClass().getResource("css/rankPage.css").toExternalForm());
        RankPageController controller=(RankPageController)setOverviewControllor("view/rank/rankPage.fxml","Rank list:",false);
        controller.initializeTable();
        controller.getStage().initStyle(StageStyle.UNDECORATED);
        controller.getStage().show();
    }

    public void snapshot(Node view) {
        Image image = view.snapshot(null, null);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png",
                    new File("f:\\" + System.currentTimeMillis() + ".png"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Generation successful!");
            alert.setContentText("The path is f:\\"+System.currentTimeMillis() + ".png");
            alert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Generation failed!");
            alert.setContentText("There must be something wrong with your computer!");
            alert.showAndWait();

        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}

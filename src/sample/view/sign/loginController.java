package sample.view.sign;

import sample.model.Controller;

public class loginController extends Controller {
    public void handleSignin(){
        getMain().showSigninPage();
        getStage().close();

    }

    public void handleSignUp(){
        getMain().showUserOverview();
        getStage().close();
    }
}

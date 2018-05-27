package sample.view.sign;

import sample.model.Controller;

public class signinController extends Controller {
    public void handleOK(){
        getMain().showUserOverview();
        getStage().close();
    }
}

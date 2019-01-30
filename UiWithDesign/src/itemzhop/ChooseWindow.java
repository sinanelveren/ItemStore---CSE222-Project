package itemzhop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseWindow  implements Initializable {

    @FXML
    Button btn_SignIn;
    @FXML
    Button btn_CreateAccount;
    @FXML
    Button btn_About;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void signInButtonAction(ActionEvent actionEvent) throws IOException {

            Parent home_page_parent= FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene home_page_scene=new Scene(home_page_parent);
            Stage app_stage=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(home_page_scene);
            app_stage.show();


    }


    public void createAccountButtonAction(ActionEvent actionEvent) throws IOException {

        Parent home_page_parent= FXMLLoader.load(getClass().getResource("createAccount.fxml"));
        Scene home_page_scene=new Scene(home_page_parent);
        Stage app_stage=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();


    }

}

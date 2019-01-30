package itemzhop;


import javax.swing.*;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Optional;

public class CreateItem {

    @FXML
    public TextField item_name_new ;


    @FXML public void btn_upload_click()
    {

    }
    @FXML
    public void btn_create_click(){
        if(item_name_new.getText() != null)
            System.out.println(item_name_new.getText());

       /* Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        alert.show();*/

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("İteminiz Oluşturuldu");
        alert.show();
    }

    @FXML
    public void initialize() throws IOException {

    }
    public void btn_back_clicked(javafx.event.ActionEvent actionEvent)  {
        //Load create item page

        try {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("mainFrame.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
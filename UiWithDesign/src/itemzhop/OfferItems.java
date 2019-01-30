package itemzhop;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import javafx.scene.control.*;
public class OfferItems {

    @FXML
    private Label item_info  ;
    @FXML
    private TextField offer_price ;


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

    @FXML
    public void initialize() throws IOException {
        BufferedReader getUserData = new BufferedReader(new FileReader("temp.csv"));
        String line = "";
        if ((line = getUserData.readLine()) != null) {
            String[] tmpItemStr = line.split(",");
            for(String str : tmpItemStr)
                item_info.setText(item_info.getText() + str + "\n");
        }
    }

    public void btn_offer_clicked(javafx.event.ActionEvent actionEvent) {
        try {
            if(null != offer_price.getText() &&  !offer_price.getText().isEmpty()){

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Onay Ekranı");
                alert.setHeaderText("Ürüne Teklif vermeyi Onaylıyormusunuz");
                alert.setContentText("Onaylansınmı ?    ");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    FileOpearation.AppendStringFile(offer_price.getText(), "TEMP",false);


                }
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("mainFrame.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
            }
        }catch (Exception ex){
            System.out.println(ex.toString());
        }



    }


}

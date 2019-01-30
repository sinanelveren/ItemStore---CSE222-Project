package itemzhop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ItemIncomingOfferController {
    @FXML private Label itemPrice;
    @FXML private Label itemValue;
    @FXML private Label itemDesc;
    @FXML private Label itemName;

    ItemIncomingOffer itemOffer;

    private AnchorPane root;

    public void setOffer(ItemIncomingOffer offer){
        itemOffer= offer;

        itemName.setText(offer.item.name);
        //itemValue.setText(""+offer.item.value);
        //itemDesc.setText(offer.item.desc);
        itemPrice.setText(""+offer.price);
    }

    public AnchorPane getRoot() {
        return root;
    }

    @FXML
    private void actionBuy(ActionEvent event) {
        System.out.println("Buy: "+itemOffer.item.name);
    }

    public static ItemIncomingOfferController LoadItem() throws IOException {
        URL itemFxmlUrl = ItemIncomingOfferController.class.getResource("item.fxml");

        FXMLLoader loader = new FXMLLoader(itemFxmlUrl);

        AnchorPane pane= loader.load();
        ItemIncomingOfferController cont = (ItemIncomingOfferController)loader.getController();
        cont.root= pane;

        return cont;
    }
}

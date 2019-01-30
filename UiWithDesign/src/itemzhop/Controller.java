package itemzhop;

import itemzhop.Algorithms.BinarySearchTree;
import itemzhop.Algorithms.RedBlackTree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

public class Controller {
    @FXML
    private Button btn_buy = new Button();

    @FXML
    private VBox leftBox;
    @FXML
    private ComboBox<String> sortSelect = new ComboBox<String>();
    @FXML
    private TableView<Sale> storeTable = new TableView<>();
    private ArrayList<Sale> shopItemsL = new ArrayList<>();
    @FXML
    private TextField txt_search;
    Boolean flagOffer = false;

    RedBlackTree<Sale> shopItems = new RedBlackTree();
    BinarySearchTree tree = new BinarySearchTree();

    private void addItemsInTable(ObservableList<Sale> data) {

        TableColumn itemName = new TableColumn("Ürün Adi");
        itemName.setMinWidth(100);
        itemName.setCellValueFactory(new PropertyValueFactory<Sale, String>("itemName"));

        TableColumn seller = new TableColumn("Satıcı");
        seller.setMinWidth(100);
        seller.setCellValueFactory(new PropertyValueFactory<Sale, String>("seller"));

        TableColumn price = new TableColumn("Teklif");
        price.setMinWidth(20);
        price.setCellValueFactory(new PropertyValueFactory<Sale, String>("price"));


        TableColumn maxPrice = new TableColumn("Son Fiyat");
        maxPrice.setMinWidth(20);
        maxPrice.setCellValueFactory(new PropertyValueFactory<Sale, String>("maxPrice"));


        TableColumn time = new TableColumn("Kalan Süre");
        time.setMinWidth(50);
        time.setCellValueFactory(new PropertyValueFactory<Sale, String>("remainingTime"));


        storeTable.setEditable(true);
        storeTable.getColumns().addAll(itemName, seller, price, maxPrice, time);
        storeTable.getItems().addAll(data);
    }

    @FXML
    public void initialize() throws IOException {

        final ObservableList<Sale> data =
                FXCollections.observableArrayList();

        Random rand = new Random();
        BufferedReader getUserData = new BufferedReader(new FileReader("sales.csv"));
        String[] tmpItemStr;
        String line;
        Sale sale;

        while ((line = getUserData.readLine()) != null) {
            tmpItemStr = line.split(",");

            sale = createSale(tmpItemStr);
            shopItems.add(sale);
            shopItemsL.add(sale);

            data.add(sale);
            tree.add(sale);
        }
        addItemsInTable(data);
    }

    /**
     * pars csv line and create Sale objecjt with this paramters
     *
     * @param tmpItemStr a line of csve file
     * @return Sale object that a line for a item's sale
     */
    private Sale createSale(String[] tmpItemStr) {
        long timeData;
        int time;       //time left as integer in seconds
        int seconds = 0;
        int minutes = 0;
        int hours = 0;
        int days = 0;

        NumberFormat formatter = new DecimalFormat("00");       //for time column


        timeData = Long.parseLong(tmpItemStr[5]);      //time convertion

        long currentTime = (new Date()).getTime() / 1000;


        timeData = timeData - currentTime;
        time = (int) timeData;
        if (timeData > 0) {

            seconds = (int) ((timeData) % 60);
            minutes = (int) ((timeData / 60) % 60);
            hours = (int) ((timeData / (60 * 60)) % 24);
            days = (int) ((timeData / (60 * 60 * 24)) % 7);
        }

        return new Sale(Integer.parseInt(tmpItemStr[0]),       //id
                tmpItemStr[1],                          //item name
                tmpItemStr[2],                          //seller
                Integer.parseInt(tmpItemStr[3]),        //price
                Integer.parseInt(tmpItemStr[4]),        //max price
                time,
                (days + " gün " + formatter.format(hours) + ":" +
                        formatter.format(minutes) + ":" + formatter.format(seconds)),         //time in seconds
                tmpItemStr[6]);                          //last bidder name
    }

    public void loadItems(ActionEvent actionEvent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("profilePage.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    @FXML
    private void btn_search_click() {

        storeTable.getItems().clear();
        ObservableList<Sale> data =
                FXCollections.observableArrayList();

        if (txt_search.getText() == null || txt_search.getText().isEmpty()) {

            data = fillData(shopItemsL);

        } else {
            ArrayList<Sale> result = new ArrayList<>();
            result = shopItems.FindAll(new Sale(txt_search.getText()), shopItemsL);
            data = fillData(result);
        }
        storeTable.setItems(data);
        storeTable.refresh();
    }

    private ObservableList<Sale> fillData(ArrayList<Sale> dataField) {
        ObservableList<Sale> data =
                FXCollections.observableArrayList();
        for (Sale sale : dataField) {
            data.add(sale);
        }
        return data;
    }

    public void itemBuy(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Onay Ekranı");
        alert.setHeaderText("Ürünü Almayı Onaylıyormusunuz");
        alert.setContentText("Onaylansınmı ?    ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Sale selected = storeTable.getSelectionModel().getSelectedItem();
            System.out.println(selected.toString());
            String str = FileOpearation.getSessionId() + "," + selected.getId();
            FileOpearation.AppendStringFile(str, "USERITEMS",true);
            
        }
    }
    public void btn_create_click(ActionEvent actionEvent)  {
        //Load create item page

        try {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("create_Item.fxml"));
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
    public void btn_offer_item_clicked(ActionEvent actionEvent) {

        flagOffer = true;


        Sale selected = storeTable.getSelectionModel().getSelectedItem();
        for(int i = 0; i < shopItemsL.size(); ++ i) {
            if (shopItemsL.get(i).getId() == selected.getId()
                    && shopItemsL.get(i).getItemName().equals(selected.getItemName())
                    && shopItemsL.get(i).getSeller().equals(selected.getSeller())) {
                shopItemsL.remove(i);
            }

        }
        System.out.println(selected.toString());
        String str = selected.toString();
        FileOpearation.AppendStringFile(str, "TEMP",false);


        Parent home_page_parent = null;
        try {

            home_page_parent = FXMLLoader.load(getClass().getResource("offer_item.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(home_page_scene);
            app_stage.show();

            FileOpearation.AppendStringFile("", "TEMP",false);

        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    @FXML
    public void btn_create_item_Click(ActionEvent actionEvent) throws IOException {
        //Load create item page

        Parent home_page_parent= FXMLLoader.load(getClass().getResource("Create_Item.fxml"));
        Scene home_page_scene=new Scene(home_page_parent);
        Stage app_stage=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }

}
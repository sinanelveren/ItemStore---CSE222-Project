package itemzhop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class profilePage {

    @FXML
    Button btn_arkadaslar=new Button();
    @FXML
    private TableView<Sale> tbl_profile = new TableView<>() ;
    @FXML private ListView<Item> listView;

    @FXML private TextField txt_search;
    ArrayList<Item> myItem=new ArrayList<>( );


    ObservableList data = FXCollections.observableList(myItem);



    //storeTable içerisine items.csv içerisinde yer alan ürünlerin tamamını çekeceğiz ve her satırı bir eleman olarak ekleyeceğiz


    @FXML
    public void loadFriends() throws IOException {
        tbl_profile.getColumns().clear();
        tbl_profile.getItems().clear();

        final ObservableList<Sale> data =
                FXCollections.observableArrayList();

        BufferedReader getUserData = new BufferedReader(new FileReader("accounts.csv"));
        String[] tmpItemStr;
        String line;
        Item tempItem;

        while ((line = getUserData.readLine()) != null) {
            tmpItemStr = line.split(",");
            tempItem=new Item();
            tempItem.name=tmpItemStr[0];
            tempItem.description=tmpItemStr[2];
            tempItem.defaultPrice=0;
            myItem.add(tempItem);

            //data.add(new Sale( new Sale(tmpItemStr[1],"",500,100,"") );
        }

        TableColumn itemName = new TableColumn("Kullanıcı Adı");
        itemName.setMinWidth(100);
        itemName.setCellValueFactory(
                new PropertyValueFactory<Sale, String>("name"));

        TableColumn seller = new TableColumn("Kullanıcı Türü");
        seller.setMinWidth(100);
        seller.setCellValueFactory(
                new PropertyValueFactory<Sale, String>("seller"));



        tbl_profile.setEditable(true);
        tbl_profile.getColumns().addAll(itemName, seller);
        tbl_profile.getItems().addAll(data);


    }

    public void loadFriendItems() throws IOException {
        tbl_profile.getColumns().clear();
        tbl_profile.getItems().clear();
        final ObservableList<Sale> data2 =
                FXCollections.observableArrayList();
        BufferedReader getSessionData = new BufferedReader(new FileReader("session.csv"));
        String[] tmpUser;
        String line;
        BufferedReader getFoolowersData = new BufferedReader(new FileReader("followers.csv"));
        BufferedReader getSalesData = new BufferedReader(new FileReader("sales.csv"));

        if ((line = getSessionData.readLine()) != null) {
            String sessionId=line;
            while((line=getFoolowersData.readLine())!=null) {
                tmpUser = line.split(",");
                if (sessionId.equals(tmpUser[0])) {
                    System.out.println("bulduk");

                    String[] tmpSale;
                    for (int i = 1; i <tmpUser.length ; i++) {
                        System.out.println("tmp user ın size :"+tmpUser.length);
                        int b=Integer.parseInt(tmpUser[i]);
                        //System.out.println(tmpUser[i]);//takip ettiklerimizin listesi. sırayla hepsinin satıştak elemanlarını yazdıralım
                        String line2="";
                        getSalesData= new BufferedReader(new FileReader("sales.csv"));
                        while((line2=getSalesData.readLine())!=null) {
                            tmpSale = line2.split(",");
                                int a=Integer.parseInt(tmpSale[0]);


                            System.out.println(a+" "+b);
                                if (a==b){
                                    System.out.println("yakaladım");
                                  // data2.add(new Sale(tmpSale[1],tmpSale[2],Integer.parseInt(tmpSale[3]),Integer.parseInt(tmpSale[4]),tmpSale[5],tmpSale[6]));
                                   data2.add(new Sale(Integer.parseInt(tmpSale[0]),
                                           tmpSale[1],
                                           tmpSale[2],
                                           Integer.parseInt(tmpSale[3]),
                                           Integer.parseInt(tmpSale[4]),
                                           Integer.parseInt(tmpSale[5]),
                                           tmpSale[6],
                                           tmpSale[6]));

                                }
                        }
                    }
                }

            }

            TableColumn itemName = new TableColumn("Eşya Adı");
            itemName.setMinWidth(100);
            itemName.setCellValueFactory(
                    new PropertyValueFactory<Sale, String>("itemName"));

            TableColumn seller = new TableColumn("Satıcı");
            seller.setMinWidth(100);
            seller.setCellValueFactory(
                    new PropertyValueFactory<Sale, String>("seller"));
            TableColumn q = new TableColumn("Ücret");
            q.setMinWidth(100);
            q.setCellValueFactory(
                    new PropertyValueFactory<Sale, Integer>("price"));

            TableColumn w = new TableColumn("Maksimum Ücret");
            w.setMinWidth(100);
            w.setCellValueFactory(
                    new PropertyValueFactory<Sale, Integer>("maxPrice"));
            TableColumn t = new TableColumn("Zaman");
            t.setMinWidth(100);
            t.setCellValueFactory(
                    new PropertyValueFactory<Sale, String>("time"));

            TableColumn lastBidder = new TableColumn("Teklif Veren");
            lastBidder.setMinWidth(100);
            lastBidder.setCellValueFactory(
                    new PropertyValueFactory<Sale, String>("lastBidder"));

            tbl_profile.setEditable(true);
            tbl_profile.getColumns().addAll(itemName,seller,q,w,t,lastBidder);
            tbl_profile.getItems().addAll(data2);

        }
    }
}

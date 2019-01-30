package itemzhop;

import java.util.ArrayList;

public class Player extends User {
    private ArrayList<Item> backPack;
    private int gold ;

    public Player(boolean isAdmin, String nickName, String password,int gold) {
        super(isAdmin, nickName, password);
        this.gold = gold;
    }

    public void createBidding(AuctionHouse auctionHouse, Item item, int buyOutPrice, int bid) {
        //Remove on backpack Item
        //market her zaman satın alır.

        auctionHouse.createBid(item,buyOutPrice,bid,this);

    }
    public void buy(AuctionHouse auctionHouse, int index, int buyOutPrice, int bid){

    }
}

package itemzhop;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AuctionHouse {
    private Map<Integer, ItemBidding> itemList;
    private Set<Player> players = new HashSet<Player>();

    public void createBid(Item item,int buyOut,int bid,Player seller) {
        ItemBidding newItem = new ItemBidding(item,bid,buyOut,24,seller);
        itemList.put(newItem.id,newItem);
    }

    private void checkForTimeOutBiddings(){

    }
    private void buyOut(Player buyer, int price, ItemBidding item){
        //oyuncuya gold ekle
    }
}

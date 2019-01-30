package itemzhop;

public class ItemBidding {
    static int idGenerator=1000;
    int id;
    int bidPrice;
    int buyOutPrice;
    int timeLeft;
    Item item ;
    Player seller;
    Player HighestBidder;
    int HighestBid;

    public ItemBidding(Item item, int bidPrice, int buyOutPrice, int timeLeft, Player player) {
        this.id = idGenerator++;
        this.bidPrice = bidPrice;
        this.buyOutPrice = buyOutPrice;
        this.timeLeft = timeLeft;
        this.item = item;
        this.seller = player;
    }
    public void bidOn(Player bidder,int bidPrice){
        repayHighestBidder();
        // set highest bidder and bid
    }
    public void buyOut(Player buyer ){
        repayHighestBidder();

        finalizeBidding( buyer ,buyOutPrice);
    }
    private void finalizeBidding(Player buyer,int finalPrice){
        //TODO pay seller
        //TODO put item on buyers inventory
    }
    private void repayHighestBidder(){
        //TODO check if there is a previous bidder if there is repay them

    }
}

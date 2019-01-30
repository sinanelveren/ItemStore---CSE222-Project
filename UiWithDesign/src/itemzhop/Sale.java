package itemzhop;

/**
 * for each item's sale
 * each object shows a new item
 */
public  class Sale implements Comparable<Sale> {

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public static void setIdStart(int idStart) {
        Sale.idStart = idStart;
    }

    private int price;
    private int maxPrice;

    @Override
    public String toString() {
        return "Ürün" +
                ", Adı='" + itemName + '\'' +
                ", Fiyatı=" + price +
                ", Maksimum Fiyat=" + maxPrice +
                ", Süresi=" + remainingTime +
                ", Satıcı='" + seller + '\'' +
                ", Son Teklif Veren='" + lastBidder + '\'' ;
    }

    private int time;
    private String remainingTime;
    private String itemName;
    private String seller;
    private String lastBidder;
    private static int idStart = 1000;      //star from 1000 to INF


    public Sale(String itemName,
                String seller,
                int maxPrice){

        this.id = generateID();

        this.itemName = itemName;
        this.seller = seller;
        this.price = 0;
        this.maxPrice = maxPrice;
        this.remainingTime = Integer.toString(180000);        //2 days in seconds
        this.time = 180000;
        this.lastBidder = "nll";            //nll : empty
    }




    public Sale(int id,
                String itemName,
                String seller ,
                int price,
                int maxPrice,
                int time,
                String remainingTime,
                String lastBidder) {
        this.id = id;
        this.itemName = itemName;
        this.seller = seller;
        this.price = price;
        this.maxPrice = maxPrice;
        this.time = time;
        this.remainingTime = remainingTime;
        this.lastBidder = lastBidder;
    }

    public Sale(String name) {
        this.itemName  = name;
    }


    //getters
    public int getId() {

        return id;
    }


    public int getTime() {

        return time;
    }


    public int getPrice() {

        return price;
    }


    public int getMaxPrice() {

        return maxPrice;
    }


    public String getRemainingTime() {

        return remainingTime;
    }


    public String getItemName() {

        return itemName;
    }


    public String getSeller() {

        return seller;
    }


    public String getLastBidder() {

        return lastBidder;
    }


    private int generateID() {
        return ++idStart;
    }

    //setter


    public void setPrice(int price) {

        this.price = price;
    }



    public void setRemainingTime(String remainingTime) {

        this.remainingTime = remainingTime;
    }



    public void setLastBidder(String lastBidder) {

        this.lastBidder = lastBidder;
    }



    @Override
    public int compareTo(Sale o) {
        return this.getItemName().compareTo( o.getItemName() );
    }
    @Override
    public boolean equals(Object o){
        Sale sale = (Sale) o;
        System.out.println(this.getItemName());
        if(this.getItemName().equals(sale.getItemName())){
            System.out.println(this.getItemName());
            return true;
        }

        return false;
    }


}

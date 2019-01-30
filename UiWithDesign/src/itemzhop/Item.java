package itemzhop;

public  class Item {

    public String getName() {
        return name;
    }

    public String name;
    public int defaultPrice;


    public Item(){}
    public Item(String name, String description,int defaultPrice) {
        this.name = name;
        this.description = description;
        this.defaultPrice = defaultPrice;
    }

    public String description;

    @Override
    public String toString() {
        return name.toString()+"  -  "+description.toString()+"  -  "+defaultPrice;
    }
}

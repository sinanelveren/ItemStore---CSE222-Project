package itemzhop;

public class ConsumableItem extends Item  {

    public ConsumableItem(String name, String description) {
        super(name, description,0);
    }
    @Override
    public String getName(){
        return super.getName() + "ConsumableItems";
    }
}

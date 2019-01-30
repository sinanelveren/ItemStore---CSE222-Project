package itemzhop;

public class Weapon extends Item  {

    public Weapon(String name, String description) {
        super(name, description,0);
    }
    @Override
    public String getName(){
        return super.getName() + "Weapon";
    }
}


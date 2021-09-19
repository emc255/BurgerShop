package emc.components;

public enum Meat implements MenuItem{
    ANGUS("ANGUS","Angus Meat", 2.50),
    PORK("PORK","Pork Meat", 2.00),
    SAUSAGE("SAUSAGE","Hotdog", 1.50),
    VEGGIE("VEGGIE","Veggie Patty",2.25);



    private final String name;
    private final String description;
    private final double price;

    Meat(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public String getDisplayDescription() {
        return description;
    }

    @Override
    public double getDisplayPrice() {
        return price;
    }


}

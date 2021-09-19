package emc.components;

public enum Bread implements MenuItem {
    ITALIAN( "ITALIAN","Italian Bread", 1.25),
    WHEAT("WHEAT","Wheat Bread", 1.50),
    WHITE("WHITE","White Bread", 2);



    private final String name;
    private final String description;
    private final double price;

    Bread(String name, String description, double price) {
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

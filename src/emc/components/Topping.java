package emc.components;

public enum Topping implements MenuItem {
    SODA("SODA","Soda",1.00),
    CHEESE("CHEESE","Cheese", .50),
    CHIPS("CHIPS","Chips",1.25),
    LETTUCE("LETTUCE","Lettuce", .10),
    ONION("ONION","Onion", .15),
    TOMATO("TOMATO","Tomato", .20);

    private final String name;
    private final String description;
    private final double price;

    Topping(String name, String description, double price) {
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

package emc.burger;

import emc.components.Bread;
import emc.components.Meat;
import emc.components.Topping;
import java.util.ArrayList;
import java.util.List;

public abstract class Burger {
    private final String name;
    private Bread bread;
    private Meat meat;
    private final List<Topping> toppingsList;
    private double price;

    public Burger(String name) {
        this.name = name;
        this.toppingsList = new ArrayList<>();
    }

    public Burger(String name, Bread bread, Meat meat) {
        this.name = name;
        this.bread = bread;
        this.meat = meat;
        this.toppingsList = new ArrayList<>();
        // this.price = this.bread.getPrice() + this.meatType.getPrice() + toppingPriceTotal();
    }


    public void addBreadType(int chosenBreadNumber) {
        int counter = 0;

        for (Bread bread: Bread.values()) {
            if(counter == chosenBreadNumber -1) {
                this.bread = Bread.valueOf(bread.getDisplayName());
                break;
            } else {
                counter++;
            }
        }
    }

    public void addMeatType(int chosenMeatNumber) {
        int counter = 0;

        for (Meat meat: Meat.values()) {
            if(counter == chosenMeatNumber -1) {
                this.meat = Meat.valueOf(meat.getDisplayName());
                break;
            } else {
                counter++;
            }
        }
    }

    public void addToppings(Topping topping) {
        toppingsList.add(topping);
    }

    public void addToppings(int chosenToppingNumber) {
        int counter = 0;
        for (Topping topping: Topping.values()) {
            if(counter == chosenToppingNumber -1) {
                toppingsList.add(Topping.valueOf(topping.getDisplayName()));
                break;
            } else {
                counter++;
            }
        }
    }

    public void removeTopping() {
        if(!toppingsList.isEmpty()) {
            toppingsList.remove(toppingsList.size()-1);
        }
    }

    public void burgerPriceTotal() {
        this.price = this.bread.getDisplayPrice() + this.meat.getDisplayPrice() + toppingPriceTotal();
    }

    private double toppingPriceTotal() {
        double price = 0.0;
        if (toppingsList.isEmpty()) {
            return 0.0;
        }

        for (Topping toppingPrice : toppingsList) {
            price += toppingPrice.getDisplayPrice();
        }
        return price;
    }

    public String getName() {
        return name;
    }

    public Bread getBread() {
        return bread;
    }

    public Meat getMeat() {
        return meat;
    }

    public List<Topping> getToppingsList() {
        return toppingsList;
    }

    public double getPrice() {
        return price;
    }
}



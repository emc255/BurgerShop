package emc.burger;

import emc.components.Bread;
import emc.components.Meat;
import emc.components.Topping;

public class DeluxeBurger extends Burger{


    public DeluxeBurger() {
        super("Deluxe Burger");
        super.addToppings(Topping.SODA);
        super.addToppings(Topping.CHIPS);
    }

}

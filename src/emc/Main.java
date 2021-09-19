package emc;

import emc.burger.BasicBurger;
import emc.burger.Burger;
import emc.burger.DeluxeBurger;
import emc.burger.HealthyBurger;
import emc.components.Bread;
import emc.components.Meat;
import emc.components.MenuItem;
import emc.components.Topping;

import java.util.*;

public class Main {
    private static final String CREATE_YOUR_OWN_BURGER = "Create Your Own Burger";
    private static final String DELUXE_BURGER = "Deluxe Burger";
    private static final String HEALTHY_BURGER = "Healthy Burger";
    private static final String EXIT = "Exit";

    private static final List<String> BURGER_NAMES = new ArrayList<>(Arrays.asList(EXIT, CREATE_YOUR_OWN_BURGER, DELUXE_BURGER, HEALTHY_BURGER));
    private static final String BREAD = "Bread";
    private static final String MEAT = "Meat";
    private static final String TOPPING = "Topping";

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        Menu();
        int maxNumberOfBurgerNames = BURGER_NAMES.size();
        int choice = getUserChoice(maxNumberOfBurgerNames);

        while (true) {
            if (choice > -1) {
                for (int i = 0; i < BURGER_NAMES.size(); i++) {
                    if (i == choice) {
                        chooseBread(BURGER_NAMES.get(i));
                        break;
                    }
                }
                break;
            } else {
                Menu();
                choice = getUserChoice(maxNumberOfBurgerNames);
            }
        }
    }

    public static void chooseBread(String choice) {
        int chosenBreadNumber = -1;
        int maxNumberOfBreadItems = Bread.values().length + 1;
        switch (choice) {
            case CREATE_YOUR_OWN_BURGER:
                BasicBurger basicBurger = new BasicBurger();
                displayMenu(Bread.values(), BREAD);
                chosenBreadNumber = getUserChoice(maxNumberOfBreadItems);

                while (true) {
                    if (chosenBreadNumber > -1) {
                        if (chosenBreadNumber == 0) {
                            System.out.println("Order Cancel....");
                        } else if (chosenBreadNumber == maxNumberOfBreadItems) {
                            start();
                        } else {
                            basicBurger.addBreadType(chosenBreadNumber);
                            chooseMeat(basicBurger);
                        }
                        break;
                    } else {
                        displayMenu(Bread.values(), BREAD);
                        chosenBreadNumber = getUserChoice(maxNumberOfBreadItems);
                    }
                }
                break;
            case DELUXE_BURGER:
                DeluxeBurger deluxeBurger = new DeluxeBurger();
                displayMenu(Bread.values(), BREAD);
                chosenBreadNumber = getUserChoice(maxNumberOfBreadItems);

                while (true) {
                    if (chosenBreadNumber > -1) {
                        if (chosenBreadNumber == 0) {
                            System.out.println("Order Cancel....");
                        } else if (chosenBreadNumber == maxNumberOfBreadItems) {
                            start();
                        } else {
                            deluxeBurger.addBreadType(chosenBreadNumber);
                            chooseMeat(deluxeBurger);
                        }
                        break;
                    } else {
                        displayMenu(Bread.values(), BREAD);
                        chosenBreadNumber = getUserChoice(maxNumberOfBreadItems);
                    }
                }
                break;
            case HEALTHY_BURGER:
                HealthyBurger healthyBurger = new HealthyBurger();
                chooseTopping(healthyBurger);
                break;
            default:
                System.out.println("Thank You");
                break;
        }
    }

    public static void chooseMeat(Burger burger) {
        int chosenMeatNumber = -1;
        int maxNumberOfMeatItems = Meat.values().length + 1;
        displayMenu(Meat.values(), MEAT);
        chosenMeatNumber = getUserChoice(maxNumberOfMeatItems);
        while (true) {
            if (chosenMeatNumber > -1) {
                if (chosenMeatNumber == 0) {
                    System.out.println("Order Cancel....");
                } else if (chosenMeatNumber == maxNumberOfMeatItems) {
                    chooseBread(burger.getName());
                } else {
                    burger.addMeatType(chosenMeatNumber);
                    if (burger.getName().equals(DELUXE_BURGER)) {
                        payNowDisplay(burger);
                    } else {
                        chooseTopping(burger);
                    }
                }
                break;
            } else {
                displayMenu(Meat.values(), MEAT);
                chosenMeatNumber = getUserChoice(maxNumberOfMeatItems);
            }
        }
    }


    public static void chooseTopping(Burger burger) {
        int chosenToppingNumber = -1;
        int maxNumberOfToppingItems = Topping.values().length + 2;
        displayMenuForTopping(Topping.values(), TOPPING);
        chosenToppingNumber = getUserChoice(maxNumberOfToppingItems);

        while (true) {
            if (chosenToppingNumber > -1) {
                if (chosenToppingNumber == 0) {
                    System.out.println("Cancelling");
                } else if (chosenToppingNumber == maxNumberOfToppingItems - 1) {
                    if (burger.getToppingsList().isEmpty()) {
                        if (burger.getName().equals(HEALTHY_BURGER)) {
                            start();
                        } else {
                            chooseMeat(burger);
                        }
                        break;
                    } else {
                        burger.removeTopping();
                        if (burger.getToppingsList().isEmpty()) {
                            if (burger.getName().equals(HEALTHY_BURGER)) {
                                start();
                            } else {
                                chooseMeat(burger);
                            }
                            break;
                        }

                        chooseTopping(burger);
                    }

                } else if (chosenToppingNumber == maxNumberOfToppingItems) {
                    payNowDisplay(burger);
                } else {
                    burger.addToppings(chosenToppingNumber);
                    addMoreTopping(burger);
                }
                break;
            } else {
                displayMenuForTopping(Topping.values(), TOPPING);
                chosenToppingNumber = getUserChoice(maxNumberOfToppingItems);
            }
        }

    }

    public static void addMoreTopping(Burger burger) {
        if(burger.getName().equals(HEALTHY_BURGER)) {
            if(burger.getToppingsList().size() < 4) {
                chooseTopping(burger);
            } else {
                payNowDisplay(burger);
            }
        } else {
            if(burger.getToppingsList().size() < 6) {
                chooseTopping(burger);
            } else {
                payNowDisplay(burger);
            }
        }
    }

    public static void payNowDisplay(Burger burger) {

        System.out.println("===========================");
        System.out.println(burger.getName());
        System.out.println(burger.getBread().getDisplayDescription());
        System.out.println(burger.getMeat().getDisplayDescription());
        for (Topping topping : burger.getToppingsList()) {
            System.out.println(topping.getDisplayDescription());
        }
        burger.burgerPriceTotal();
        System.out.println("Total " + burger.getPrice());
        System.out.println("===========================");
    }

    public static void Menu() {
        System.out.println("Welcome, Please Choose Your Burger");
        for (int i = 0; i < BURGER_NAMES.size(); i++) {
            System.out.println(i + " -> " + BURGER_NAMES.get(i));
        }
    }

    public static void displayMenu(MenuItem[] menuItems, String description) {
        int num = 0;

        System.out.println("Choose " + description + " Type");
        System.out.println(num++ + " -> Quit");
        for (MenuItem menuItem : menuItems) {
            Formatter formatter = new Formatter();
            formatter.format("%.2f", menuItem.getDisplayPrice());
            System.out.println(num++ + " -> " + menuItem.getDisplayDescription() + " = $" + formatter);
        }
        System.out.println(num + " -> Back");

    }

    public static void displayMenuForTopping(MenuItem[] menuItems, String description) {
        int num = 0;

        System.out.println("Choose " + description + " Type");
        System.out.println(num++ + " -> Quit");
        for (MenuItem menuItem : menuItems) {
            Formatter formatter = new Formatter();
            formatter.format("%.2f", menuItem.getDisplayPrice());
            System.out.println(num++ + " -> " + menuItem.getDisplayDescription() + " = $" + formatter);
        }
        System.out.println(num++ + " -> Back");
        System.out.println(num + " -> PAY NOW");

    }

    public static int getUserChoice(int numberOfItems) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int num = sc.nextInt();
            if (num <= numberOfItems && num >= 0) {
                return num;
            }
        }
        return -1;
    }

}

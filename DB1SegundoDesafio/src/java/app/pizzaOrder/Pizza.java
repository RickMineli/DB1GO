package app.pizzaOrder;

import app.enums.Topping;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private Integer id;
    private List<Topping> toppings = new ArrayList<>();

    public Pizza(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public List<Topping> getToppings() {
        return toppings;
    }


    public void addTopping(Topping newTopping){
        this.toppings.add(newTopping);

    }
}

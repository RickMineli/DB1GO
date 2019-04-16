package app.pizzaOrder;

import app.enums.Topping;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PizzaTest {
    Pizza pizza = new Pizza(1);

    @Test
    public void shouldCreatePizza(){
        Assert.assertEquals((Integer)1,pizza.getId());
    }

    @Test
    public void shouldAddTopping(){
        pizza.addTopping(Topping.ONION);
        pizza.addTopping(Topping.MOZZARELLA);
        pizza.addTopping(Topping.PEPPERONI);
        List<Topping> expected = new ArrayList<>();
        expected.add(Topping.ONION);
        expected.add(Topping.MOZZARELLA);
        expected.add(Topping.PEPPERONI);
        Assert.assertEquals(expected,pizza.getToppings());
    }
}

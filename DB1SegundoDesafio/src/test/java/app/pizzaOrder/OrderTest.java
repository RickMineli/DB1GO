package app.pizzaOrder;

import app.enums.Topping;
import org.junit.Assert;
import org.junit.Test;

public class OrderTest {
    Customer customer = new Customer(1, "Ricardo", 123456, "rick@gmail.com");
    Order order = new Order(1, "Rua", customer);


    @Test
    public void shouldCreateOrder() {
        Assert.assertEquals((Integer) 1, order.getId());
        Assert.assertEquals("Rua", order.getAddress());
        Assert.assertEquals((Integer) 1, order.getCustomer().getId());
        Assert.assertEquals("Ricardo", customer.getName());
    }

    @Test
    public void shouldAddPizza() {
        order.addPizza(new Pizza(1));
        order.getPizzaById(1).addTopping(Topping.CHICKEN);
        Assert.assertEquals(1, order.getPizzas().size());
    }

    @Test
    public void shouldFinalizeOrder() {
        order.addPizza(new Pizza(1));
        order.getPizzaById(1).addTopping(Topping.CHICKEN);
        order.getPizzaById(1).addTopping(Topping.PEPPERONI);
        order.getPizzaById(1).addTopping(Topping.OLIVE);
        order.finalizeOrder();
        Assert.assertEquals(30d, order.getExpectedTime(),0);
        Assert.assertEquals(6d, order.getPrice(),0);
    }

    @Test
    public void shouldSetPaymentCreditCard() {
        order.addPizza(new Pizza(1));
        order.getPizzaById(1).addTopping(Topping.CHICKEN);
        order.finalizeOrder();
        order.setPaymentCredit("123456", 1221, 123);
        Assert.assertEquals("123456", ((CreditCard) order.getPayment()).getNumber());
        Assert.assertEquals((Integer) 123, ((CreditCard) order.getPayment()).getSecurityNumber());
    }

    @Test
    public void shouldSetPaymentCash() {
        order.addPizza(new Pizza(1));
        order.getPizzaById(1).addTopping(Topping.CHICKEN);
        order.finalizeOrder();
        order.setPaymentCash(10d);
        Assert.assertEquals(10d, ((Cash) order.getPayment()).getCashAmount(), 0);
        Assert.assertEquals(8d, ((Cash) order.getPayment()).getChange(), 0);
    }

}

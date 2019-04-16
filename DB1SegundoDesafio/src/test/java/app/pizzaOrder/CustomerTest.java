package app.pizzaOrder;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {
    Customer customer = new Customer(1, "Ricardo", 123456, "rick@gmail.com");

    @Test
    public void shouldCreateCustomer() {
        Assert.assertEquals((Integer) 1, customer.getId());
        Assert.assertEquals("Ricardo", customer.getName());
        Assert.assertEquals((Integer) 123456, customer.getPhoneNumber());
        Assert.assertEquals("rick@gmail.com", customer.getEmail());
    }

}

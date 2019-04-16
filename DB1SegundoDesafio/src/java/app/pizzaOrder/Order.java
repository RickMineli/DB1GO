package app.pizzaOrder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final Integer id;
    private String address;
    private Double price;
    private Double expectedTime;
    private LocalDateTime orderDate;
    private Boolean isPickup;
    private List<Pizza> pizzas = new ArrayList<>();
    private Payment payment;
    private Customer customer;

    public Order(Integer id,String address, Customer customer) {
        this.id = id;
        this.address = address;
        this.orderDate = LocalDateTime.now();
        this.isPickup = false;
        this.customer = customer;
    }

    public String getAddress() {
        return address;
    }

    public Double getPrice() {
        return price;
    }

    public Double getExpectedTime() {
        return expectedTime;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Boolean getPickup() {
        return isPickup;
    }

    public Integer getId() {
        return id;
    }

    public Payment getPayment() {
        return payment;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setPickup(Boolean pickup) {
        isPickup = pickup;
    }

    public Pizza getPizzaById(Integer id){
        for (int i = 0; i <pizzas.size(); i++){
            if (pizzas.get(i).getId() == id){
                return pizzas.get(i);
            }
        }
        return null;
    }



    public void addPizza(Pizza pizza){
        this.pizzas.add(pizza);
    }
    public void setPaymentCash(Double cashAmount){
        this.payment = new Cash(cashAmount,this.price);
    }
    public void setPaymentCredit(String number,Integer expDate, Integer securityNumber){
        this.payment = new CreditCard(number,expDate,securityNumber,this.price);
    }

    public void finalizeOrder(){
        Integer toppingAmount =0;
        for (int i = 0; i < pizzas.size();i++)
            toppingAmount += pizzas.get(i).getToppings().size();
        this.price = toppingAmount * 2d;
        this.expectedTime = toppingAmount * 10d;
    }
}


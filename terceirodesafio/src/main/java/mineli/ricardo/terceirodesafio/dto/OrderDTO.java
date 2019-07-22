package mineli.ricardo.terceirodesafio.dto;

import mineli.ricardo.terceirodesafio.model.enums.Topping;

import java.util.List;

public class OrderDTO {

    private String address;
    private Boolean pickup;
    private List<Topping> toppings;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getPickup() {
        return pickup;
    }

    public void setPickup(Boolean pickup) {
        this.pickup = pickup;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }
}

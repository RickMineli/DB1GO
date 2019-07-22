package mineli.ricardo.terceirodesafio.model;

import mineli.ricardo.terceirodesafio.model.enums.Topping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "topping")
    private List<Topping> toppings = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "pizza_order_id", nullable = false)
    private Order order;

    protected Pizza() {
    }

    public Pizza(Order order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return getId().equals(pizza.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

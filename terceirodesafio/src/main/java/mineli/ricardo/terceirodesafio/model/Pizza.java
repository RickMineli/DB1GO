package mineli.ricardo.terceirodesafio.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topping", nullable = false)
    private String topping;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pizza_order_id", nullable = false)
    private Order order;

    protected Pizza() {
    }

    public Pizza(String topping, Order order) {
        this.topping = topping;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

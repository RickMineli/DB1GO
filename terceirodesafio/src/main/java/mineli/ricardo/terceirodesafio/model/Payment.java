package mineli.ricardo.terceirodesafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "payment")
public abstract class Payment {

    @Id
    private Long id;

    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "date", nullable = false)
    private Date date;

    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    @JsonIgnore
    private Order order;

    protected Payment (){}

    public Payment(Double price, Date date, Order order) {
        this.price = price;
        this.date = date;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

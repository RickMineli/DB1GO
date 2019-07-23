package mineli.ricardo.terceirodesafio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class CreditCard extends Payment{

    @Column(name = "number")
    private Integer number;

    protected CreditCard() {
    }

    public CreditCard(Double price, Date date, Order order, Integer number) {
        super(price, date, order);
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


}

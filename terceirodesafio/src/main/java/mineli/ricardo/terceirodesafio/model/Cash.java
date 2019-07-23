package mineli.ricardo.terceirodesafio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Cash extends Payment {

    @Column(name = "change")
    private Double change;

    @Column(name = "cashAmount")
    private Double cashAmount;

    public Cash(Double price, Date date, Order order, Double change, Double cashAmount) {
        super(price, date, order);
        this.change = change;
        this.cashAmount = cashAmount;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public Double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Double cashAmount) {
        this.cashAmount = cashAmount;
    }
}

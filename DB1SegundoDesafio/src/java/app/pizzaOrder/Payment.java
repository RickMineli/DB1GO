package app.pizzaOrder;

import java.time.LocalDateTime;


public abstract class Payment {
    private Double price;
    private LocalDateTime date;
    private Boolean paid;

    public Payment(Double price) {
        this.price = price;
        this.date = LocalDateTime.now();
        this.paid = false;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void finalizePayment(){
        this.paid = true;
    }
}

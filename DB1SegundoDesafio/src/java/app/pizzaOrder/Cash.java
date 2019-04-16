package app.pizzaOrder;


public class Cash extends Payment {
    private Double change;
    private Double cashAmount;

    public Cash(Double cashAmount, Double price) {
        super(price);
        this.cashAmount = cashAmount;
        this.calcChange();
    }

    public Double getChange() {
        return change;
    }

    public Double getCashAmount() {
        return cashAmount;
    }

    public void calcChange(){
        this.change = cashAmount - this.getPrice();
    }

    @Override
    public String toString() {
        return "Cash{" +
                "price=" + this.getPrice() +
                ", date=" + this.getDate() +
                ", paid=" + getPaid() +
                ", change=" + change +
                ", cashAmount=" + cashAmount +
                '}';
    }
}

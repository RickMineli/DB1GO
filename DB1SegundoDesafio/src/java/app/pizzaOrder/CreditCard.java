package app.pizzaOrder;

public class CreditCard extends Payment {
    private String number;
    private Integer expDate;
    private Integer securityNumber;

    public CreditCard(String number, Integer expDate, Integer securityNumber, Double price) {
        super(price);
        this.number = number;
        this.expDate = expDate;
        this.securityNumber = securityNumber;
    }

    public String getNumber() {
        return number;
    }

    public Integer getExpDate() {
        return expDate;
    }

    public Integer getSecurityNumber() {
        return securityNumber;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "price=" + getPrice() +
                ", date=" + getDate() +
                ", paid=" + getPaid() +
                ", number=" + number +
                ", expDate=" + expDate +
                ", securityNumber=" + securityNumber +
                '}';
    }

}

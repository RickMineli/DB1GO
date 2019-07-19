package mineli.ricardo.terceirodesafio.dto;

public class OrderDTO {

    private String adress;
    private Boolean pickup;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Boolean getPickup() {
        return pickup;
    }

    public void setPickup(Boolean pickup) {
        this.pickup = pickup;
    }
}

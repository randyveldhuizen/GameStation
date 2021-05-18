package applications;

import org.json.JSONObject;

public class Applications {

    private String name;
    private double price;

    public Applications(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Applications(JSONObject jsonObject) {
        this.name = jsonObject.getString("name");
        this.price = jsonObject.getDouble("price");
    }



    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.name + " (€" + this.price + ")";
    }
}

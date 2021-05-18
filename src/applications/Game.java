package applications;

import org.json.JSONObject;

public class Game extends Applications {

    /*
     * Methoden
     */

    private int minimumAge;
    private int maxPlayers;
    private int rating;


    /*
     * Dubbele constructor.
     */

    public Game(String name, double price, int minimumAge, int maxPlayers, int rating) {
        super(name, price);
        this.minimumAge = minimumAge;
        this.maxPlayers = maxPlayers;
        this.rating = rating;
    }

    /*
     * constructor met JSON import
     */

    public Game(JSONObject object) {
        super(object);
        this.minimumAge = object.getInt("minimumAge");
        this.maxPlayers = object.getInt("maxPlayers");
        this.rating = object.getInt("rating");
    }


    @Override
    public String toString() {
        return "Game " + super.toString() + " (Rating: " + this.rating + "). Max " + this.maxPlayers + " players with a minimum age of " + this.minimumAge;
    }
}

package user;

import applications.Applications;
import exceptions.GameStationException;

import java.util.ArrayList;

public class User {

    private String username;
    private double balance;
    private ArrayList<Applications> appsowned = new ArrayList<>();


    public User(String username){
        this.username = username;
    }

    /**
     * Adds balance to the entered username.
     * @param value Amount of money to be added.
     */

    public void addBalance(double value){
        if (value > 0){
            this.balance+= value;
        }
    }


    /**
     * Adds games and apps to username.
     * @param app Entered game/application.
     * @throws exceptions.GameStationException When balance is too low.
     */
    public void addOwnedApp(Applications app) throws GameStationException {
        if (balance < app.getPrice()){
            throw new GameStationException("Balance too low!");
        }

        this.balance -= app.getPrice();
        this.appsowned.add(app);
    }


    /*
    Getters
    */

    public String getUsername() {
        return username;
    }


    @Override
    public String toString(){
        String string = "User " + this.username + " has a remaining balance of €" + this.balance;
        string += "\n\tOwned apps:";

        for (Applications app : appsowned) {
            string += "\n\t\t- " + app;
        }

        return string;
    }

}
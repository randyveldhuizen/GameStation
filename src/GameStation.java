import applications.Applications;
import applications.Game;
import applications.MediaApp;
import exceptions.GameStationException;
import user.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;

public class GameStation {

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Applications> apps = new ArrayList<>();

    /**
     * Creates a new user profile.
     * @param username The chosen username for the system.
     * @throws exceptions.GameStationException When the username already exists.
     */

    public void addUser(String username) throws GameStationException {
        User user = findUser(username);

        if (user != null) {
            throw new GameStationException("User " + username + " already exists!");
        }

        this.users.add(new User(username));
    }


    /**
     * Adds a new game to the system by manual entry.
     * @param name Name of the game.
     * @param price How much the game costs.
     * @param minimumAge If the game has an age rating.
     * @param maxPlayers If the game has options for multiplayer
     * @param rating Rating set by GameSpy
     * @throws exceptions.GameStationException If the game already exists.
     */
    public void addGame(String name, double price, int minimumAge, int maxPlayers, int rating) throws GameStationException {
        Applications app = findApp(name);
        if (app != null) {
            throw new GameStationException("Game " + name + " already exists!");
        }

        this.apps.add(new Game(name, price, minimumAge, maxPlayers, rating));
    }


    /**
     * Adding credit to existing users.
     * @param username The user you want to add credits to.
     * @param value The amount of money added to user's wallet.
     * @throws GameStationException When a user doesn't exists.
     */
    public void addBalanceToUser(String username, double value) throws GameStationException {
        User user = findUser(username);

        // If user doesn't exist
        if (user == null) {
            throw new GameStationException("User " + username + " doesn't exist!");
        }

        user.addBalance(value);
    }

    /**
     * Buy any app available in the store. Wallet is checked by addOwnedApp. Gives error when there is not enough money.
     * @param username User that wants the game.
     * @param appname The app the user wants to buy.
     * @throws GameStationException When a user or application doesn't exist.
     */

    public void buyAppForUser(String username, String appname) throws GameStationException {
        User user = findUser(username);

        if (user == null) {
            throw new GameStationException("User " + username + " doesn't exist!");
        }

        Applications app = findApp(appname);

        if (app == null) {
            throw new GameStationException("Application " + appname + " doesn't exists!");
        }

        user.addOwnedApp(app);
    }


    /**
     * Adds games to the store via a JSON file. Done with JSON.simple.
     * @param filename The JSON file that gets imported.
     * @throws IOException
     * @throws GameStationException Gives error when a game with the same title already exists.
     */

    public void addGameByFile(String filename) throws IOException, GameStationException {
        JSONArray jsonArray = new JSONArray(new String(Files.readAllBytes(Paths.get(filename))));

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (findApp(jsonObject.getString("name")) != null) {
                throw new GameStationException("Game " + jsonObject.getString("name") + " already exists!");
            }

            this.apps.add(new Game(jsonObject));
        }
    }


    /**
     * Adds a App to the store.
     * @param name Name of the application.
     * @param price The costs of the application.
     * @param type Media the app is for (video, audio etc).
     * @throws GameStationException
     */
    public void addMediaApp(String name, double price, String type) throws GameStationException {
        Applications app = findApp(name);
        if (app != null) {
            throw new GameStationException("MediaApp " + name + " already exists!");
        }

        this.apps.add(new MediaApp(name, price, type));
    }


    /**
     * Checksum for addUser.
     * @param username Name of the user about to be created.
     * @return If the username does not exist, it gives back a null and the slot will be filled
     * Is the username already in use, it gives back the said username and addUser will give an error.
     */
    private User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }

        return null;
    }

    /**
     * Checksum for all games and apps.
     * @param name Name of the to be added game/app.
     */

    private Applications findApp(String name) {
        for (Applications app : apps) {
            if (app.getName().equalsIgnoreCase(name)) {
                return app;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        String string = "GameStation administration:\n";
        string += "\nUsers:";

        for (User user : users) {
            string += "\n" + user;
        }

        string += "\nApps:";

        for (Applications app : apps) {
            string += "\n" + app;
        }

        return string;
    }
}

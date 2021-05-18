import exceptions.GameStationException;

import java.io.IOException;

public class Apl {

    public static void main(String[] args) {
        new Apl().run();
    }

    private void run() {
        GameStation gameStation = new GameStation();

        try {

            gameStation.addGameByFile("games.json");

            gameStation.addUser("Truus");
            gameStation.addUser("Gerald");


            gameStation.addGame("Rocket League", 20, 16, 1,5);

            gameStation.addMediaApp("Netflix app", 0, "Video Entertainment app");
            gameStation.addMediaApp("Spotify app", 9.99, "Audio Entertainment app");

            gameStation.addBalanceToUser("Gerald", 100);
            gameStation.addBalanceToUser("Truus", 39.99);

            gameStation.buyAppForUser("Gerald", "The Witcher 3");
            gameStation.buyAppForUser("Truus", "Cities: Skylines");
            gameStation.buyAppForUser("Truus", "Netflix app");

            System.out.println(gameStation);

        } catch (GameStationException gse) {
            System.out.println(gse.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }
}

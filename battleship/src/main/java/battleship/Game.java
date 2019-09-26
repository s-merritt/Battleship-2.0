package battleship;

import java.util.concurrent.TimeUnit;

import exceptions.LocationAlreadyGuessedException;
import exceptions.LocationOutOfBoundsException;
import objects.Coordinate;
import objects.Location;
import objects.Ship;
import objects.User;
import objects.computers.HunterComputer;

/**
 * Hello world!
 *
 */
public class Game {
    public static void main(String[] args) {
        User u = new User();
        HunterComputer c = new HunterComputer();

        // set up opponents
        u.setOpponentPlayer(c);
        c.setOpponentPlayer(u);

        try {
            u.makeGuess(new Coordinate('F', 8));
        } catch (Exception e) {
            System.out.println("oof");
        }

        try {
            u.createAndPlaceShip(4, "battleship", Ship.Orientation.VERTICAL, new Coordinate('B', 3));
            u.createAndPlaceShip(3, "cruiser", Ship.Orientation.HORIZONTAL, new Coordinate('A', 7));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("YOUR SHIPS ===");
        u.showShips();

        System.out.println("FROM COMPUTER ===");
        c.getOpponentPlayer().GetPlayerGrid().showShips();

        Location.Status s = null;

        while (true) {
            try {
                s = c.makeGuess();
                System.out.println(s.toString());
                c.showGuesses();

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                }
            } catch (LocationAlreadyGuessedException e) {
                System.out.println("Already Guessed!");
            } catch (LocationOutOfBoundsException e) {
                System.out.println("Out of bounds!");
            }

        }

    }
}

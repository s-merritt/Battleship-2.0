package battleship;

import java.util.concurrent.TimeUnit;

import exceptions.LocationAlreadyGuessedException;
import exceptions.LocationOutOfBoundsException;
import objects.Coordinate;
import objects.Location;
import objects.Ship;
import objects.User;
import objects.computers.HunterComputer;
import objects.computers.SimpleComputer;

/**
 * Hello world!
 *
 */
public class Game {
    public static void main(String[] args) {
        User u = new User();
        HunterComputer c = new HunterComputer();

        try {
            u.makeGuess(new Coordinate('F', 8), c);
        } catch (Exception e) {
            System.out.println("oof");
        }

        try {
            u.createAndPlaceShip(4, "battleship", Ship.Orientation.VERTICAL, new Coordinate('B', 3));
            u.createAndPlaceShip(3, "cruiser", Ship.Orientation.HORIZONTAL, new Coordinate('G', 2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        u.showShips();

        Location.Status s = null;

        while (true) {
            try {
                s = c.makeGuess(u);
                System.out.println(s.toString());
                c.showGuesses(u);

                try {
                    TimeUnit.SECONDS.sleep(2);
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

package battleship;

import exceptions.LocationAlreadyGuessedException;
import exceptions.LocationOutOfBoundsException;
import objects.Coordinate;
import objects.Location;
import objects.Ship;
import objects.User;
import objects.computers.SimpleComputer;

/**
 * Hello world!
 *
 */
public class Game {
    public static void main(String[] args) {
        User u = new User();
        SimpleComputer c = new SimpleComputer();

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

        try {
           // s = c.makeGuess(u);
           s = c.makeGuess(u);
        } catch (LocationAlreadyGuessedException e) {
            System.out.println("Already Guessed!");
        } catch (LocationOutOfBoundsException e) {
            System.out.println("Out of bounds!");
        }

        if (s != null) {
            System.out.println(s.toString());
        }

        c.showGuesses(u);

    }
}

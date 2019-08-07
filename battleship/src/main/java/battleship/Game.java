package battleship;

import objects.*;

/**
 * Hello world!
 *
 */
public class Game {
    public static void main(String[] args) {
        User u = new User();

        try {
            u.makeGuess(new Coordinate('F', 8));
        } catch (Exception e) {
            System.out.println("oof");
        }

        u.showGuesses();

        try {
            u.createAndPlaceShip(4, "battleship", Ship.Orientation.VERTICAL, new Coordinate('B', 3));
            u.createAndPlaceShip(3, "cruiser", Ship.Orientation.HORIZONTAL, new Coordinate('G', 2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        u.showShips();

    }
}

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
            u.GetPlayerGrid().checkLocationForShip(1, 3);
        } catch (Exception e) {
            System.out.println("oof");
        }

        u.GetPlayerGrid().showGuesses();

        Ship s1 = new Ship(4, "battleship", Ship.Orientation.VERTICAL, new Coordinate('A', 3));
        Ship s2 = new Ship(3, "cruiser", Ship.Orientation.HORIZONTAL, new Coordinate('D', 5));

        try {
            u.GetPlayerGrid().setShip(s1);
            u.GetPlayerGrid().setShip(s2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        u.GetPlayerGrid().showShips();

    }
}

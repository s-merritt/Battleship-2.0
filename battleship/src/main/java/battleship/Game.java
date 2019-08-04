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

        Ship s = new Ship(4);

        try {
            u.GetPlayerGrid().setShip(3, 4, s, false);
        } catch (Exception e) {
            System.out.println("oof");
        }

        u.GetPlayerGrid().showShips();


    }
}

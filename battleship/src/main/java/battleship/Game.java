package battleship;

import objects.computers.*;
import objects.*;
import objects.Ship.Orientation;

import java.util.Scanner;

import exceptions.LocationAlreadyOccupiedException;
import exceptions.LocationOutOfBoundsException;

/**
 * @class Game
 * 
 *        Contains the game itself, alternating turns until one player is the
 *        winner
 *
 */
public class Game {
  private static final Scanner in = new Scanner(System.in);

  private Computer computer;
  private User user;

  private Player current_attacker;

  /**
   * Default constructor
   */
  public Game() {
    this.computer = new HunterComputer();
    this.user = new User();
  }

  public int UserPlaceShips() {
    System.out.println("It's time for you to place your ships.");
    Pause(1);
    System.out.println("We'll start with the largest one and work our way down.");
    System.out.println(
        "You will provide a coordinate and orientation for the ship. The ship will placed starting at that coordinate, \nand place it in the direction that you specified (top-down for vertical ships, left-right for horizontal ships).");
    Pause(2);
    System.out.println(
        "We'll check to make sure that the ship can be placed there first; if the spot you chose was invalid, you can try again");
    Pause(1);

    for (int i = 0; i < Player.NUM_STARTING_SHIPS;) { // only increment i if we placed a ship
      int len = Player.SHIP_LENGTHS[i];
      String ship_name = Player.SHIP_NAMES[i];
      System.out.println("Placing ship of length " + len + ", " + ship_name);
      Pause(1);

      boolean valid = false;
      Coordinate head = new Coordinate(-1, -1);
      while (!valid) {
        System.out.println("Enter a coordinate (e.g. A1 or a1)");
        String raw_coord = in.next();

        head = parseRawCoordinate(raw_coord.toUpperCase());

        if (head != null) {
          valid = true;
        } else {
          System.out.println("Please try again");
        }
      }

      valid = false;
      Ship.Orientation orientation = Orientation.HORIZONTAL;
      in.nextLine(); // eat up new line character
      while (!valid) {
        System.out.println("Now enter an orientation: v (for vertical) or h (for horizontal)");

        String orien_input = in.next();

        if (orien_input.equals("v")) {
          orientation = Ship.Orientation.VERTICAL;
          valid = true;
        } else if (orien_input.equals("h")) {
          orientation = Ship.Orientation.HORIZONTAL;
          valid = true;
        } else {
          System.out.println("Invalid orientation, please try again");
        }
      }

      // try to place the ship, only increment the counter if we succeed, else the
      // user will have to try again
      try {
        this.user.createAndPlaceShip(len, ship_name, orientation, head);
        i++;
        this.user.GetPlayerGrid().showShips();
      } catch (LocationAlreadyOccupiedException e) {
        System.out.println("Overlapping ships detected! Please try again...");
      } catch (LocationOutOfBoundsException e) {
        System.out.println("Ship placement was outside of the grid! Please try again...");
      }
    }

    return 0;
  }

  public void ComputerPlaceShips() {
    System.out.println("Now it's the computers turn to place their ships.");
    Pause(1);
    this.computer.placeShips();
    System.out.println("All done.");

    //TODO debug only
    this.computer.GetPlayerGrid().showShips();
  }

  public int CheckWin() {
    return 0;
  }

  public void Pause(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      System.exit(1);
    }
  }

  public Coordinate parseRawCoordinate(String raw_coord) {
    // max string size would be 3 characters (e.g. A10)
    if (raw_coord.length() > 3) {
      System.out.println("Invalid Coordinate, too long!");
      return null;
    }

    char col = raw_coord.charAt(0);
    if (!Character.isLetter(col)) {
      System.out.println("Invalid Coordinate, first character was not a letter!");
      return null;
    }

    String raw_row = raw_coord.substring(1);
    int row = -1;
    try {
      row = Integer.parseInt(raw_row);
    } catch (NumberFormatException e) {
      System.out.println("Invalid Coordiante, given row is not an integer!");
      return null;
    }

    Coordinate coord = new Coordinate(col, row);

    if (Grid.withinBounds(coord.getGridRow(), coord.getGridCol())) {
      return coord;
    } else {
      System.out.println("Invalid Coordinate, not within grid");
      return null;
    }
  }

  /**
   * main function to call for running the game
   * 
   * @return result code (0 is all good, not zero means error)
   */
  public int Run() {
    return 0;
  }

  public static void main(String[] args) {
    Game g = new Game();

 //   g.UserPlaceShips();
    g.ComputerPlaceShips();

  }
}

package objects;

import java.util.Scanner;

import exceptions.LocationAlreadyGuessedException;
import exceptions.LocationAlreadyOccupiedException;
import exceptions.LocationOutOfBoundsException;
import objects.Ship.Orientation;

public class User extends Player {
  public static final Scanner IN = new Scanner(System.in);

  public User() {
    super();
  }

  @Override
  public void placeShips() {
    System.out.println("It's time for you to place your ships.");
    Pause(1);
    System.out.println("We'll start with the largest one and work our way down.");
    Pause(1);
    System.out.println(
        "You will provide a coordinate and orientation for the ship. The ship will placed starting at that coordinate, and place it in the direction that you specified (top-down for vertical ships, left-right for horizontal ships).");
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
        String raw_coord = User.IN.next();

        head = parseRawCoordinate(raw_coord.toUpperCase());

        if (head != null) {
          valid = true;
        } else {
          System.out.println("Please try again");
        }
      }

      valid = false;
      Ship.Orientation orientation = Orientation.HORIZONTAL;
      User.IN.nextLine(); // eat up new line character
      while (!valid) {
        System.out.println("Now enter an orientation: v (for vertical) or h (for horizontal)");

        String orien_input = User.IN.next();

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
        this.createAndPlaceShip(len, ship_name, orientation, head);
        i++;
        this.GetPlayerGrid().showShips();
      } catch (LocationAlreadyOccupiedException e) {
        System.out.println("Overlapping ships detected! Please try again...");
      } catch (LocationOutOfBoundsException e) {
        System.out.println("Ship placement was outside of the grid! Please try again...");
      }
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

  public boolean GetUserGuess() {
    Coordinate coord = new Coordinate(-1, -1);
    Location.Status status = Location.Status.UNGUESSED;
    while (true) {
      System.out.println("Enter a coordinate (e.g. A1 or a1)");
      String raw_coord = User.IN.next();

      coord = parseRawCoordinate(raw_coord.toUpperCase());

      if (coord == null) {
        System.out.println("Please try again");
        Pause(1);
        continue;
      }

      try {
        status = this.makeGuess(coord);
        break;
      } catch (LocationOutOfBoundsException e) {
        System.out.println("That coordinate is out of bounds! Try again...");
        Pause(1);
        continue;
      } catch (LocationAlreadyGuessedException e) {
        System.out.println("You already guessed that coordiante! Try again...");
        Pause(1);
        continue;
      }
    }
    boolean wasHit = false;
    if (status == Location.Status.HIT) {
      wasHit = true;
      System.out.println("HIT, you get to go again!");
    } else if (status == Location.Status.MISS) {
      System.out.println("MISS, your turn is over...");
    } else {
      // error
      System.out.println("Something went wrong!");
      System.exit(1);
    }
    Pause(1);
    System.out.println("Here's a board with your guesses again...");
    Pause(1);
    this.showGuesses();

    return wasHit;
  }
}

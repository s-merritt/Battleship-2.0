package objects;

import exceptions.LocationAlreadyGuessedException;
import exceptions.LocationAlreadyOccupiedException;
import exceptions.LocationOutOfBoundsException;
import interfaces.Resettable;
import objects.Location.Status;

/**
 * Grid class that will contain a 2D array of Locations
 */
public class Grid implements Resettable {
  /**
   * Max number of rows for the grid
   */
  public static final int MAX_ROWS = 10;
  /**
   * Max number of columns for the grid
   */
  public static final int MAX_COLS = 10;

  /**
   * The Game grid itself
   */
  private Location[][] grid;

  /**
   * Default Constructor
   */
  public Grid() {
    this.grid = new Location[MAX_ROWS][MAX_COLS];
    for (int row = 0; row < MAX_ROWS; row++)
      for (int col = 0; col < MAX_COLS; col++)
        grid[row][col] = new Location();
  }

  /**
   * Method for placing a ship of given length starting from the given coordinate.
   * If the ship is horizontal, it will be placed starting from the head
   * coordinate and build to the right; if the ship is vertical, it will be placed
   * starting from the head coordinate and build downward
   * 
   * @param headRow
   * @param headCol
   * @param Ship
   * @param isHorizontal
   * @throws LocationOutOfBoundsException
   */
  public void setShip(int headRow, int headCol, Ship ship, boolean isHorizontal)
      throws LocationOutOfBoundsException, LocationAlreadyOccupiedException {
    if (isHorizontal) { // only column number changes
      // check that ship will be within bounds
      if (headCol + ship.getLength() > MAX_COLS) {
        throw new LocationOutOfBoundsException("Invalid Ship Location: out of bounds!");
      }
      // check that ship will not overlap existing ship
      for (int i = 0; i < ship.getLength(); i++) {
        if (grid[headRow][headCol + i].hasShip()) {
          throw new LocationAlreadyOccupiedException("Invalid Ship Location: ship will overlap with existing ship!");
        }
      }

      // place ship
      for (int i = 0; i < ship.getLength(); i++) {
        setShip(headRow, headCol + i);
      }
    } else { // vertical, only row number changes
      // check that ship will be within bounds
      if (headRow + ship.getLength() > MAX_ROWS) {
        throw new LocationOutOfBoundsException("Invalid Ship Location: out of bounds!");
      }
      // check that ship will not overlap existing ship
      for (int i = 0; i < ship.getLength(); i++) {
        if (grid[headRow + i][headCol].hasShip()) {
          throw new LocationAlreadyOccupiedException("Invalid Ship Location: ship will overlap with existing ship!");
        }
      }

      // place ship
      for (int i = 0; i < ship.getLength(); i++) {
        setShip(headRow + i, headCol);
      }
    }
  }

  /**
   * Sets a ship at the given location
   * 
   * @param row row number (0-9)
   * @param col col number (0-9)
   * @throws LocationAlreadyOccupiedException, LocationOutOfBoundsException
   */
  public void setShip(int row, int col) throws LocationAlreadyOccupiedException, LocationOutOfBoundsException {
    // check that coords are within bounds
    if (!withinBounds(row, col)) {
      throw new LocationOutOfBoundsException("Location out of bounds!");
    }

    // check that Location does not have ship already
    // if not, mark that is does; if it does, throw exception
    if (this.grid[row][col].hasShip()) {
      throw new LocationAlreadyOccupiedException("There's already a ship at that location!");
    } else {
      this.grid[row][col].setShip();
    }
  }

  /**
   * Called when a Player makes a guess
   * 
   * @param row
   * @param col
   * @return Location.Status HIT or MISS
   * @throws LocationAlreadyGuessedException
   */
  public Location.Status checkLocationForShip(int row, int col) throws LocationAlreadyGuessedException {
    Location l = this.grid[row][col];
    // check if Location has already been guessed
    if (l.getStatus() != Status.UNGUESSED) {
      throw new LocationAlreadyGuessedException("Location has already been guessed!");
    }

    if (l.hasShip()) {
      this.grid[row][col].markHit();
      return Location.Status.HIT;
    } else {
      this.grid[row][col].markMiss();
      return Location.Status.MISS;
    }

  }

  /**
   * Helper method for checking if a guess was within the bounds of the Grid
   * 
   * @param row
   * @param col
   */
  public boolean withinBounds(int row, int col) {
    return row < MAX_ROWS && row >= 0 && col < MAX_COLS && col >= 0;
  }

  public void showGuesses() {
    System.out.print("   ");
    for (int i = 0; i < MAX_COLS; i++) {
      System.out.print((char) (i + 65) + " ");
    }
    System.out.println();

    for (int row = 0; row < MAX_ROWS; row++) {
      if (row < 9) {
        System.out.print(row + 1 + "  ");
      } else {
        System.out.print(row + 1 + " ");
      }
      for (int col = 0; col < MAX_COLS; col++) {
        if (grid[row][col].getStatus() == Status.MISS) {
          System.out.print("O ");
        } else if (grid[row][col].getStatus() == Status.HIT) {
          System.out.print("X ");
        } else {
          System.out.print("- ");
        }
      }
      System.out.println();
    }

  }

  public void showShips() {
    System.out.print("   ");
    for (int i = 0; i < MAX_COLS; i++) {
      System.out.print((char) (i + 65) + " ");
    }
    System.out.println();

    for (int row = 0; row < MAX_ROWS; row++) {
      if (row < 9) {
        System.out.print(row + 1 + "  ");
      } else {
        System.out.print(row + 1 + " ");
      }
      for (int col = 0; col < MAX_COLS; col++) {
        if (grid[row][col].hasShip()) {
          System.out.print("X ");
        } else {
          System.out.print("- ");
        }
      }
      System.out.println();
    }

  }

  /**
   * Reset method override, resets the Grid
   */
  public void Reset() {
    for (Location[] row : this.grid) {
      for (Location l : row) {
        l.Reset();
      }
    }
  }

}
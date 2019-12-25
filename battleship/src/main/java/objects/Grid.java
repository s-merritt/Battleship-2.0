package objects;

import exceptions.LocationAlreadyGuessedException;
import exceptions.LocationAlreadyOccupiedException;
import exceptions.LocationOutOfBoundsException;
import interfaces.Resettable;
import objects.Location.Status;
import objects.Ship.Orientation;

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
   * 
   * TODO(merritt) make this configurable eventually?
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
   * @param ship Ship object
   * @throws LocationOutOfBoundsException
   * @throws LocationAlreadyOccupiedException
   */
  public void setShip(Ship ship) throws LocationOutOfBoundsException, LocationAlreadyOccupiedException {
    int headRow = ship.getHead().getGridRow();
    int headCol = ship.getHead().getGridCol();
    int len = ship.getLength();

    if (ship.getOrientation() == Orientation.HORIZONTAL) { // only column number (Y) changes
      // check that ship will be within bounds
      if (headCol + len > MAX_COLS) {
        throw new LocationOutOfBoundsException("Invalid Ship Location: out of bounds!");
      }
      // check that ship will not overlap existing ship
      for (int i = 0; i < len; i++) {
        if (grid[headRow][headCol + i].hasShip()) {
          throw new LocationAlreadyOccupiedException("Invalid Ship Location: ship will overlap with existing ship!");
        }
      }

      // place ship
      for (int i = 0; i < len; i++) {
        setShip(headRow, headCol + i, ship.getID());
      }
    } else { // vertical, only row number (X) changes
      // check that ship will be within bounds
      if (headRow + len > MAX_ROWS) {
        throw new LocationOutOfBoundsException("Invalid Ship Location: out of bounds!");
      }
      // check that ship will not overlap existing ship
      for (int i = 0; i < len; i++) {
        if (grid[headRow + i][headCol].hasShip()) {
          throw new LocationAlreadyOccupiedException("Invalid Ship Location: ship will overlap with existing ship!");
        }
      }

      // place ship
      for (int i = 0; i < len; i++) {
        setShip(headRow + i, headCol, ship.getID());
      }
    }
  }

  /**
   * Marks the specified coordinate to have a Ship in that Location
   * 
   * Does NOT check bounds as the caller does that already
   * 
   * @param row row number (0-9)
   * @param col col number (0-9)
   * @param id  id of the ship being set
   */
  public void setShip(int row, int col, int id) {
    this.grid[row][col].setShip(id);
  }

  /**
   * Called when a Player makes a guess
   * 
   * @param row
   * @param col
   * @return shipID (> 0) if HIT, 0 if MISS
   * @throws LocationAlreadyGuessedException
   * @throws LocationOutOfBoundsException
   */
  public int checkLocationForShip(Coordinate c) throws LocationAlreadyGuessedException, LocationOutOfBoundsException {
    int row = c.getGridRow();
    int col = c.getGridCol();

    if (!withinBounds(row, col)) {
      throw new LocationOutOfBoundsException("Out Location out of bounds!");
    }

    // check if Location has already been guessed
    if (this.grid[row][col].getStatus() != Status.UNGUESSED) {
      throw new LocationAlreadyGuessedException("Location has already been guessed!");
    }

    if (this.grid[row][col].hasShip()) {
      this.grid[row][col].markHit();
      return this.grid[row][col].getShipID();
    } else {
      this.grid[row][col].markMiss();
      return 0;
    }

  }

  /**
   * Helper function that only peeks at the Location of the given coordinate. That
   * is, it will not mark the Location in any way, it will only return 1 for
   * success or throw an exception
   * 
   * @param c Coordinate of interest
   */
  public int PeekLocation(Coordinate c) throws LocationAlreadyGuessedException, LocationOutOfBoundsException {
    int row = c.getGridRow();
    int col = c.getGridCol();

    if (!withinBounds(row, col)) {
      throw new LocationOutOfBoundsException("Out Location out of bounds!");
    }

    // check if Location has already been guessed
    if (this.grid[row][col].getStatus() != Status.UNGUESSED) {
      throw new LocationAlreadyGuessedException("Location has already been guessed!");
    }

    return 1;
  }

  /**
   * Helper method for checking if a guess was within the bounds of the Grid
   * 
   * @param row
   * @param col
   */
  public static boolean withinBounds(int row, int col) {
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
    System.out.println(" ====== Your Board ====== ");
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
   * Helper function to get the Location at a given coordinate
   * 
   * @param c coordinate of interest
   * @return Location at the given coordinate
   * @throws LocationOutOfBoundsException
   */
  public Location at(Coordinate c) throws LocationOutOfBoundsException {
    if (!withinBounds(c.getGridRow(), c.getGridCol()))
      throw new LocationOutOfBoundsException("Coordinate out of bounds!");
    else
      return grid[c.getGridRow()][c.getGridCol()];
  }

  public Location[][] getGridData() {
    return this.grid;
  }

  /**
   * Reset method override, resets the Grid
   */
  @Override
  public void Reset() {
    for (Location[] row : this.grid) {
      for (Location l : row) {
        l.Reset();
      }
    }
  }

}
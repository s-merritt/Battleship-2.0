package main.java.objects;

import main.java.exceptions.*;
import main.java.interfaces.Resettable;

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
  private Location[][] grid = new Location[MAX_ROWS][MAX_COLS];

  /**
   * Default Constructor
   */
  public Grid() {
  }

  /**
   * Method for placing a ship of given length starting from the given coordinate.
   * If the ship is horizontal, it will be placed starting from the head
   * coordinate and build to the right; if the ship is vertical, it will be placed
   * starting from the head coordinate and build downward
   * 
   * @param headRow
   * @param headCol
   * @param length       length of the ship
   * @param isHorizontal
   * @throws LocationOutOfBoundsException
   */
  public void setShip(int headRow, int headCol, int length, boolean isHorizontal)
      throws LocationOutOfBoundsException, LocationAlreadyOccupiedException {
    if (isHorizontal) { // only column number changes
      // check that ship will be within bounds
      if (headCol + length > MAX_COLS) {
        throw new LocationOutOfBoundsException("Invalid Ship Location: out of bounds!");
      }
      // check that ship will not overlap existing ship
      for (int i = 0; i < length; i++) {
        if (grid[headRow][headCol + i].hasShip()) {
          throw new LocationAlreadyOccupiedException("Invalid Ship Location: ship will overlap with existing ship!");
        }
      }

      // place ship
      for (int i = 0; i < length; i++) {
        setShip(headRow, headCol + i);
      }
    } else { // vertical, only row number changes
      // check that ship will be within bounds
      if (headRow + length > MAX_ROWS) {
        throw new LocationOutOfBoundsException("Invalid Ship Location: out of bounds!");
      }
      // check that ship will not overlap existing ship
      for (int i = 0; i < length; i++) {
        if (grid[headRow + i][headCol].hasShip()) {
          throw new LocationAlreadyOccupiedException("Invalid Ship Location: ship will overlap with existing ship!");
        }
      }

      // place ship
      for (int i = 0; i < length; i++) {
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
   * @return whether or not the Location has a Ship in it
   * @throws LocationAlreadyGuessedException
   */
  public boolean checkLocationForShip(int row, int col) throws LocationAlreadyGuessedException {
    // check if Location has already been guessed
    if (this.grid[row][col].hasBeenTargeted()) {
      throw new LocationAlreadyGuessedException("Location has already been guessed!");
    } else {
      // mark that Location as guessed
      this.grid[row][col].markGuessed();
    }

    return this.grid[row][col].hasShip();
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
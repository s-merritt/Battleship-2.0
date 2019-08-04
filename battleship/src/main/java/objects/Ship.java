package objects;

import interfaces.Resettable;

/**
 * Ship class
 * 
 */
public class Ship implements Resettable {
  /**
   * length of the ship, cannot be changed after the ship is created
   */
  private final int length;

  /**
   * Ship's status
   */
  private boolean isSunk = false;

  /**
   * Constructs ship with desired length
   * 
   * @param length
   */
  public Ship(int length) {
    this.length = length;
  }

  /**
   * length getter
   * 
   * @return length
   */
  public int getLength() {
    return this.length;
  }

  /**
   * Checks ships status
   * 
   * @return isSunk
   */
  public boolean isSunk() {
    return this.isSunk;
  }

  /**
   * sets the Ship to sunk
   */
  public void sinkShip() {
    this.isSunk = true;
  }

  /**
   * Reset the Ship
   */
  public void Reset() {
    this.isSunk = false;
  }
}
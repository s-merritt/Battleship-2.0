package objects;

import interfaces.Resettable;

/**
 * Ship class
 * 
 */
public class Ship implements Resettable {

  public enum Status {
    UNPLACED, PLACED, SUNK
  }

  public enum Orientation {
    HORIZONTAL, VERTICAL
  }

  /**
   * length of the ship, cannot be changed after the ship is created
   */
  private final int length;

  /**
   * Ship's status
   */
  private Status status;

  /**
   * Ship's name
   */
  private String name;

  /**
   * Ship's orientation
   */
  private Orientation orientation;

  /**
   * Head of the ship, starting point for how the ship is placed
   */
  private Coordinate head;

  /**
   * Constructs ship with desired length
   * 
   * @param length
   */
  public Ship(int length, String name, Orientation orientation, Coordinate head) {
    this.status = Status.UNPLACED;
    this.length = length;
    this.name = name;
    this.orientation = orientation;
    this.head = head;
  }

  /**
   * Head getter
   * @return head
   */
  public Coordinate getHead(){
    return this.head;
  }

  /**
   * Orientation getter
   */
  public Orientation getOrientation() {
    return this.orientation;
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
   * name getter
   * 
   * @return name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Checks ships status
   * 
   * @return isSunk
   */
  public boolean isSunk() {
    return this.status == Status.SUNK;
  }

  /**
   * sets the Ship to sunk
   */
  public void sinkShip() {
    this.status = Status.SUNK;
  }

  /**
   * sets ship to placed
   */
  public void shipPlaced() {
    this.status = Status.PLACED;
  }

  /**
   * Reset the Ship
   */
  public void Reset() {
    this.status = Status.UNPLACED;
  }
}
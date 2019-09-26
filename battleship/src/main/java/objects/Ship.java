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
   * Health of a ship, i.e. how many unhit spots it has left
   */
  private int health;

  /**
   * ID of the ship
   */
  private int id;

  /**
   * Constructs a ship
   * 
   * @param id
   * @param length
   * @param name
   * @param orientation
   * @param head
   */
  public Ship(int id, int length, String name, Orientation orientation, Coordinate head) {
    this.id = id;
    this.status = Status.UNPLACED;
    this.health = this.length = length;
    this.name = name;
    this.orientation = orientation;
    this.head = head;
  }

  /**
   * Head getter
   * 
   * @return head
   */
  public Coordinate getHead() {
    return this.head;
  }

  /**
   * ID getter
   * 
   * @return id
   */
  public int getID() {
    return this.id;
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
   * health getter
   * 
   * @return health
   */
  public int getHealth() {
    return this.health;
  }

  /**
   * Decrement ship's health
   * 
   * If the ship's health reaches zero, the ship is sunk
   */
  public void decrementHealth() {
    this.health--;
    if (health == 0) {
      this.sinkShip();
    }
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
   * head coordinate setter
   * 
   * @param head
   */
  public void setHead(Coordinate head) {
    this.head = head;
  }

  /**
   * Reset the Ship
   */
  public void Reset() {
    this.health = this.length;
    this.status = Status.UNPLACED;
  }
}
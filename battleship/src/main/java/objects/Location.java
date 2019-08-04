package objects;

import interfaces.Resettable;

/**
 * Location class that will hold data about a location on the game grid
 */
public class Location implements Resettable {
  /**
   * whether or not the Location has a Ship in it
   */
  private boolean occupied = false;

  /**
   * whether or not the Player has attacked this Location
   */
  private boolean targeted = false;

  /**
   * Default Constructor
   */
  public Location() {
  }

  /**
   * Checks if the Location has a ship in it
   * 
   * @return occupied
   */
  public boolean hasShip() {
    return this.occupied;
  }

  /**
   * Checks if the Location has been guessed
   * 
   * @return targeted
   */
  public boolean hasBeenTargeted() {
    return this.targeted;
  }

  /**
   * Marks the Location as occupied
   */
  public void setShip() {
    this.occupied = true;
  }

  /**
   * Marks the Location has having been guessed
   */
  public void markGuessed() {
    this.targeted = true;
  }

  /**
   * Resets the Location to its initial values
   */
  public void Reset() {
    this.occupied = false;
    this.targeted = false;
  }

}
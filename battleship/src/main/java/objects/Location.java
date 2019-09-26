package objects;

import interfaces.Resettable;

/**
 * Location class that will hold data about a location on the game grid
 */
public class Location implements Resettable {
  public enum Status {
    UNGUESSED, MISS, HIT
  }

  /**
   * holds the ID of the ship occupy this Location
   */
  private int shipID = 0;

  /**
   * whether or not the Location has a Ship in it
   */
  private boolean occupied = false;

  /**
   * whether or not the Player has attacked this Location
   */
  private Status status = Status.UNGUESSED;

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
  public Status getStatus() {
    return this.status;
  }

  /**
   * ship ID getter
   * 
   * @return shipID
   */
  public int getShipID() {
    return this.shipID;
  }

  /**
   * ship ID setter
   * 
   * @param shipID
   */
  public void setShipID(int shipID) {
    this.shipID = shipID;
  }

  /**
   * Marks the Location as occupied
   */
  public void setShip(int id) {
    this.occupied = true;
    this.shipID = id;
  }

  /**
   * Marks the Location as a miss
   */
  public void markMiss() {
    this.status = Status.MISS;
  }

  /**
   * Marks the Location has a hit
   */
  public void markHit() {
    this.status = Status.HIT;
  }

  /**
   * Resets the Location to its initial values
   */
  public void Reset() {
    this.occupied = false;
    this.status = Status.UNGUESSED;
  }

}
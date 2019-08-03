package main.java.objects;

import main.java.interfaces.Resettable;

/**
 * Abstract Player class, contains important player data for the game
 */
public abstract class Player implements Resettable {
  /**
   * number of ships each player starts with
   */
  private int numShips = 5;

  /**
   * number of hits the player has made
   */
  private int numHits = 0;

  /**
   * number of misses the player has made
   */
  private int numMisses = 0;

  /**
   * contains all ships placed by Player
   */
  private Grid playerGrid;

  /**
   * default constructor
   */
  public Player() {
  }

  /**
   * Player's Grid getter
   */
  public Grid GetPlayerGrid() {
    return this.playerGrid;
  }

  /**
   * numShips getter
   * 
   * @return numShips
   */
  public int GetNumShips() {
    return this.numShips;
  }

  /**
   * numHits getter
   * 
   * @return numHits
   */
  public int GetNumHits() {
    return this.numHits;
  }

  /**
   * numMisses getter
   * 
   * @return numMisses
   */
  public int getNumMisses() {
    return this.numMisses;
  }

  /**
   * increments numHits
   */
  public void incrementHits() {
    this.numHits++;
  }

  /**
   * increments numMisses
   */
  public void incrementMisses() {
    this.numMisses++;
  }

  /**
   * decrements numShips
   */
  public void sinkShip() {
    this.numShips--;
  }

  /**
   * Resets the Player's data to what it would be at the start of the game
   */
  public void Reset() {
    this.numHits = 0;
    this.numMisses = 0;
    this.numShips = 5;

    // TODO clear grid
  }

}
package objects;

import interfaces.Resettable;

/**
 * Abstract Player class, contains important player data for the game
 */
public abstract class Player implements Resettable {
  /**
   * starting number of ships
   */
  private static final int NUM_STARTING_SHIPS = 5;

  /**
   * ship lengths
   */
  private static final int[] SHIP_LENGTHS = { 2, 3, 3, 4, 5 };

  /**
   * number of ships each player starts with
   */
  private int numRemainingShips = 5;

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
   * array of player ships
   */
  private Ship[] ships;

  /**
   * default constructor
   */
  public Player() {
    this.playerGrid = new Grid();
    this.ships = new Ship[NUM_STARTING_SHIPS];
    for (int i = 0; i < NUM_STARTING_SHIPS; i++) {
      this.ships[i] = new Ship(Player.SHIP_LENGTHS[i]);
    }
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
  public int GetNumRemainingShips() {
    return this.numRemainingShips;
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
    this.numRemainingShips--;
  }

  /**
   * Resets the Player's data to what it would be at the start of the game
   */
  public void Reset() {
    this.numHits = 0;
    this.numMisses = 0;
    this.numRemainingShips = NUM_STARTING_SHIPS;

    this.playerGrid = new Grid();
    this.ships = new Ship[NUM_STARTING_SHIPS];
    for (int i = 0; i < NUM_STARTING_SHIPS; i++) {
      this.ships[i] = new Ship(Player.SHIP_LENGTHS[i]);
    }
  }

}
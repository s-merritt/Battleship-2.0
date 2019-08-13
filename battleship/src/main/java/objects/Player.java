package objects;

import java.util.HashMap;
import java.util.Map;

import exceptions.LocationAlreadyGuessedException;
import exceptions.LocationAlreadyOccupiedException;
import exceptions.LocationOutOfBoundsException;
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
  private Map<String, Ship> ships;

  /**
   * default constructor
   */
  public Player() {
    this.playerGrid = new Grid();
    this.ships = new HashMap<String, Ship>(NUM_STARTING_SHIPS);
    // each ship will get initialized later
  }

  public void createAndPlaceShip(int length, String name, Ship.Orientation orientation, Coordinate head)
      throws LocationAlreadyOccupiedException, LocationOutOfBoundsException {

    // create new ship and add to hasmap
    Ship s = new Ship(length, name, orientation, head);
    this.ships.put(name, s);

    // attempt to place ship
    this.playerGrid.setShip(this.ships.get(name));
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
   * Makes a guess on the opponent player's grid
   * 
   * @param c the coordinate of the guess being made
   * @param p the opponent player
   * @return Status of the Location (i.e. HIT or MISS)
   * @throws LocationOutOfBoundsException
   * @throws LocationAlreadyGuessedException
   */
  public Location.Status makeGuess(Coordinate c, Player p)
      throws LocationOutOfBoundsException, LocationAlreadyGuessedException {
    return p.GetPlayerGrid().checkLocationForShip(c);
  }

  /**
   * Shows the guess of the current player on the given opponent's board
   * 
   * @param p the opponent player
   */
  public void showGuesses(Player p) {
    p.GetPlayerGrid().showGuesses();
  }

  /**
   * Shows the ships of the player on their on grid
   */
  public void showShips() {
    this.playerGrid.showShips();
  }

  /**
   * Resets the Player's data to what it would be at the start of the game
   */
  public void Reset() {
    this.numHits = 0;
    this.numMisses = 0;
    this.numRemainingShips = NUM_STARTING_SHIPS;

    this.playerGrid = new Grid();
    this.ships = new HashMap<String, Ship>(NUM_STARTING_SHIPS);
  }

}
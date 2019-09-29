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
  public static final int NUM_STARTING_SHIPS = 5;

  /**
   * ship lengths
   */
  public static final int[] SHIP_LENGTHS = { 5, 4, 3, 3, 2 };

  /**
   * default ship names
   */
  public static final String[] SHIP_NAMES = { "Aircraft Carrier", "Battleship", "Missile Cruiser", "Submarine",
      "Patrol Boat" };

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
   * latest id for new ships
   */
  private Integer shipID = 0;

  /**
   * array of player ships
   */
  private Map<Integer, Ship> ships;

  /**
   * Opponent of this Player
   */
  protected Player opponent;

  /**
   * default constructor
   */
  public Player() {
    this.playerGrid = new Grid();
    this.ships = new HashMap<Integer, Ship>(NUM_STARTING_SHIPS);
    // each ship will get initialized later
  }

  public void createAndPlaceShip(int length, String name, Ship.Orientation orientation, Coordinate head)
      throws LocationAlreadyOccupiedException, LocationOutOfBoundsException {

    // create new ship and add to hasmap
    Ship s = new Ship(++this.shipID, length, name, orientation, head);
    this.ships.put(this.shipID, s);

    // attempt to place ship
    this.playerGrid.setShip(this.ships.get(this.shipID));
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
  public void loseShip() {
    this.numRemainingShips--;
  }

  /**
   * opponent Player getter
   * 
   * @return this.opponent
   */
  public Player getOpponentPlayer() {
    return this.opponent;
  }

  /**
   * opponent Player setter
   * 
   * @param p the opponent Player
   */
  public void setOpponentPlayer(Player p) {
    this.opponent = p;
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
  public Location.Status makeGuess(Coordinate c) throws LocationOutOfBoundsException, LocationAlreadyGuessedException {
    int id = this.opponent.GetPlayerGrid().checkLocationForShip(c);

    if (id > 0) {
      this.opponent.LoseShipHealth(id);
      this.incrementHits();
      if (this.opponent.shipWasSunk(id)) {
        this.opponent.loseShip();
      }
      return Location.Status.HIT;
    } else {
      this.incrementMisses();
      return Location.Status.MISS;
    }
  }

  /**
   * Decrements the health of the Ship with the given ID
   * 
   * @param id
   */
  public void LoseShipHealth(int id) {
    this.ships.get(id).decrementHealth();
    ;
  }

  /**
   * Checks if the ship with the given id was sunk
   * 
   * @param id
   */
  public boolean shipWasSunk(int id) {
    return this.ships.get(id).isSunk();
  }

  /**
   * Shows the guess of the current player on the given opponent's board
   */
  public void showGuesses() {
    System.out.println(" ===== Your Guesses =====");
    this.opponent.GetPlayerGrid().showGuesses();
  }

  /**
   * Shows the ships of the player on their on grid
   */
  public void showShips() {
    this.playerGrid.showShips();
  }

  public abstract void placeShips();

  public static void Pause(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      System.exit(1);
    }
  }

  /**
   * Resets the Player's data to what it would be at the start of the game
   */
  @Override
  public void Reset() {
    this.numHits = 0;
    this.numMisses = 0;
    this.numRemainingShips = NUM_STARTING_SHIPS;

    this.playerGrid = new Grid();
    this.ships = new HashMap<Integer, Ship>(NUM_STARTING_SHIPS);
  }

}
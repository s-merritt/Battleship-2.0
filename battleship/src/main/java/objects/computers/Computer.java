package objects.computers;

import java.util.LinkedList;
import java.util.Random;

import exceptions.LocationAlreadyGuessedException;
import exceptions.LocationAlreadyOccupiedException;
import exceptions.LocationOutOfBoundsException;
import objects.Coordinate;
import objects.Player;
import objects.Location.Status;
import objects.Ship.Orientation;

public abstract class Computer extends Player {

  protected boolean foundShip;

  protected LinkedList<Coordinate> nextGuesses;

  protected Random rand;

  public Computer() {
    this.foundShip = false;
    this.nextGuesses = new LinkedList<Coordinate>();
    this.rand = new Random();
  }

  public abstract Status makeGuess() throws LocationOutOfBoundsException, LocationAlreadyGuessedException;

  public abstract Coordinate makeNewGuess();

  public void addGuess(Coordinate coord) {
    this.nextGuesses.push(coord);
  }

  public Coordinate getNextGuess() {
    if (!this.hasGuesses()) {
      return null;
    }

    return nextGuesses.pop();
  }

  public boolean hasGuesses() {
    return !this.nextGuesses.isEmpty();
  }

  /**
   * Computer places their ships randomly on the board
   */
  @Override
  public void placeShips() {
    for (int i = 0; i < NUM_STARTING_SHIPS;) {
      // random place on the grid
      int len = SHIP_LENGTHS[i];
      String ship_name = SHIP_NAMES[i];
      Coordinate head = new Coordinate(rand.nextInt(10), rand.nextInt(10));
      Orientation orientation = Orientation.HORIZONTAL;

      // try to place the ship, only increment the counter if we succeed, else the
      // user will have to try again
      try {
        this.createAndPlaceShip(len, ship_name, orientation, head);
        i++;
      } catch (LocationAlreadyOccupiedException e) {
        // do nothing, try again
      } catch (LocationOutOfBoundsException e) {
        // do nothing, try again
      }
    }
  }
}

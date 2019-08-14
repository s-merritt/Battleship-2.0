package objects.computers;

import java.util.ArrayList;

import exceptions.LocationAlreadyGuessedException;
import exceptions.LocationOutOfBoundsException;
import objects.Coordinate;
import objects.Location;
import objects.Player;
import objects.Coordinate.NeighboringDirection;

public class HunterComputer extends Computer {
  private enum AttackDirection {
    VERTICAL, HORIZONTAL, UNSET
  }

  private AttackDirection attackDirection;

  /**
   * Coordinate of previous Hit
   */
  private Coordinate prevHit;

  public HunterComputer() {
    super();
    attackDirection = AttackDirection.UNSET;
    prevHit = null;
  }

  /**
   * Overrides abstract method from Computer class.
   * 
   * Makes a guess based on whether or not it has guesses saved up or not. If not,
   * it will generate a new guess
   * 
   * @throws LocationOutOfBoundsException
   * @throws LocationAlreadyGuessedException
   */
  @Override
  public Location.Status makeGuess(Player p) throws LocationOutOfBoundsException, LocationAlreadyGuessedException {
    Coordinate nextGuess;
    if (hasGuesses()) { // get a guess if it already has one
      nextGuess = getNextGuess();
    } else { // make a new guess (random)
      nextGuess = makeNewGuess();
    }

    // TODO debug
    System.out.println("computers guess: " + nextGuess.toString());

    Location.Status result = makeGuess(nextGuess, p);

    if (result == Location.Status.HIT) {
      // if new hit is next to previous hit, we know the direction of the ship and can
      // narrow down our search for the next Coordinate that has a Ship
      if (prevHit != null) {
        this.attackDirection = translateDirection(nextGuess.Neighbor(prevHit));
        trimGuesses();
        System.out.println("attack dir: " + this.attackDirection);
      }

      // save surrounding points in order to search around that spot
      LookAround(nextGuess, p);
      this.prevHit = nextGuess;
    }

    return result;
  }

  /**
   * Overrides function from Computer class
   * 
   * Generates a new guess randomly
   * 
   * @return generated Coordinate
   */
  @Override
  public Coordinate makeNewGuess() {
    // random guess on the grid
    int row = rand.nextInt(10);
    int col = rand.nextInt(10);

    return new Coordinate(row, col);
  }

  /**
   * 'Looks around' the given coordinate to save those Coordinates for future
   * guesses. Only saves Coordinates that haven't been checked and are in the
   * bounds of the grid.
   * 
   * @param c Coordinate to search around
   * @param p Opponent
   */
  public void LookAround(Coordinate c, Player p) {
    char gameCol = c.getGameCol();
    int gameRow = c.getGameRow();

    ArrayList<Coordinate> temp = new ArrayList<Coordinate>(4);
    if (attackDirection != AttackDirection.HORIZONTAL) {
      // Look up
      Coordinate up = new Coordinate(gameCol, gameRow - 1);
      temp.add(up);

      // Look down
      Coordinate down = new Coordinate(gameCol, gameRow + 1);
      temp.add(down);
    }

    if (attackDirection != AttackDirection.VERTICAL) {
      // Look left
      Coordinate left = new Coordinate((char) (gameCol - 1), gameRow);
      temp.add(left);

      // Look right
      Coordinate right = new Coordinate((char) (gameCol + 1), gameRow);
      temp.add(right);
    }

    for (Coordinate coord : temp) {
      System.out.println(coord.toString());
      // check if the Coordinate will be a valid guess, remove it if it is not
      try {
        p.GetPlayerGrid().PeekLocation(coord);

        // if passed through try-catch, must be valid coordiante
        this.nextGuesses.push(coord);
      } catch (LocationAlreadyGuessedException e) {
      } catch (LocationOutOfBoundsException e) {
      }
    }
  }

  public void trimGuesses() {
    if (attackDirection == AttackDirection.UNSET)
      return;

    for (Coordinate c : this.nextGuesses) {
      // remove any coordinates that aren't in the attack direction
      if (translateDirection(c.Neighbor(this.prevHit)) != this.attackDirection) {
        System.out.println("removed unneeded coord");
        nextGuesses.remove(c);
      }
    }
  }

  public AttackDirection translateDirection(Coordinate.NeighboringDirection dir) {
    switch (dir) {
    case VERTICAL:
      return AttackDirection.VERTICAL;
    case HORIZONTAL:
      return AttackDirection.HORIZONTAL;
    default:
      return AttackDirection.UNSET;
    }
  }

}
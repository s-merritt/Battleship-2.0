package objects.computers;

import exceptions.LocationAlreadyGuessedException;
import exceptions.LocationOutOfBoundsException;
import objects.Coordinate;
import objects.Location;

/**
 * Simple Computer class. It will only make random guesses against the player
 */
public class SimpleComputer extends Computer {
  /**
   * Default constructor
   */
  public SimpleComputer() {
    super();
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
  public Location.Status makeGuess() throws LocationOutOfBoundsException, LocationAlreadyGuessedException {
    // computer will only make a random guess
    Coordinate nextGuess = makeNewGuess();
    
    // TODO debug
    System.out.println("computers guess: " + nextGuess.toString());

    return makeGuess(nextGuess);
  }

  /**
   * Overrides function from the Computer class.
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

}

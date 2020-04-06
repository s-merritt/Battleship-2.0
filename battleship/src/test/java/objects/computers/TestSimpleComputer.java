package objects.computers;

import exceptions.LocationAlreadyGuessedException;
import exceptions.LocationOutOfBoundsException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import objects.computers.SimpleComputer;
import objects.Location;

/**
 * Unit tests objects.Coordinate
 */
public class TestSimpleComputer extends TestCase {
  /**
   * Create the test case
   * 
   * @param testname name of the test case
   */
  public TestSimpleComputer(String testname) {
    super(testname);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(TestSimpleComputer.class);
  }

  public void testConstructor() {
    SimpleComputer sc = new SimpleComputer();

    assertFalse(sc.foundShip);
    assertNotNull(sc.nextGuesses);
    assertNotNull(sc.rand);
  }

  public void testMakeGuess() throws LocationAlreadyGuessedException, LocationOutOfBoundsException {
    SimpleComputer sc = new SimpleComputer();
    sc.setOpponentPlayer(new SimpleComputer());

    sc.makeGuess();
    assertEquals(1, sc.getNumMisses());

    // check opponent Player's Grid for a miss
    int num_misses_found = 0;
    for (Location[] row : sc.getOpponentPlayer().getPlayerGrid().data())
      for (Location l : row)
        if (l.getStatus() == Location.Status.MISS)
          num_misses_found++;
    assertEquals(1, num_misses_found);

  }
}
package objects.computers;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import objects.Coordinate;
import objects.Grid;
import objects.Location;
import objects.computers.Computer;
import objects.computers.SimpleComputer;

/**
 * Unit tests objects.Coordinate
 */
public class TestComputer extends TestCase {
  /**
   * Create the test case
   * 
   * @param testname name of the test case
   */
  public TestComputer(String testname) {
    super(testname);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(TestComputer.class);
  }

  public void testConstructor() {
    Computer c = new SimpleComputer();

    assertFalse(c.foundShip);
    assertNotNull(c.nextGuesses);
    assertEquals(0, c.nextGuesses.size());
    assertNotNull(c.rand);
  }

  public void testAddGuesses() {
    Computer c = new SimpleComputer();

    // check with no guesses first
    assertFalse(c.hasGuesses());

    c.addGuess(new Coordinate('A', 1));
    assertEquals(1, c.nextGuesses.size());
    assertTrue(c.hasGuesses());
  }

  public void testNextGuess() {
    Computer c = new SimpleComputer();

    // with no guesses, should return null
    assertNull(c.getNextGuess());

    c.addGuess(new Coordinate('A', 1));
    c.addGuess(new Coordinate('B', 2));
    c.addGuess(new Coordinate('C', 3));
    assertEquals(3, c.nextGuesses.size());

    Coordinate coord = c.getNextGuess();
    assertEquals(2, c.nextGuesses.size());
    assertNotNull(coord);
    assertEquals(new Coordinate('C', 3), coord);
  }

  public void testPlaceShips() {
    Computer c = new SimpleComputer();

    // NOTE: this function utilizes java.util.Random to create random coordinates
    // for placing ships. We will assume that the randomness of the class has been
    // thoroughly tested and will not test it here
    c.placeShips();
    Grid g1 = c.getPlayerGrid();

    // check the grid to make sure 5 ships were placed correctly
    assertEquals(5, g1.getNumShips());
    int ship_spots = 0;
    for (Location[] row : g1.data())
      for (Location l : row)
        if (l.hasShip())
          ship_spots++;
    assertEquals(5 + 4 + 3 + 3 + 2, ship_spots);
  }
}
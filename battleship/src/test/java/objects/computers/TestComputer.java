package objects.computers;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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

  public void testConstructor(){
    Computer c = new SimpleComputer();

    assertFalse(c.foundShip);
    assertNotNull(c.nextGuesses);
    assertNotNull(c.rand);
  }
}
package objects.computers;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import objects.computers.SimpleComputer;

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

  public void testConstructor(){
    SimpleComputer sc = new SimpleComputer();

    assertFalse(sc.foundShip);
    assertNotNull(sc.nextGuesses);
    assertNotNull(sc.rand);
  }
}
package objects;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests for objects.Player
 */
public class TestUser extends TestCase {
  /**
   * Create the test case
   * 
   * @param testname name of the test case
   */
  public TestUser(String testname) {
    super(testname);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(TestUser.class);
  }

  public void testConstructor() {
    User u = new User();
    assertNotNull(u);
  }

  public void testPlaceShips() {
    // TODO need to devise a way to test with fake user input
    // this may require breaking up the placeShips function into
    // smaller pieces
  }
}

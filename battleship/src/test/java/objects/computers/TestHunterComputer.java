package objects.computers;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import objects.computers.HunterComputer;

/**
 * Unit tests objects.Coordinate
 */
public class TestHunterComputer extends TestCase {
  /**
   * Create the test case
   * 
   * @param testname name of the test case
   */
  public TestHunterComputer(String testname) {
    super(testname);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(TestHunterComputer.class);
  }

  public void testConstructor() {
    HunterComputer hc = new HunterComputer();

    assertFalse(hc.foundShip);
    assertNotNull(hc.nextGuesses);
    assertNotNull(hc.rand);
    assertEquals(HunterComputer.AttackDirection.UNSET, hc.getAttackDirection());
    assertNull(hc.getPreviousHit());
  }
}
package util;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import util.Timing;


/**
 * Unit tests objects.Coordinate
 */
public class TestTiming extends TestCase {

  /**
   * Create the test case
   * 
   * @param testname name of the test case
   */
  public TestTiming(String testname) {
    super(testname);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(TestTiming.class);
  }

  public void testConstructor() {
    Timing e = new Timing();
    assertNotNull(e);
  }

  public void testPause() {
    long t0 = System.currentTimeMillis();
    Timing.pause(1);
    long dur = System.currentTimeMillis() - t0;

    // time won't be exactly 1 second due to function calls,
    // but will be within a close threshold
    assertTrue(dur > 1000 && dur < 1010);
  }
}
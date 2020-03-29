package exceptions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import exceptions.TestLocationAlreadyOccupiedException;

/**
 * Unit tests objects.Coordinate
 */
public class TestLocationAlreadyOccupiedException extends TestCase {

  /**
   * Create the test case
   * 
   * @param testname name of the test case
   */
  public TestLocationAlreadyOccupiedException(String testname) {
    super(testname);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(TestLocationAlreadyOccupiedException.class);
  }

  public void testConstructor() {
    LocationAlreadyOccupiedException e = new LocationAlreadyOccupiedException();
    assertNotNull(e);
  }

  public void testMessage() {
    LocationAlreadyOccupiedException e = new LocationAlreadyOccupiedException("this is a test message");
    assertEquals("this is a test message", e.getLocalizedMessage());
  }

  public void testCause() {
    LocationAlreadyOccupiedException e = new LocationAlreadyOccupiedException(new Throwable());
    assertNotNull(e.getCause());
  }

  public void testCauseAndMessage() {
    LocationAlreadyOccupiedException e = new LocationAlreadyOccupiedException("test message", new Throwable());
    assertEquals("test message", e.getLocalizedMessage());
    assertNotNull(e.getCause());
  }

}
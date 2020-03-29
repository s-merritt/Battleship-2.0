package exceptions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import exceptions.TestLocationOutOfBoundsException;

/**
 * Unit tests objects.Coordinate
 */
public class TestLocationOutOfBoundsException extends TestCase {

  /**
   * Create the test case
   * 
   * @param testname name of the test case
   */
  public TestLocationOutOfBoundsException(String testname) {
    super(testname);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite(){
    return new TestSuite(TestLocationOutOfBoundsException.class);
  }

  public void testConstructor(){
    LocationAlreadyGuessedException e = new LocationAlreadyGuessedException();
    assertNotNull(e);
  }

  public void testMessage(){
    LocationAlreadyGuessedException e = new LocationAlreadyGuessedException("this is a test message");
    assertEquals("this is a test message", e.getLocalizedMessage());
  }

  public void testCause(){
    LocationAlreadyGuessedException e = new LocationAlreadyGuessedException(new Throwable());
    assertNotNull(e.getCause());
  }

  public void testCauseAndMessage(){
    LocationAlreadyGuessedException e = new LocationAlreadyGuessedException("test message", new Throwable());
    assertEquals("test message", e.getLocalizedMessage());
    assertNotNull(e.getCause());
  }

}
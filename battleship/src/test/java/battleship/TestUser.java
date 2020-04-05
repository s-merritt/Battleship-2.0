package battleship;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import util.TestUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import objects.*;
import objects.computers.*;

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
}

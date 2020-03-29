package battleship;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import objects.Ship;
import objects.Coordinate;

/**
 * Unit tests objects.Ship
 */
public class TestShip extends TestCase {
    /**
     * Create the test case
     * 
     * @param testname name of the test case
     */
    public TestShip(String testname) {
        super(testname);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(TestShip.class);
    }

    public void testConstructor() {
        Ship s = new Ship(1, 3, "test_ship", Ship.Orientation.HORIZONTAL, new Coordinate('B', 4));

        assertEquals(1, s.getID());
        assertEquals(3, s.getLength());
        assertEquals("test_ship", s.getName());
        assertEquals(Ship.Orientation.HORIZONTAL, s.getOrientation());
        assertEquals(new Coordinate('B', 4), s.getHead());
    }

    public void testSetHead(){
        Ship s = new Ship(1, 3, "test_ship", Ship.Orientation.HORIZONTAL, new Coordinate('B', 4));
        s.setHead(new Coordinate('A', 1));
        assertEquals(new Coordinate('A', 1), s.getHead());
    }

    public void testHealth() {
        Ship s = new Ship(1, 3, "test_ship", Ship.Orientation.HORIZONTAL, new Coordinate('B', 4));

        // check that ship is NOT sunk yet...
        assertFalse(s.isSunk());

        for (int expected = s.getHealth(); expected > 0; expected--) {
            assertEquals(expected, s.getHealth());
            s.decrementHealth();
        }

        // ship should be sunk at this point
        assertTrue(s.isSunk());
    }

    public void testReset() {
        Ship s = new Ship(1, 3, "test_ship", Ship.Orientation.HORIZONTAL, new Coordinate('B', 4));

        s.shipPlaced();
        s.decrementHealth();

        s.Reset();
        assertEquals(3, s.getHealth());
        assertEquals(Ship.Status.UNPLACED, s.getStatus());
    }
}

package battleship;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import objects.Location;
import objects.Location.Status;

/**
 * Unit tests for object.Location
 */
public class TestLocation extends TestCase {

    /**
     * Create the test case
     * 
     * @param testname name of the test case
     */
    public TestLocation(String testname) {
        super(testname);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(TestLocation.class);
    }

    public void testConstructor() {
        Location l = new Location();

        assertEquals(0, l.getShipID());
        assertEquals(Status.UNGUESSED, l.getStatus());
        assertFalse(l.hasShip());
    }

    public void testSetShip() {
        Location l = new Location();

        l.setShip(1);

        assertEquals(1, l.getShipID());
        assertTrue(l.hasShip());
    }

    public void testSetShipID() {
        Location l = new Location();

        l.setShipID(3);

        assertEquals(3, l.getShipID());
    }

    public void testMarkMiss() {
        Location l = new Location();

        l.markMiss();

        assertEquals(Status.MISS, l.getStatus());
    }

    public void testMarkHit() {
        Location l = new Location();

        l.markHit();

        assertEquals(Status.HIT, l.getStatus());
    }

    public void testReset() {
        Location l = new Location();

        // change fields in some arbitrary manor
        l.setShip(1);
        l.markHit();

        l.Reset();

        assertFalse(l.hasShip());
        assertEquals(Status.UNGUESSED, l.getStatus());
        assertEquals(0, l.getShipID());
    }

}

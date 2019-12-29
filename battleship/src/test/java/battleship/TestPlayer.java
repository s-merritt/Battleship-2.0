package battleship;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import objects.Coordinate;
import objects.Player;
import objects.Ship;
import objects.User;

/**
 * Unit tests for objects.Player
 */
public class TestPlayer extends TestCase {
    /**
     * Create the test case
     * 
     * @param testname name of the test case
     */
    public TestPlayer(String testname) {
        super(testname);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(TestPlayer.class);
    }

    public void testConstructor(){

    }

    public void testShipPlacement() throws Exception {
        Player p = new User();
        
        p.createAndPlaceShip(2, "test_ship", Ship.Orientation.VERTICAL, new Coordinate('A', 1));

        // check that player's ship map was populated
        assertTrue(p.GetNumRemainingShips() == 1);

        //check player's grid to ensure ship was placed
        assertTrue(p.GetPlayerGrid().checkLocationForShip(new Coordinate('A', 1)) > 0);
        assertTrue(p.GetPlayerGrid().checkLocationForShip(new Coordinate('A', 2)) > 0);
    }
}
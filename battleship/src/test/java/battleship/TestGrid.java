package battleship;

import exceptions.LocationAlreadyGuessedException;
import exceptions.LocationAlreadyOccupiedException;
import exceptions.LocationOutOfBoundsException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import objects.Coordinate;
import objects.Grid;
import objects.Location;
import objects.Ship;

/**
 * Unit tests for objects.Grid
 */
public class TestGrid extends TestCase {
    /**
     * Create the test case
     * 
     * @param testname name of the test case
     */
    public TestGrid(String testname) {
        super(testname);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(TestGrid.class);
    }

    public void testConstructor() {
        Grid g = new Grid();

        Location[][] data = g.data();
        for (int row = 0; row < Grid.MAX_ROWS; row++)
            for (int col = 0; col < Grid.MAX_COLS; col++)
                assertNotNull(data[row][col]);
    }

    public void testNumShips() throws LocationOutOfBoundsException, LocationAlreadyOccupiedException {
        Grid g = new Grid();
        assertEquals(0, g.getNumShips());

        Ship s = new Ship(1, 2, "test_ship", Ship.Orientation.VERTICAL, new Coordinate(0, 0));
        g.setShip(s);

        assertEquals(1, g.getNumShips());
    }

    public void testWithinBounds() {
        assertTrue(Grid.withinBounds(3, 3));
        assertFalse(Grid.withinBounds(11, 11));
    }

    public void testSetShipValid() throws Exception {
        Grid g = new Grid();

        // with this definition, we expect the ship to occupy A1 and A2 on the grid
        Ship s = new Ship(1, 2, "test_ship", Ship.Orientation.VERTICAL, new Coordinate(0, 0));

        g.setShip(s);

        Location l1 = g.at(new Coordinate('A', 1));
        Location l2 = g.at(new Coordinate('A', 2));

        // assert that ship is in location we expect
        assertTrue(l1.hasShip());
        assertTrue(l2.hasShip());

        // check surrounding locations to ensure extra ship spots weren't placed
        Location too_long = g.at(new Coordinate('A', 3));
        Location wrong_direction = g.at(new Coordinate('B', 1));

        assertFalse(too_long.hasShip());
        assertFalse(wrong_direction.hasShip());
    }

    // TODO(merritt) need to expand test cases for invalid cases
    public void testSetShipInvalid() throws LocationOutOfBoundsException, LocationAlreadyOccupiedException {
        Grid g = new Grid();
        // test horizontal ship first
        // out of bounds
        Ship s = new Ship(1, 5, "OoB_ship", Ship.Orientation.HORIZONTAL, new Coordinate('J', 1));
        try {
            g.setShip(s);

            // if we reach this point, test fails
            assertTrue("Grid.setShip should not be able to place a ship out of bounds", false);
        } catch (LocationOutOfBoundsException e) {
            assertTrue(true);
        } catch (Exception e) { // any other exception is failure
            assertTrue(false);
        }
        g.Reset();

        // location already occupied
        Ship s1 = new Ship(1, 5, "valid_ship", Ship.Orientation.HORIZONTAL, new Coordinate('C', 4));
        Ship s2 = new Ship(2, 3, "overlap_ship", Ship.Orientation.HORIZONTAL, new Coordinate('B', 4));

        g.setShip(s1);

        try {
            g.setShip(s2);

            // if we get to this point, test fails
            assertTrue("Grid.setShip should not be able to overlap ships.", false);
        } catch(LocationAlreadyOccupiedException e){
            assertTrue(true);
        } catch (Exception e){
            assertTrue(false);
        }
        g.Reset();

        // test with vertical ships
        // out of bounds
        s = new Ship(1, 5, "OoB_ship", Ship.Orientation.VERTICAL, new Coordinate('B', 10));
        try {
            g.setShip(s);

            // if we reach this point, test fails
            assertTrue("Grid.setShip should not be able to place a ship out of bounds", false);
        } catch (LocationOutOfBoundsException e) {
            assertTrue(true);
        } catch (Exception e) { // any other exception is failure
            assertTrue(false);
        }
        g.Reset();

        // location already occupied
        s1 = new Ship(1, 5, "valid_ship", Ship.Orientation.VERTICAL, new Coordinate('C', 4));
        s2 = new Ship(2, 3, "overlap_ship", Ship.Orientation.VERTICAL, new Coordinate('C', 2));

        g.setShip(s1);

        try {
            g.setShip(s2);

            // if we get to this point, test fails
            assertTrue("Grid.setShip should not be able to overlap ships.", false);
        } catch(LocationAlreadyOccupiedException e){
            assertTrue(true);
        } catch (Exception e){
            assertTrue(false);
        }
    }

    public void testCheckLocationSuccess() throws Exception {
        Grid g = new Grid();
        Ship s = new Ship(1, 2, "test_ship", Ship.Orientation.VERTICAL, new Coordinate(0, 0));

        g.setShip(s);

        assertTrue(g.checkLocationForShip(new Coordinate('A', 1)) > 0);
    }

    public void testCheckLocationFailure() throws Exception {
        Grid g = new Grid();
        Ship s = new Ship(1, 2, "test_ship", Ship.Orientation.VERTICAL, new Coordinate(0, 0));

        g.setShip(s);

        assertTrue(g.checkLocationForShip(new Coordinate('C', 7)) == 0);

        try {
            g.checkLocationForShip(new Coordinate('P', 7));

            // if we get to this point, bounds check must be invalid
            assertTrue("Grid.checkLocationForShip does not properly check bounds", false);
        } catch (LocationOutOfBoundsException e) {
            assertTrue(true);
        }

        try {
            // try to guess C7 again
            g.checkLocationForShip(new Coordinate('C', 7));

            // if we get to this point, check failed
            assertTrue("Grid.checkLocationForShip does not check if the Location was already guessed", false);
        } catch (LocationAlreadyGuessedException e) {
            assertTrue(true);
        }
    }

    public void testPeekLocationSuccess() throws Exception {
        Grid g = new Grid();
        Ship s = new Ship(1, 2, "test_ship", Ship.Orientation.VERTICAL, new Coordinate(0, 0));

        g.setShip(s);

        assertTrue(g.PeekLocation(new Coordinate('A', 1)) == 1);
    }

    public void testPeekLocationFailure() throws Exception {
        Grid g = new Grid();
        Ship s = new Ship(1, 2, "test_ship", Ship.Orientation.VERTICAL, new Coordinate(0, 0));

        g.setShip(s);

        // mark a guess at any Location
        assertTrue(g.checkLocationForShip(new Coordinate('C', 7)) == 0);

        try {
            g.PeekLocation(new Coordinate('P', 7));

            // if we get to this point, bounds check must be invalid
            assertTrue("Grid::PeekLocation does not properly check bounds", false);
        } catch (LocationOutOfBoundsException e) {
            assertTrue(true);
        }

        try {
            // try to guess C7 again
            g.PeekLocation(new Coordinate('C', 7));

            // if we get to this point, check failed
            assertTrue("Grid.PeekLocation does not check if the Location was already guessed", false);
        } catch (LocationAlreadyGuessedException e) {
            assertTrue(true);
        }
    }

    public void testAtValid() throws Exception {
        Grid g = new Grid();
        assertNotNull(g.at(new Coordinate('A', 4)));
    }

    public void testAtInvalid() {
        Grid g = new Grid();

        try {
            g.at(new Coordinate('G', 127));

            // if we reach this point, bound check failed
            assertTrue("Grid.at does not check bounds properly", false);
        } catch (LocationOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    public void testReset() throws Exception {
        Grid g = new Grid();

        Ship s = new Ship(1, 2, "test_ship", Ship.Orientation.VERTICAL, new Coordinate(0, 0));
        Coordinate c1 = new Coordinate('G', 7);
        Coordinate c2 = new Coordinate('A', 1);

        g.setShip(s);
        g.checkLocationForShip(c1);
        g.checkLocationForShip(c2);

        g.Reset();

        // check that Location of ship is now empty
        assertFalse(g.at(c2).hasShip());

        // check that guessed location is now unguessed
        assertTrue(g.at(c1).getStatus() == Location.Status.UNGUESSED);
    }

    // TODO(merritt) figure out how to test print outs
    // same issue with other print out tests: succeeds
    // on linux but fails in Windows
}
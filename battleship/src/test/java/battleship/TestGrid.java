package battleship;

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

        Location[][] data = g.getGridData();
        for (int row = 0; row < Grid.MAX_ROWS; row++)
            for (int col = 0; col < Grid.MAX_COLS; col++)
                assertNotNull(data[row][col]);
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

    //TODO(merritt) need to expand test cases for invalid cases
    public void testSetShipInvalid(){
    }

    //TODO(merritt) come back to this one

}
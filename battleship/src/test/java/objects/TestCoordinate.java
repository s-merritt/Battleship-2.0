package objects;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import objects.Coordinate;

/**
 * Unit tests objects.Coordinate
 */
public class TestCoordinate extends TestCase {
    /**
     * Create the test case
     * 
     * @param testname name of the test case
     */
    public TestCoordinate(String testname) {
        super(testname);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(TestCoordinate.class);
    }

    public void testConstructorGame() {
        Coordinate c = new Coordinate('A', 1);

        // this coordinate should be at [0, 0]
        int[] expected = { 0, 0 };
        assertEquals(expected[0], c.getGridRow());
        assertEquals(expected[1], c.getGridCol());
    }

    public void testConstructorGrid() {
        Coordinate c = new Coordinate(0, 0);

        // this coordinate should be at A1
        int[] expected = { (int) 'A', 1 };
        assertEquals(expected[0], (int) c.getGameCol());
        assertEquals(expected[1], c.getGameRow());
    }

    public void testToString() {
        Coordinate c = new Coordinate('C', 7);

        assertEquals("C7", c.toString());
    }

    public void testEquals() {
        Coordinate c1 = new Coordinate('A', 3);
        Coordinate c2 = new Coordinate('A', 3);

        assertTrue(c1.equals(c2));
    }

    public void testNotEquals() {
        // compare with null object
        Coordinate c = new Coordinate('A', 1);
        assertFalse(c.equals(null));

        // compare with non Coordinate-type Object
        assertFalse(c.equals(new Object()));

        // same row, different columns
        Coordinate c1 = new Coordinate('B', 7);
        Coordinate c2 = new Coordinate('C', 7);

        assertFalse(c1.equals(c2));

        // same column, different rows
        c1 = new Coordinate('D', 4);
        c2 = new Coordinate('D', 2);

        assertFalse(c1.equals(c2));

        // different rows and columns
        c1 = new Coordinate('A', 4);
        c2 = new Coordinate('F', 2);

        assertFalse(c1.equals(c2));
    }

    public void testneighbors() {
        Coordinate c1 = new Coordinate('A', 5);
        Coordinate c2 = new Coordinate('B', 5);

        assertEquals(Coordinate.NeighboringDirection.HORIZONTAL, c1.neighbors(c2));

        c1 = new Coordinate('A', 7);
        c2 = new Coordinate('A', 8);

        assertEquals(Coordinate.NeighboringDirection.VERTICAL, c1.neighbors(c2));
    }

    public void testNotneighbors() {
        // same row, but columns are not adjacent
        Coordinate c1 = new Coordinate('A', 5);
        Coordinate c2 = new Coordinate('F', 5);

        assertEquals(Coordinate.NeighboringDirection.NONE, c1.neighbors(c2));

        // same column, but rows are not adjacent
        c1 = new Coordinate('A', 5);
        c2 = new Coordinate('A', 9);

        assertEquals(Coordinate.NeighboringDirection.NONE, c1.neighbors(c2));

        // different rows and columns
        c1 = new Coordinate('A', 2);
        c2 = new Coordinate('F', 5);

        assertEquals(Coordinate.NeighboringDirection.NONE, c1.neighbors(c2));
    }
}

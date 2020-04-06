package objects;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import util.TestUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import objects.computers.*;

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
        Player p = new User();

        assertEquals(0, p.getNumHits());
        assertEquals(0, p.getNumMisses());
        assertEquals(5, p.getNumRemainingShips());
        assertNotNull(p.getPlayerGrid());
        assertNull(p.getOpponentPlayer());
    }

    public void testSetOpponent() {
        Player p = new User();
        Computer c = new SimpleComputer();
        
        p.setOpponentPlayer(c);
        c.setOpponentPlayer(p);

        assertEquals(c, p.getOpponentPlayer());
        assertEquals(p, c.getOpponentPlayer());
    }

    public void testMakeGuess() throws Exception {
        Player p = new User();
        Computer c = new SimpleComputer();
        
        c.createAndPlaceShip(3, "test_ship", Ship.Orientation.VERTICAL, new Coordinate('A', 1));

        p.setOpponentPlayer(c);
        c.setOpponentPlayer(p);

        // MISS
        p.makeGuess(new Coordinate('A', 4));
        assertEquals(1, p.getNumMisses());
        
        // HIT
        p.makeGuess(new Coordinate('A', 1));
        assertEquals(1, p.getNumHits());

        // sink ship
        p.makeGuess(new Coordinate('A', 2));
        p.makeGuess(new Coordinate('A', 3));
        assertEquals(4, p.getOpponentPlayer().getNumRemainingShips());
    }

    public void testNumShips() throws Exception {
        Player p = new User();
        p.loseShip();

        assertEquals(4, p.getNumRemainingShips());
    }

    public void testShipPlacement() throws Exception {
        Player p = new User();
        
        p.createAndPlaceShip(2, "test_ship", Ship.Orientation.VERTICAL, new Coordinate('A', 1));

        // check that player's ship map was populated
        assertTrue(p.getPlayerGrid().getNumShips() == 1);

        //check player's grid to ensure ship was placed
        assertTrue(p.getPlayerGrid().checkLocationForShip(new Coordinate('A', 1)) > 0);
        assertTrue(p.getPlayerGrid().checkLocationForShip(new Coordinate('A', 2)) > 0);
    }

    public void testShowGuesses() throws Exception {
        Player p = new User();
        Computer c = new SimpleComputer();

        p.setOpponentPlayer(c);
        c.setOpponentPlayer(p);

        p.makeGuess(new Coordinate('A', 4));

        // redirect stdout to be able to compare output string
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        p.showGuesses();
        switch(TestUtil.fetchOS()){
            case MAC:
            case LINUX:
                String expectedOut = " ===== Your Guesses =====\n" +
                                    "   A B C D E F G H I J \n" +
                                    "1  - - - - - - - - - - \n" +
                                    "2  - - - - - - - - - - \n" +
                                    "3  - - - - - - - - - - \n" +
                                    "4  O - - - - - - - - - \n" +
                                    "5  - - - - - - - - - - \n" +
                                    "6  - - - - - - - - - - \n" +
                                    "7  - - - - - - - - - - \n" +
                                    "8  - - - - - - - - - - \n" +
                                    "9  - - - - - - - - - - \n" +
                                    "10 - - - - - - - - - - \n";
            
                assertEquals(expectedOut, out.toString());
            case WINDOWS:
                assertTrue(true);
        }
        //reset stdout
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

    }
    
    public void testShowShips() throws Exception {
        Player p = new User();
        Computer c = new SimpleComputer();

        p.setOpponentPlayer(c);
        c.setOpponentPlayer(p);

        p.createAndPlaceShip(2, "test_ship", Ship.Orientation.VERTICAL, new Coordinate('A', 1));

        //redirect stdout to compare output string
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        p.showShips();
        switch(TestUtil.fetchOS()){
            case MAC:
            case LINUX:
                String expectedOut =" ====== Your Board ====== \n" +
                                    "   A B C D E F G H I J \n" +
                                    "1  X - - - - - - - - - \n" +
                                    "2  X - - - - - - - - - \n" +
                                    "3  - - - - - - - - - - \n" +
                                    "4  - - - - - - - - - - \n" +
                                    "5  - - - - - - - - - - \n" +
                                    "6  - - - - - - - - - - \n" +
                                    "7  - - - - - - - - - - \n" +
                                    "8  - - - - - - - - - - \n" +
                                    "9  - - - - - - - - - - \n" +
                                    "10 - - - - - - - - - - \n";     
                assertEquals(expectedOut, out.toString());
            case WINDOWS:
                assertTrue(true);
        }
        // reset stdout
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    public void testReset(){
        Player p = new User();

        p.loseShip();
        p.incrementHits();
        p.incrementMisses();

        p.Reset();
        assertTrue(p.getNumHits() == 0);
        assertTrue(p.getNumMisses() == 0);
        assertTrue(p.getNumRemainingShips() == 5);
        assertNotNull(p.getPlayerGrid());
        assertNull(p.getOpponentPlayer());
    }
}
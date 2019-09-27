package battleship;

import objects.computers.*;
import objects.*;
import objects.Ship.Orientation;

import exceptions.LocationAlreadyOccupiedException;
import exceptions.LocationOutOfBoundsException;

/**
 * @class Game
 * 
 *        Contains the game itself, alternating turns until one player is the
 *        winner
 *
 */
public class Game {

  private Computer computer;
  private User user;

  private Player current_attacker;

  /**
   * Default constructor
   */
  public Game() {
    this.computer = new HunterComputer();
    this.user = new User();
  }

  public void UserPlaceShips() {
    this.user.placeShips();
  }

  public void ComputerPlaceShips() {
    System.out.println("Now it's the computers turn to place their ships.");
    User.Pause(1);
    this.computer.placeShips();
    System.out.println("All done.");

    // TODO debug only
    this.computer.GetPlayerGrid().showShips();
  }

  public int CheckWin() {
    return 0;
  }

  /**
   * main function to call for running the game
   * 
   * @return result code (0 is all good, not zero means error)
   */
  public int Run() {
    return 0;
  }

  public static void main(String[] args) {
    Game g = new Game();

    g.UserPlaceShips();
    g.ComputerPlaceShips();

  }
}

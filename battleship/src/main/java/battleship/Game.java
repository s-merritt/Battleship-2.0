package battleship;

import objects.computers.*;
import objects.*;

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

  /**
   * keeps track of whose turn it is
   * 
   * true if user's turn, false for computer's turn
   */
  private boolean userTurn;

  /**
   * Default constructor
   */
  public Game() {
    this.computer = new HunterComputer();
    this.user = new User();

    this.computer.setOpponentPlayer(this.user);
    this.user.setOpponentPlayer(this.computer);

    this.userTurn = true; // user goes first for now, randomize this later
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

  /**
   * Checks the number of remaining ships for each player to determine a winner
   * (if there is one at the time)
   * 
   * @return 0 if no winner, 1 if user wins, -1 if computer wins
   */
  public int CheckWin() {
    if (this.user.GetNumRemainingShips() == 0)
      return 1;
    else if (this.computer.GetNumRemainingShips() == 0)
      return -1;
    else
      return 0;
  }

  public boolean isUserTurn() {
    return this.userTurn;
  }

  public void SwitchTurns() {
    this.userTurn = !this.userTurn;
  }

  public void doUserTurn() {
    this.user.GetUserGuess();
  }

  public void doComputerTurn() {
    System.out.println("Now it's the computer's turn.");
    boolean madeHit = false;
    do {
      Player.Pause(1);
      try {
        Location.Status status = this.computer.makeGuess();
        Player.Pause(1);
        if (status == Location.Status.HIT) {
          System.out.println("HIT! They get to go again.");
          madeHit = true;
        } else {
          System.out.println("MISS! That's the end of their turn");
          madeHit = false;
        }
      } catch (Exception e) {

      }
      Player.Pause(2);
    } while (madeHit);
  }

  /**
   * main function to call for running the game
   * 
   * @return result code (0 is all good, not 0 means error)
   */
  public int Run() {
    UserPlaceShips();
    ComputerPlaceShips();

    while (CheckWin() == 0) {
      // do game
      if (isUserTurn()) {
        doUserTurn();
      } else {
        doComputerTurn();
      }
      SwitchTurns();
    }

    return 0;
  }

  public static void main(String[] args) {
    Game g = new Game();

    g.Run();

  }
}

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
    Player.Pause(2);
    this.computer.placeShips();
    System.out.println("All done.");
    Player.Pause(1);

    // TODO debug only
    this.computer.getPlayerGrid().showShips();
  }

  /**
   * Checks the number of remaining ships for each player to determine a winner
   * (if there is one at the time)
   * 
   * @return 0 if no winner, 1 if user wins, -1 if computer wins
   */
  public int CheckWin() {
    if (this.user.getNumRemainingShips() == 0)
      return -1;
    else if (this.computer.getNumRemainingShips() == 0)
      return 1;
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
    System.out.println("Your turn to guess!");
    Player.Pause(1);
    System.out.println("Here's what you've guessed so far:");
    Player.Pause(1);
    System.out.println("==== Your Guesses ====");
    this.computer.getPlayerGrid().showGuesses();
    Player.Pause(2);

    while(this.user.GetUserGuess() && CheckWin() == 0);
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
      Player.Pause(1);

      System.out.println("Here's what the computer has guessed...");
      Player.Pause(1);

      System.out.println("==== Computer's Guesses ====");
      this.user.getPlayerGrid().showGuesses();

      Player.Pause(2);
    } while (madeHit && CheckWin() == 0);
  }

  /**
   * main function to call for running the game
   * 
   * @return result code (0 is all good, not 0 means error)
   */
  public int Run() {
    System.out.println("Welcome to Battleship!");
    System.out.println(" ==================== ");
    Player.Pause(2);

    UserPlaceShips();
    ComputerPlaceShips();

    while (true) {
      // do game
      if (isUserTurn()) {
        doUserTurn();
        if (CheckWin() == 1)
          break;
      } else {
        doComputerTurn();
        if (CheckWin() == -1)
          break;
      }
      SwitchTurns();
    }

    System.out.println("\n==== GAME OVER ====");
    Player.Pause(1);

    int winner = CheckWin();
    if (winner == 1) {
      System.out.println("\nHey you won, good job!");
    } else if (winner == -1) {
      System.out.println("\nAw you lost, better luck next time!");
    }
    System.out.println("Thanks for playing!");

    return 0;
  }

  public static void main(String[] args) {
    Game g = new Game();

    g.Run();

  }
}

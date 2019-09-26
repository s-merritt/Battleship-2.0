package objects.computers;

import java.util.LinkedList;
import java.util.Random;

import exceptions.LocationAlreadyGuessedException;
import exceptions.LocationOutOfBoundsException;
import objects.Coordinate;
import objects.Player;
import objects.Location.Status;

public abstract class Computer extends Player {

  protected boolean foundShip;

  protected LinkedList<Coordinate> nextGuesses;

  protected Random rand;

  public Computer() {
    this.foundShip = false;
    this.nextGuesses = new LinkedList<Coordinate>();
    this.rand = new Random();
  }

  public abstract Status makeGuess() throws LocationOutOfBoundsException, LocationAlreadyGuessedException;

  public abstract Coordinate makeNewGuess();

  public void addGuess(Coordinate coord) {
    this.nextGuesses.push(coord);
  }

  public Coordinate getNextGuess() {
    if (!this.hasGuesses()) {
      return null;
    }

    return nextGuesses.pop();
  }

  public boolean hasGuesses() {
    return !this.nextGuesses.isEmpty();
  }
}

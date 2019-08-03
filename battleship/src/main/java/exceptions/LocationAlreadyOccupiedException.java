package main.java.exceptions;

public class LocationAlreadyOccupiedException extends Exception {
  private static final long serialVersionUID = 1L;

  public LocationAlreadyOccupiedException() {
  }

  public LocationAlreadyOccupiedException(String message) {
    super(message);
  }

  public LocationAlreadyOccupiedException(Throwable cause) {
    super(cause);
  }

  public LocationAlreadyOccupiedException(String message, Throwable cause) {
    super(message, cause);
  }
}
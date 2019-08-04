package exceptions;

public class LocationAlreadyGuessedException extends Exception {
  private static final long serialVersionUID = 1L;

  public LocationAlreadyGuessedException() {
  }

  public LocationAlreadyGuessedException(String message) {
    super(message);
  }

  public LocationAlreadyGuessedException(Throwable cause) {
    super(cause);
  }

  public LocationAlreadyGuessedException(String message, Throwable cause) {
    super(message, cause);
  }

}
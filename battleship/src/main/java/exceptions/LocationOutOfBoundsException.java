package main.java.exceptions;

public class LocationOutOfBoundsException extends Exception {
  private static final long serialVersionUID = 1L;

  public LocationOutOfBoundsException(){
  }

  public LocationOutOfBoundsException(String message){
    super(message);
  }

  public LocationOutOfBoundsException(Throwable cause){
    super(cause);
  }

  public LocationOutOfBoundsException(String message, Throwable cause) {
    super(message, cause);
  }
}
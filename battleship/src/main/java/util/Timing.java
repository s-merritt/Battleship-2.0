package util;

public class Timing {
  public static void pause(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      System.exit(1);
    }
  }
}
package utils;

/**
 * Simple utility class to contain some helpful static functions for use in unit
 * tests
 */
public class TestUtil {

  /**
   * OS enum for defining current OS being used
   */
  public enum OS {
    WINDOWS, MAC, LINUX
  }

  /**
   * Field to hold the value of the current OS after it has been fetched once
   */
  private static OS aOS = null;

  /**
   * Function for fetching the OS name and saving it in a static field
   * 
   * @return aOS, a static OS enum
   */
  public static OS fetchOS() {
    if (aOS == null) {
      String operSys = System.getProperty("os.name").toLowerCase();
      if (operSys.contains("win")) {
        aOS = OS.WINDOWS;
      } else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
        aOS = OS.LINUX;
      } else if (operSys.contains("mac")) {
        aOS = OS.MAC;
      }
    }
    return aOS;
  }
}
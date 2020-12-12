/**
 * Interface with getter methods for screen objects.
 *
 * @author Christopher Dervan
 */
public interface ScreenSpec {

  /**
   * Gets resolution.
   */
  String getResolution();

  /**
   * Gets refresh rate.
   */
  int getRefreshRate();

  /**
   * Gets resolution.
   */
  int getResponseTime();

}
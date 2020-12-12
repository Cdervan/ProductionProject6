/**
 * Screen class creates a blueprint of a screen for a MoviePlayer object and implements ScreenSpec
 * interface.
 *
 * @author Christopher Dervan
 */
public class Screen implements ScreenSpec {

  final String resolution;
  final int refreshRate;
  final int responseTime;

  /**
   * Class constructor to set up fields/specs for a screen.
   *
   * @param resolution   - resolution of a screen
   * @param refreshRate  - refresh rate of a screen
   * @param responseTime - response time of a screen
   */
  Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  /**
   * ToString method used to display screen info.
   *
   * @return - string with screen specs
   */
  public String toString() {
    return "Screen: " + "\n" + "Resolution : " + resolution + "\n"
        + "Refresh Rate : " + refreshRate + "\n" + "Response time : " + responseTime;
  }

  /**
   * Gets screen resolution.
   *
   * @return - resolution
   */
  @Override
  public String getResolution() {
    return resolution;
  }

  /**
   * Gets screen refresh rate.
   *
   * @return - refreshRate
   */
  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  /**
   * Gets screen response time.
   *
   * @return - responseTime
   */
  @Override
  public int getResponseTime() {
    return responseTime;
  }
}

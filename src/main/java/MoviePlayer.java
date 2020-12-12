/**
 * MoviePlayer class builds a product object of ItemType visual that inherits from Product and
 * implements from MultimediaControl.
 *
 * @author Christopher Dervan
 */
public class MoviePlayer extends Product implements MultimediaControl {

  public final Screen screen;
  public final MonitorType monitorType;  // unused declaration

  /**
   * Class constructor that builds a moviePlayer object.
   *
   * @param name         - product name
   * @param manufacturer - manufacturer name
   * @param screen       - screen of monitor product
   * @param monitorType  - type of monitor
   */
  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType,
      int id) {
    super(id, name, manufacturer, ItemType.VISUAL);

    this.screen = screen;
    this.monitorType = monitorType;
  }

  /**
   * ToString method used in displaying product name, manufacturer, type and screen.
   *
   * @return - string with MoviePlayer info
   */
  public String toString() {
    return "Name: " + getName() + "\n" + "Manufacturer:" + getManufacturer() + "Type:"
        + ItemType.VISUAL + "\n" + screen;
  }

  /**
   * Sets name.
   *
   * @param name - product name
   */
  @Override
  public void setName(String name) {
  }

  /**
   * Sets manufacturer.
   *
   * @param manufacturer - manufacturer name
   */
  @Override
  public void setManufacturer(String manufacturer) {
  }

  /**
   * Method that prints "playing movie".
   */
  @Override
  public void play() {
    System.out.println("Playing movie");
  }

  /**
   * Method that prints "Stopping movie".
   */
  @Override
  public void stop() {
    System.out.println("Stopping movie");
  }

  /**
   * Method that prints "Next movie".
   */
  @Override
  public void previous() {
    System.out.println("Next movie");
  }

  /**
   * Method that prints "Previous move".
   */
  @Override
  public void next() {
    System.out.println("Previous move");
  }


}

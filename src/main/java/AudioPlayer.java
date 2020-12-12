/**
 * AudioPlayer class inherits from Product class and implements methods from MultimediaControl
 * interface.
 *
 * @author Christoher Dervan
 */
public class AudioPlayer extends Product implements MultimediaControl {

  public final String supportedAudioFormats;
  public final String supportedPlaylistFormats;

  /**
   * Class constructor that sets supportedAudioFormats and supportedPlaylistFormats for an
   * AudioPlayer object using Product constructor.
   *
   * @param name                     - product name
   * @param manufacturer             - manufacturer name for product
   * @param supportedAudioFormats    - audio formats that are supported by the product
   * @param supportedPlaylistFormats - playlist formats that are supported by the product
   */
  public AudioPlayer(String name, String manufacturer, String supportedAudioFormats,
      String supportedPlaylistFormats, int id) {
    super(id, name, manufacturer, ItemType.AUDIO);

    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
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
   * Set manufacturer.
   *
   * @param manufacturer - manufacturer name
   */
  @Override
  public void setManufacturer(String manufacturer) {

  }

  /**
   * Method that prints "Playing".
   */
  @Override
  public void play() {
    System.out.println("Playing");
  }

  /**
   * Method that prints "Stopping".
   */
  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  /**
   * Method that prints "Previous".
   */
  @Override
  public void previous() {
    System.out.println("Previous");
  }

  /**
   * Method that prints "Next".
   */
  @Override
  public void next() {
    System.out.println("Next");
  }

  /**
   * toString method is used to display AudioPlayer name, manufacturer, sets type to audio, and
   * shows supported audio and playlist formats.
   *
   * @return - string with product info and supported formats
   */
  public String toString() {
    return "Name: " + getName() + "\n" + "Manufacturer: "
        + getManufacturer() + "\n" + "Type: "
        + ItemType.AUDIO + "\n" + "Supported Audio Formats:" + supportedAudioFormats + "\n"
        + "Supported Playlist Formats:" + supportedPlaylistFormats;
  }
}


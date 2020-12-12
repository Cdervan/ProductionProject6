/**
 * ItemType enum contains possible item types and their respective two letter codes.
 *
 * @author Christopher Dervan
 */
public enum ItemType {

  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");

  public final String label;

  /**
   * Constructor that sets type to a string variable.
   *
   * @param label - two letter code specific to each type
   */
  ItemType(String label) {
    this.label = label;
  }

  /**
   * Method that converts string label back to ItemType.
   *
   * @param string - two letter label
   */
  public static ItemType valueOfLabel(String string) {
    switch (string) {
      case "AU":
        return AUDIO;
      case "VI":
        return VISUAL;
      case "AM":
        return AUDIO_MOBILE;
      case "VM":
        return VISUAL_MOBILE;
      default:
        return null;
    }
  }

  /**
   * Getter for label.
   *
   * @return - label
   */
  public String getLabel() {
    return label;
  }
}


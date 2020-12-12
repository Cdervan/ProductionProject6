/**
 * Item interface sets up getters and setters for product id, name and manufacturer.
 *
 * @author Christopher Dervan
 */
public interface Item {

  /**
   * Gets product id.
   */
  int getId();

  /**
   * Sets product name.
   *
   * @param name - product name
   */
  void setName(String name);

  /**
   * Gets product name.
   */
  String getName();

  /**
   * Sets manufacturer name.
   *
   * @param manufacturer - manufacturer name
   */
  void setManufacturer(String manufacturer);

  /**
   * Gets product name.
   */
  String getManufacturer();

}

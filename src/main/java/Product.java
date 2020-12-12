/**
 * Product class is the blueprint for a product object.
 *
 * @author Christopher Dervan
 */
public class Product implements Item {

  private final int id;
  public ItemType type;
  private String manufacturer;
  private String name;

  /**
   * Class constructor for product class that sets product id, name, manufacturer, and type.
   *
   * @param id           - unique product id number
   * @param name         - product name
   * @param manufacturer - name of manufacturer that produces specific product
   * @param type         - item type of product
   */
  Product(int id, String name, String manufacturer, ItemType type) {
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * ToString method prints out name, manufacturer and item type.
   *
   * @return - string with product information
   */
  public String toString() {
    return "Name: " + name + "\n" + "Manufacturer: " + manufacturer + "\n" + "Type: "
        + type;
  }

  /**
   * Gets product id.
   *
   * @return - id
   */
  public int getId() {
    return id;
  }

  /**
   * Gets manufacturer name.
   *
   * @return - manufacturer
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Sets manufacturer.
   *
   * @param manufacturer - manufacturer name
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * Gets product name.
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets product name.
   *
   * @param name - product name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets product item type.
   *
   * @return type
   */
  public ItemType getType() {
    return type;
  }

  /**
   * Sets product item type.
   *
   * @param type - ItemType of product
   */
  public void setType(ItemType type) {
    this.type = type;
  }

}
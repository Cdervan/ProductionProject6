import java.util.Date;

/**
 * Production Record class is the blueprint for productionRecord objects.
 *
 * @author Christopher Dervan
 */
public class ProductionRecord {

  public int productionNumber;
  public int productId;
  public String serialNumber;
  public Date dateProduced;

  public static int numAU = 0;
  public static int numVI = 0;
  public static int numAM = 0;
  public static int numVM = 0;

  /**
   * Class constructor, currently not in use.
   *
   * @param productId - unique product number based on Product class/table
   */
  ProductionRecord(int productId) {
    this.productId = productId;
    productionNumber = 0;
    serialNumber = String.valueOf(0);
    dateProduced = new Date();
  }

  /**
   * Overloaded ProductionRecord constructor creates ProductionRecord object.
   *
   * @param productionNumber - unique number for every product; auto increments
   * @param productId        - unique product number based on Product class/table
   * @param serialNumber     - unique product identifier code
   * @param dateProduced     - exact time and date the unit was produced
   */
  public ProductionRecord(int productionNumber, int productId,
      String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productId = productId;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  /**
   * Overloaded ProductionRecord constructor specifies the serial number.
   *
   * @param product   - instantiation of a product object from the product class
   * @param prodCount - amount of units being made for a specific product
   */
  public ProductionRecord(Product product, int prodCount) {
    this.dateProduced = new Date();

    int endDigits;

    // keeps track of end digits of serial number for each item type
    if (product.getType() == ItemType.AUDIO) {
      numAU += prodCount;
      endDigits = numAU;
    } else if (product.getType() == ItemType.VISUAL) {
      numVI += prodCount;
      endDigits = numVI;
    } else if (product.getType() == ItemType.AUDIO_MOBILE) {
      numAM += prodCount;
      endDigits = numAM;
    } else {
      numVM += prodCount;
      endDigits = numVM;
    }

    serialNumber = product.getManufacturer().toUpperCase().substring(0, 3) + product.getType()
        .getLabel() + String.format("%05d", endDigits);
  }

  /**
   * Sets up the production record string for the production log.
   *
   * @return - string with product number and name, serial number and date
   */
  @Override
  public String toString() {

    return "Prod. Num: " + productionNumber + " Product Name: " + getProductId()
        + " Serial Num: " + getSerialNum() + " Date: " + getProdDate() + "\n";
  }

  // GETTERS AND SETTERS

  /**
   * Gets production number.
   *
   * @return - int production number
   */
  public int getProductionNum() {  // unused declaration warning
    return productionNumber;
  }

  /**
   * Gets product id.
   *
   * @return - int productId
   */
  public int getProductId() {
    return productId;
  }

  /**
   * Gets serial number.
   *
   * @return - string serialNum
   */
  public String getSerialNum() {
    return serialNumber;
  }

  /**
   * Gets production date.
   *
   * @return Date prodDate
   */
  public Date getProdDate() {
    return dateProduced;
  }

  /**
   * Sets product id.
   *
   * @param prodId - product id
   */
  public void setProductId(int prodId) {  // unused declaration warning
    productId = prodId;
  }

  /**
   * Sets product serial number.
   *
   * @param serialNum - product serial number
   */
  public void setSerialNum(String serialNum) {  // unused declaration warning
    serialNumber = serialNum;
  }

  /**
   * Sets production date.
   *
   * @param prodDate - date product was produced
   */
  public void setProdDate(Date prodDate) {  // unused declaration warning
    dateProduced = prodDate;
  }

}

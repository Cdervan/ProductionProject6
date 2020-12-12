/*
  Christopher Dervan
  Contains majority of code; connects to database and allows
  for user input to be stored into database
  9/19/2020
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller class connects and adds to databases and uses those databases to display info to FXML
 * elements like tables, listviews and text areas for the main GUI application as well as button
 * inputs for on action events.
 *
 * @author Christopher Dervan
 */
public class Controller {

  private Statement stmt;
  private PreparedStatement pstmt;
  private Connection conn;

  @FXML
  private TextField txtProdName;

  @FXML
  private TextField txtMnfctr;

  @FXML
  private ComboBox<ItemType> cmbItemType;

  @FXML
  private ComboBox<Integer> cmbProdQuant;

  @FXML
  private TextArea prodLogTxt;            // text area on third tab

  @FXML
  private ListView<Product> listProducts;   // listView on second tab

  @FXML
  private TableView<Product> tableView;  // table on first tab

  @FXML
  private TableColumn<?, ?> colId;

  @FXML
  private TableColumn<?, ?> colName;

  @FXML
  private TableColumn<?, ?> colMnfctr;

  @FXML
  private TableColumn<?, ?> colType;

  public static final ObservableList<Product> productLine = FXCollections.observableArrayList();

  final ObservableList<ProductionRecord> productionRun = FXCollections.observableArrayList();

  /**
   * Button that on click inserts new product into database and adds to product table.
   *
   * @param event - Action event activates on click of button
   * @throws SQLException - use SQL
   */
  @FXML
  void addProduct(ActionEvent event) throws SQLException {

    // insert added product into database
    addToProductDb();
    System.out.println("ADDING PRODUCT");

    // call loadProductList
    loadProductList();

  }

  /**
   * Button that on click gets product info and quantity from list view and adds to production run
   * observable list which is used to add to PRODUCTIONRECORD database. Also updates text area with
   * new production record entries.
   *
   * @param event - Action event activates on click of button
   * @throws SQLException - uses SQL
   */
  @FXML
  void recProd(ActionEvent event) throws SQLException {
    System.out.println("RECORDING PRODUCTION");
    //1) Get the selected product from the Product Line ListView and the quantity from the comboBox.

    //2) Create an ArrayList of ProductionRecord objects named productionRun.

    //3) Send the productionRun to an addToProductionDB method.

    //4) call loadProductionLog
    loadProductionLog(productionRun);

    //5) call showProduction
    addToProductionRecordDb(productionRun);
    showProdLog(productionRun);
  }

  /**
   * Method that sets up combo boxes and populates tables, listviews and text areas on startup.
   *
   * @throws SQLException - uses SQL
   */
  public void initialize() throws SQLException {
    //Populates combo boxes at startup
    comboBoxSetup();

    // 2) call setupProductLineTable
    setupProductLineTable();

    // 3) associate the ObservableList with the Product Line ListView
    setupProductListView();

    // 4) call loadProductList
    loadProductList();

    // 5) call loadProductionLog
    loadProductionLog(productionRun);

  }

  /**
   * Method that adds productionRecord objects to the productionRun observable list and displays
   * production record info from the productionRecord toString.
   *
   * @param productionRun - observable list
   */
  public void showProdLog(ObservableList<ProductionRecord> productionRun) {
    productionRun.clear();

    // populate the TextArea on the Production Log tab with the information from the productionLog,
    // replacing the productId with the product name, with one line for each product produced

    int prodCount = Integer.parseInt(String.valueOf(cmbProdQuant.getValue()));
    //Product product = listProducts.getSelectionModel().getSelectedItem();
    Product product = new Product(1, "1phone", "apple", ItemType.AUDIO);
    ProductionRecord pr = new ProductionRecord(product, prodCount);

    // adds productionRecord toString to productionLog ArrayList
    productionRun.add(pr);

    // display to textarea

    for (int i = 0; i < prodCount; i++) {
      prodLogTxt.appendText(pr.toString());

    }
    // populate the TextArea on the Production Log tab with the information from the productionLog,
    // replacing the productId with the product name, with one line for each product produced
  }

  /**
   * Method that sets up product table on first tab.
   */
  public void setupProductLineTable() {

    colName.setCellValueFactory(new PropertyValueFactory("name")); // compiler issue warning VV
    colMnfctr.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    colType.setCellValueFactory(new PropertyValueFactory("type"));

    tableView.setItems(productLine);
  }

  /**
   * Method that sets up the list view on second tab.
   */
  public void setupProductListView() {
    listProducts.setItems(productLine);
  }

  /**
   * Method that adds a user entered product to the PRODUCT database.
   */
  public void addToProductDb() {
    connectDb();
    try {

      stmt = conn.createStatement();
      String sql = "SELECT * FROM PRODUCT";
      stmt.executeQuery(sql);

      //Creates string with products with insertable placeholder values
      String insertSql = "INSERT INTO PRODUCT(name, manufacturer, type) VALUES (?, ?, ?)";

      //assigns query to prepared statement
      pstmt = conn.prepareStatement(insertSql);

      pstmt.setString(1, txtProdName.getText());
      pstmt.setString(2, txtMnfctr.getText());
      pstmt.setString(3, cmbItemType.getValue().label);

      pstmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    disconnectDb();
  }

  /**
   * Method that adds a user selected product to the PRODUCTIONRECORD database.
   *
   * @param productionRun - observable list
   */
  public void addToProductionRecordDb(ObservableList<ProductionRecord> productionRun)
      throws SQLException {
    productionRun.clear();
    connectDb();

    // Loop through the productionRun, inserting productionRecord object
    // information into the ProductionRecord database table.
    for (ProductionRecord pr : productionRun) {
      int prodId = pr.getProductId();
      String serNum = pr.getSerialNum();

      String sql = "INSERT INTO PRODUCTIONRECORD(PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED) "
          + "VALUES ( ?, ?, ?)";
      pstmt = conn.prepareStatement(sql);

      pstmt.setInt(1, prodId);
      pstmt.setString(2, serNum);
      pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

      pstmt.executeUpdate();

      showProdLog(productionRun);
    }
    disconnectDb();
  }

  /**
   * Method to add products from the PRODUCT database to the productLine observable list.
   *
   * @throws SQLException - uses SQL
   */
  public void loadProductList() throws SQLException {

    connectDb();

    // Create Product objects from the Product database table and add them to the productLine
    // ObservableList (which will automatically update the Product Line ListView).
    String sql = "SELECT * FROM PRODUCT";
    stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);

    productLine.clear();

    while (rs.next()) {

      productLine.add(new Product(
          rs.getInt(1),     // populate id
          rs.getString(2),  // populate name
          rs.getString(4),  // populate manufacturer i think
          ItemType.valueOfLabel(rs.getString(3)))); // populate type from DB ^^^
    }
    disconnectDb();
  }

  /**
   * Method to create ProductionRecord objects from database and populate productionRun Observable
   * list and then display to text area.
   *
   * @param productionRun - observable list
   */
  public void loadProductionLog(ObservableList<ProductionRecord> productionRun) {
    connectDb();
    // 1) Create ProductionRecord objects from the records in the ProductionRecord database table.
    try {
      String sql = "SELECT * FROM PRODUCTIONRECORD";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        int prNum = rs.getInt("PRODUCTION_NUM");
        int prodId = rs.getInt("PRODUCT_ID");
        String serNum = rs.getString("SERIAL_NUM");
        Date date = rs.getDate("DATE_PRODUCED");

        // 2) Populate the productionLog ArrayList
        ProductionRecord productionRecord = new ProductionRecord(prNum, prodId, serNum, date);
        //ProductionRecord currentRecord = new ProductionRecord((productLine.get()))
        productionRun.add(productionRecord);

        // 3) call showProduction
        showProdLog(productionRun);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    disconnectDb();
  }

  /**
   * Method that sets up combo boxes with choices and populates them with default choices.
   */
  public void comboBoxSetup() {
    for (int i = 1; i <= 3; i++) {
      cmbItemType.getItems().clear();
      cmbItemType.getItems()
          .addAll(ItemType.AUDIO, ItemType.VISUAL, ItemType.AUDIO_MOBILE, ItemType.VISUAL_MOBILE);
      cmbItemType.getSelectionModel().selectFirst();
    }

    for (int i = 1; i <= 10; i++) {
      cmbProdQuant.getItems().addAll(i);
      cmbProdQuant.setEditable(true);
      cmbProdQuant.getSelectionModel().selectFirst();
    }

    prodLogTxt.setEditable(false);
    prodLogTxt.clear();
  }

  /**
   * Method to establish connection with a database.
   */
  public void connectDb() {

    final String jdbcDriver = "org.h2.Driver";
    final String dbUrl = "jdbc:h2:./res/HR";

    //  Database credentials
    final String user = "";
    final String pass = "";
    conn = null;
    stmt = null;

    try {
      // STEP 1: Register JDBC driver
      Class.forName(jdbcDriver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(dbUrl, user, pass);  //empty database password bug
      stmt = conn.createStatement();

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method to close connection to a database.
   */
  public void disconnectDb() {
    // STEP 4: Clean-up environment
    try {
      if (pstmt != null) {
        pstmt.close();
      }
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method to reverse the order of an Employee's password.
   */
  public String reverseString(String id) {  // unused declaration warning
    if (id.isEmpty()) {
      return id;
    }
    return reverseString(id.substring(1)) + id.charAt(0);
  }
}

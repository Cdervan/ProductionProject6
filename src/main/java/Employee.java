import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Employee class is the blueprint for a new employee.
 *
 * @author Christoher Dervan
 */
public class Employee {

  private final String firstName;
  private final String surName;
  private final String fullName;

  private String password;
  private String userName;
  public final String defaultUsername = "user@oracleacademy.Test";
  private String email;

  boolean hasSpace;
  boolean firstNameHasSpace;
  boolean lastNameHasSpace;
  boolean passWordIsValid;

  /**
   * Class constructor that makes an employee object if criteria is accepted.
   *
   * @param fullName - Employees' first and last name
   * @param passW    - Employee password
   */
  public Employee(String fullName, String passW) {
    this.firstName = getFirstName();
    this.surName = getSurName();
    this.fullName = firstName + " " + surName;
    this.password = passW;

    checkName(firstName, surName);

    if (hasSpace) {
      setUserName(defaultUsername);
    } else {
      setUserName(fullName.substring(0, 1).toLowerCase() + surName.toLowerCase());
    }

    setEmail(firstName.toLowerCase() + "." + surName.toLowerCase() + "@oracleacademy.Test");

    isValidPassword(password);
    if (passWordIsValid) {
      password = passW;
    } else {
      password = "pw";
    }

  }

  /**
   * Method that checks first and last name for spaces.
   *
   * @param firstName - first name of employee
   * @param surName   - last name of employee
   */
  public void checkName(String firstName, String surName) {
    Pattern pattern = Pattern.compile("//s");
    Matcher firstMatcher = pattern.matcher(firstName);
    firstNameHasSpace = firstMatcher.find(); //returns true if space

    Matcher lastMatcher = pattern.matcher(surName);
    lastNameHasSpace = lastMatcher.find();

    hasSpace = firstNameHasSpace || lastNameHasSpace;
  }

  /**
   * Method that checks password to see if it meets requirements: an uppercase letter, a lowercase
   * letter, and a special character.
   *
   * @param password - employee password
   */
  public void isValidPassword(String password) {
    Pattern pattern = Pattern.compile("(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[#?!@$%^&*-])");
    Matcher matcher = pattern.matcher(password);
    passWordIsValid = matcher.find(); //returns true if password is valid
  }

  /**
   * Method used to display employee details.
   *
   * @return - string with employees' name, username, email, and password
   */
  public String toString() {
    return "Employee Details \n" + "Name : " + fullName + "\n"
        + "Username : " + userName + "\n" + "Email : " + email + "\n"
        + "Initial Password : " + password;
  }

  // GETTERS AND SETTERS

  /**
   * Sets username.
   *
   * @param username - employee username
   */
  public void setUserName(String username) {
    this.userName = username;
  }

  /**
   * Sets email.
   *
   * @param email - employee email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets employees' firstName.
   *
   * @return - firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Gets employees' last name.
   *
   * @return - surName
   */
  public String getSurName() {
    return surName;
  }

}

package steamlogin.database.table;

public class Account {
  
  private int id;
  private String username;
  private String password;
  private String using;
  private String usingComputer;
  
  public Account(int id, String username, String password, String using, String usingComputer) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.using = using;
    this.usingComputer = usingComputer;
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the using
   */
  public String getUsing() {
    return using;
  }

  /**
   * @param using the using to set
   */
  public void setUsing(String using) {
    this.using = using;
  }

  /**
   * @return the usingComputer
   */
  public String getUsingComputer() {
    return usingComputer;
  }

  /**
   * @param usingComputer the usingComputer to set
   */
  public void setUsingComputer(String usingComputer) {
    this.usingComputer = usingComputer;
  }
  
}
